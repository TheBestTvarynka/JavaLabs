import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = generateList();
        printList(list);
        int number = new Scanner(System.in).nextInt();
        int result = findClosestByValue(list, number);
        System.out.print(result);
    }

    public static LinkedList<Integer> generateList() {
        int size = new Scanner(System.in).nextInt();
        LinkedList<Integer> list = new LinkedList<>();
        Random objGenerator = new Random();
        for (int i = 0; i < size; i++) {
            list.add(objGenerator.nextInt(100));
        }
        return list;
    }

    public static LinkedList<Integer> manualCreation() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = System.in.read();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    public static int findClosestByValue(LinkedList<Integer> list, int value) {
        Iterator<Integer> it = list.iterator();
        int result = list.element();
        int current;
        while (it.hasNext()) {
            current = it.next();
            if (Math.abs(value - current) < Math.abs(value - result)) {
                result = current;
            }
        }
        return result;
    }

    public static void printList(LinkedList<Integer> list) {
        for (Integer number : list) {
            System.out.println(number);
        }
    }
}
