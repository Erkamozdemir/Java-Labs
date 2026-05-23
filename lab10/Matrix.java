public class Matrix implements Algebraic {
    protected float[][] mat;

    public Matrix(float[][] matrix) {
        this.mat = new float[matrix.length][];

        for (int i = 0; i < matrix.length; i++) {
            this.mat[i] = matrix[i].clone();
        }
    }

    public float[][] getMat() {
        return this.mat;
    }

    public Algebraic negate() {
        float[][] negatedMatrixArray = new float[this.mat.length][this.mat[0].length];

        for (int i = 0; i < this.mat.length; i++) {
            for (int j = 0; j < this.mat[i].length; j++) {
                negatedMatrixArray[i][j] = (float) (-1.0 * this.mat[i][j]);
            }
        }
        return new Matrix(negatedMatrixArray);
    }

    public Algebraic add(Algebraic algebraic) {
        if (!(algebraic instanceof Matrix) || this.mat.length != ((Matrix) algebraic).mat.length
                || this.mat[0].length != ((Matrix) algebraic).mat[0].length) {
            return null;
        } else {
            Matrix sumMatrix = new Matrix(new float[this.mat.length][this.mat[0].length]);
            for (int i = 0; i < this.mat.length; i++) {
                for (int j = 0; j < this.mat[i].length; j++) {
                    sumMatrix.mat[i][j] = (float) (this.mat[i][j] + ((Matrix) algebraic).mat[i][j]);
                }
            }
            return sumMatrix;
        }
    }

    public Algebraic subtract(Algebraic algebraic) {
        if (!(algebraic instanceof Matrix) || this.mat.length != ((Matrix) algebraic).mat.length
                || this.mat[0].length != ((Matrix) algebraic).mat[0].length) {
            return null;
        } else {
            return add(algebraic.negate());
        }
    }

    public Algebraic multiply(Algebraic algebraic) {
        if (algebraic instanceof Vector) {
            float[] vec = ((Vector) algebraic).getVec();
            if (this.mat[0].length != vec.length) {
                return null;
            }
            float[] result = new float[this.mat.length];
            for (int i = 0; i < this.mat.length; i++) {
                for (int j = 0; j < vec.length; j++) {
                    result[i] += this.mat[i][j] * vec[j];
                }
            }
            return new Vector(result);
        }
        if (algebraic instanceof Matrix) {
            float[][] other = ((Matrix) algebraic).mat;
            if (this.mat[0].length != other.length) {
                return null;
            }
            float[][] result = new float[this.mat.length][other[0].length];
            for (int i = 0; i < this.mat.length; i++) {
                for (int j = 0; j < other[0].length; j++) {
                    for (int k = 0; k < this.mat[0].length; k++) {
                        result[i][j] += this.mat[i][k] * other[k][j];
                    }
                }
            }
            return new Matrix(result);
        }
        return null;
    }

    public Vector determinant() {

        if (this.mat.length != this.mat[0].length) {
            return null;
        }

        switch (this.mat.length) {
            case 2:
                float a2 = this.mat[0][0];
                float b2 = this.mat[0][1];
                float c2 = this.mat[1][0];
                float d2 = this.mat[1][1];
                float determinant2 = a2 * d2 - b2 * c2;
                return new Vector(new float[] { determinant2 });

            case 3:
                float a3 = this.mat[0][0];
                float b3 = this.mat[0][1];
                float c3 = this.mat[0][2];
                float d3 = this.mat[1][0];
                float e3 = this.mat[1][1];
                float f3 = this.mat[1][2];
                float g3 = this.mat[2][0];
                float h3 = this.mat[2][1];
                float i3 = this.mat[2][2];
                float determinant3 = a3 * (e3 * i3 - f3 * h3) - b3 * (d3 * i3 - f3 * g3) + c3 * (d3 * h3 - e3 * g3);
                return new Vector(new float[] { determinant3 });

            default:
                return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Matrix)) {
            return false;
        }
        Matrix otherMat = (Matrix) other;
        if (this.mat.length != otherMat.mat.length || this.mat[0].length != otherMat.mat[0].length) {
            return false;
        }
        for (int i = 0; i < this.mat.length; i++) {
            for (int j = 0; j < this.mat[0].length; j++) {
                float diff = Math.abs(this.mat[i][j] - otherMat.mat[i][j]);
                if (diff > 1e-6) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.mat.length; i++) {
            sb.append("|");
            for (int j = 0; j < this.mat[i].length; j++) {
                sb.append(String.format("%.2f", this.mat[i][j]));
                if (j < this.mat[i].length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("|");
            if (i < this.mat.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
