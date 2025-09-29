public class Lab01_Q2 {
    public static void main(String[] args) {
        //First define then calculate x
        double a = 23.2;
        double b = 7.1;
        double c = 2.2;
        double d = 5.1;
        double e = 3.7;
        double f = 3.4;
        double g = 4.2;
        double x = (a - (b / c))/((d - e) * (f + g));
        System.out.println("x = " + x);
        //Same for y
        double h = 35.7;
        double i = 64.1;
        double j = 6.0;
        double k = 43;
        double l = 5;
        double m = 3;
        double n = Math.pow(j, m);
        double o = 2;
        double p = Math.pow(l, o);
        double y = (h * i - n)/(k + p);
        System.out.println("y = " + y);
        //Same for z
        double r = 2.1;
        double s = 8.0;
        double t = r + s;
        double u = -1.0 / 3.0;
        double z = Math.pow(t, u);
        System.out.println("z = " + z);



    }
}
