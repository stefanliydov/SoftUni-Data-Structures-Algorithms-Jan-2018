package sweep_and_prime;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = new ArrayList<>();
        Map<String, Item> byId = new HashMap<>();

        int ticks = 0;
        String line = scanner.nextLine();
        while (!Objects.equals (line, "end")) {
            String[] temp = line.split(" ");
            switch (temp[0]) {
                case "add":
                    addItem(items, byId, temp);
                    break;
                case "tick":
                    ticks++;
                    sweep(ticks,items);
                    break;
                case "move":
                    ticks++;
                    String id = temp[1];
                    int newX = Integer.parseInt(temp[2]);
                    int newY = Integer.parseInt(temp[3]);
                    byId.get(id).setX(newX);
                    byId.get(id).setY(newY);
                    sweep(ticks,items);
                    break;
                default:
                    break;
            }
            line = scanner.nextLine();
        }
    }

    private static void addItem(List<Item> items, Map<String, Item> byId, String[] temp) {
        String id = temp[1];
        int x = Integer.parseInt(temp[2]);
        int y = Integer.parseInt(temp[3]);
        Item item = new Item(id, x, y);
        items.add(item);
        byId.putIfAbsent(id, item);
    }

    private static void insertionSort(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            int j = i;
            while (j > 0 && items.get(j - 1).getX() > items.get(j).getX()) {
                swap(j - 1, j, items);
                j--;
            }
        }
    }

    private static void swap(int i, int j, List<Item> items) {
        Item temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }

    private static void sweep(int ticks, List<Item> items) {
        insertionSort(items);
        for (int i = 0; i < items.size(); i++) {
            Item current = items.get(i);
            for (int j = i+1; j <items.size(); j++) {
                Item candidate = items.get(j);

                if(current.interesect(candidate)){
                    System.out.printf("(%d) %s collides with %s",ticks,current.getId(), candidate.getId());
                    System.out.println();
                }
            }

        }

    }
}
