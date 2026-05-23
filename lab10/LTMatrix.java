public class LTMatrix extends Matrix {

    public LTMatrix(float[][] mat) {
        this(mat, true);
    }

    private LTMatrix(float[][] mat, boolean printMessage) {
        super(mat);

        boolean isOkay = true;
        if (mat.length != mat[0].length) {
            isOkay = false;
        } else {
            for (int i = 0; i < mat.length; i++) {
                for (int j = i + 1; j < mat[0].length; j++) {
                    if (mat[i][j] != 0) {
                        isOkay = false;
                        break;
                    }
                }
                if (!isOkay)
                    break;
            }
        }

        if (!isOkay) {
            System.out.println("Invalid lower triangular matrix");
            this.mat = new float[0][0];
        } else {
            if (printMessage) {
                System.out.println("Constructed the LTMatrix");
            }
        }
    }

    @Override
    public Algebraic negate() {
        float[][] newArray = new float[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j <= i; j++) {
                newArray[i][j] = -this.mat[i][j];
            }
        }
        return new LTMatrix(newArray, false);
    }

    @Override
    public Algebraic add(Algebraic other) {
        if (!(other instanceof Matrix))
            return null;
        if (other instanceof LTMatrix) {
            LTMatrix otherLTMatrix = (LTMatrix) other;
            if (this.mat.length != otherLTMatrix.mat.length) {
                return null;
            }
            float[][] newData = new float[mat.length][mat[0].length];
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j <= i; j++) {
                    newData[i][j] = this.mat[i][j] + otherLTMatrix.mat[i][j];
                }
            }
            return new LTMatrix(newData, false);
        }
        return super.add(other);
    }

    @Override
    public Algebraic subtract(Algebraic other) {
        if (!(other instanceof Matrix))
            return null;
        if (other instanceof LTMatrix) {
            LTMatrix otherLT = (LTMatrix) other;
            if (this.mat.length != otherLT.mat.length)
                return null;

            float[][] newData = new float[mat.length][mat[0].length];
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j <= i; j++) {
                    newData[i][j] = this.mat[i][j] - otherLT.mat[i][j];
                }
            }
            return new LTMatrix(newData, false);
        }
        return super.subtract(other);
    }

    @Override
    public Algebraic multiply(Algebraic other) {
        if (other instanceof Vector) {
            float[] vec = ((Vector) other).getVec();
            if (this.mat[0].length != vec.length)
                return null;

            float[] result = new float[this.mat.length];
            for (int i = 0; i < this.mat.length; i++) {
                for (int j = 0; j <= i; j++) {
                    result[i] += this.mat[i][j] * vec[j];
                }
            }
            return new Vector(result);
        }
        if (other instanceof LTMatrix) {
            LTMatrix otherLT = (LTMatrix) other;
            if (this.mat[0].length != otherLT.mat.length)
                return null;

            float[][] result = new float[this.mat.length][otherLT.mat[0].length];
            for (int i = 0; i < this.mat.length; i++) {
                for (int j = 0; j <= i; j++) {
                    for (int k = j; k <= i; k++) {
                        result[i][j] += this.mat[i][k] * otherLT.mat[k][j];
                    }
                }
            }
            return new LTMatrix(result, false);
        }

        return super.multiply(other);
    }

    @Override
    public Vector determinant() {
        if (this.mat.length == 0)
            return null;

        float det = 1.0f;
        for (int i = 0; i < this.mat.length; i++) {
            det *= this.mat[i][i];
        }
        return new Vector(new float[] { det });
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;

        if (other instanceof LTMatrix) {
            LTMatrix otherLTMatrix = (LTMatrix) other;
            if (this.mat.length != otherLTMatrix.mat.length)
                return false;

            for (int i = 0; i < this.mat.length; i++) {
                for (int j = 0; j <= i; j++) {
                    if (Math.abs(this.mat[i][j] - otherLTMatrix.mat[i][j]) > 1e-6) {
                        return false;
                    }
                }
            }
            return true;
        }

        if (other instanceof Matrix) {
            return super.equals(other);
        }

        return false;
    }
}