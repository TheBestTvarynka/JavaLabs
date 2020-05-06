import java.util.*;
import entity.Point;
import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {
        Set<Point> set = createSet();
        System.out.println(set);

        ArrayList<Double> line = createLine();
        System.out.println(line);

        Pair<Point, Point> max_min = findMaxMin(set, line);
        System.out.println(max_min);
    }

    public static Set<Point> createSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount of the points:");
        int amount = scanner.nextInt();
        Set<Point> set = new HashSet<Point>(amount);
        double x, y;
        for (int i = 0; i < amount; i++) {
            System.out.println("Enter coordinated of the point:");
            x = scanner.nextDouble();
            y = scanner.nextDouble();
            set.add(new Point(x, y));
        }
        return set;
    }

    public static ArrayList<Double> createLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter koeficients of the line:");
        ArrayList<Double> line = new ArrayList<>();
        line.add(scanner.nextDouble());
        line.add(scanner.nextDouble());
        line.add(scanner.nextDouble());
        return line;
    }

    public static Pair<Point, Point> findMaxMin (Set<Point> set, ArrayList<Double> line) {
        double a = line.get(0);
        double b = line.get(1);
        double c = line.get(2);

        Iterator<Point> it = set.iterator();
        Point max = it.next();
        double maxDistance = determineDistance(max.getX(), max.getY(), a, b, c);
        Point min = max;
        double minDistance = determineDistance(min.getX(), min.getY(), a, b, c);

        Point current;
        double distance;
        while (it.hasNext()) {
            current = it.next();
            distance = determineDistance(current.getX(), current.getY(), a, b, c);
            if (distance > maxDistance) {
                maxDistance = distance;
                max = current;
            }
            if (distance < minDistance) {
                minDistance = distance;
                min = current;
            }
        }
        return new Pair<>(max, min);
    }

    public static double determineDistance (double x, double y, double a, double b, double c) {
        return Math.abs(a * x + b * y + c) / Math.sqrt(a * a + b * b);
    }
}
