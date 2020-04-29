import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = null;
        try {
            list = manualCreation();
        } catch (IOException e) {
            System.out.println("Unable to create a list.");
        }
        // list = generateList();
        // printList(list);
        System.out.println("Please, enter the value:");
        int number = new Scanner(System.in).nextInt();
        int result = findClosestByValue(list, number);
        System.out.print(result);
    }

    public static List<Integer> generateList() {
        int size = new Scanner(System.in).nextInt();
        List<Integer> list = new LinkedList<>();
        Random objGenerator = new Random();
        for (int i = 0; i < size; i++) {
            list.add(objGenerator.nextInt(100));
        }
        return list;
    }

    public static List<Integer> manualCreation() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    public static int findClosestByValue(List<Integer> list, int value) {
        Iterator<Integer> it = list.iterator();
        int result = list.get(0);
        int current;
        while (it.hasNext()) {
            current = it.next();
            if (Math.abs(value - current) < Math.abs(value - result)) {
                result = current;
            }
        }
        return result;
    }

    public static void printList(List<Integer> list) {
        for (Integer number : list) {
            System.out.println(number);
        }
    }
}
