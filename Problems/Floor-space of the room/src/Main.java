import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "triangle":
                Triangle triangle = new Triangle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                System.out.printf("%.1f %n", triangle.getArea());
                break;
            case "rectangle":
                Rectangle rectangle = new Rectangle(scanner.nextDouble(), scanner.nextDouble());
                System.out.printf("%.1f %n", rectangle.getArea());
                break;
            case "circle":
                Circle circle = new Circle(scanner.nextDouble());
                System.out.printf("%.1f %n", circle.getArea());
                break;
            default:
                System.out.println("Unknown");
                break;
        }
    }
}

class Triangle {
    double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    double getArea() {
        double p = (a + b + c) * 0.5;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}

class Rectangle {
    double a, b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    double getArea() {
        return a * b;
    }
}

class Circle {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}