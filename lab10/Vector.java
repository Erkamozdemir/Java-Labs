public class Vector implements Algebraic {
    private float[] VectorArray;

    public Vector(float[] vec) {
        this.VectorArray = vec.clone();
    }

    public float[] getVec() {
        return this.VectorArray;
    }

    public Algebraic negate() {
        Vector negatedVector = new Vector(new float[this.VectorArray.length]);
        for (int i = 0; i < this.VectorArray.length; i++) {
            negatedVector.VectorArray[i] = (float) (-1.0 * this.VectorArray[i]);
        }
        return negatedVector;
    }

    public Algebraic add(Algebraic algebraic) {
        if (!(algebraic instanceof Vector) || this.VectorArray.length != ((Vector) algebraic).VectorArray.length) {
            return null;
        } else {
            Vector sumVector = new Vector(new float[this.VectorArray.length]);
            for (int i = 0; i < this.VectorArray.length; i++) {
                sumVector.VectorArray[i] = (float) (this.VectorArray[i] + ((Vector) algebraic).VectorArray[i]);
            }
            return sumVector;
        }
    }

    public Algebraic subtract(Algebraic algebraic) {
        if (!(algebraic instanceof Vector) || this.VectorArray.length != ((Vector) algebraic).VectorArray.length) {
            return null;
        } else {
            return this.add(algebraic.negate());
        }
    }

    public Algebraic multiply(Algebraic algebraic) {
        if (!(algebraic instanceof Vector) || this.VectorArray.length != ((Vector) algebraic).VectorArray.length) {
            return null;
        } else {
            float multiplication = 0;
            for (int i = 0; i < this.VectorArray.length; i++) {
                multiplication += (float) (this.VectorArray[i] * ((Vector) algebraic).VectorArray[i]);
            }
            float[] multiplicationArray = { multiplication };
            return new Vector(multiplicationArray);
        }
    }

    public Vector crossproduct(Vector other) {
        if (this.VectorArray.length != 3 || other.VectorArray.length != 3) {
            return null;
        }
        float[] a = this.VectorArray;
        float[] b = other.VectorArray;

        float x = (a[1] * b[2]) - (a[2] * b[1]);
        float y = (a[2] * b[0]) - (a[0] * b[2]);
        float z = (a[0] * b[1]) - (a[1] * b[0]);

        float[] results = { x, y, z };
        return new Vector(results);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Vector)) {
            return false;
        }
        Vector otherVec = (Vector) other;
        if (this.VectorArray.length != otherVec.VectorArray.length) {
            return false;
        }
        for (int i = 0; i < this.VectorArray.length; i++) {
            float diff = Math.abs(this.VectorArray[i] - otherVec.VectorArray[i]);
            if (diff > Math.pow(10, -6)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.VectorArray.length; i++) {
            if (this.VectorArray[i] >= 0) {
                sb.append(String.format("| %.2f|", this.VectorArray[i]));
            } else {
                sb.append(String.format("|%.2f|", this.VectorArray[i]));
            }
            if (i < this.VectorArray.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
