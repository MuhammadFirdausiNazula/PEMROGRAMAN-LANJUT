public class Circle {
    public static final double PHI = 3.14;
    private double r;

    public Circle(double radius){
        this.setR(radius);
    }

    public static void main (String [] args){
        Circle circle = new Circle(2.5);
        System.out.println("Radius:" + circle.getR());
        System.out.println("Area:" + getaDouble(circle));
    }

    private static double getaDouble(Circle circle) {
        return PHI * circle.getR() * circle.getR();
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
