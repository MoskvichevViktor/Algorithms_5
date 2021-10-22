package lesson_5;

public class Pow {
    public static void main(String[] args) {

        System.out.println(pow(2.50, 4));

    }

    private static double pow(double x, int power) {
        if (power < 0) throw new IllegalArgumentException("Неверное значение степени");
        if (power == 1) {
            return x;
        } else if (power == 0) {
            return 1;
        } else {
            return x * pow(x, --power);
        }
    }
}

