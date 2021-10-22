package lesson_5;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int maxWeight = 6;

        ArrayList<Item> things = new ArrayList<Item>();
        things.add(new Item("смартфон", 1,10000));
        things.add(new Item("часы", 2, 15000));
        things.add(new Item("ноутбук", 3, 45000));
        things.add(new Item("термос", 2, 1000));
        things.add(new Item("книга", 1, 500));

        BackPack bp = new BackPack(maxWeight, things);
        System.out.println("Максимальный вес рюкзака: " + maxWeight);

        bp.combinate(things); //перебор

        ArrayList<Item> result = bp.getBestResult(); //результат

        for (Item thing : result) {
            System.out.println(thing.toString());
        }

    }
}

class Item {
    String name;
    int weight, cost;

    public Item(String name, int weight, int cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    public String getName() { return name; }
    public int getWeight() { return weight; }
    public int getCost() { return cost; }

    @Override
    public String toString() {
        return String.format("%10s, вес: %2d, стоимость: %5d", name, weight, cost);
    }
}

class BackPack {
    int maxBackPackWeight;      // максимальный вес рюкзака
    int maxPrice;               // максимальная стоимость найденной комбинации
    ArrayList<Item> objects;    // список предметов
    ArrayList<Item> bestResult; // список предметов с максимальной стоимость

    public BackPack(int maxWeight, ArrayList<Item> objects) {
        this.maxBackPackWeight = maxWeight;
        this.objects = objects;
        this.maxPrice = 0;
        this.bestResult = null;
    }

    // вес  предметов
    public int getWeightCurrentSet(ArrayList<Item> items) {
        int w = 0;
        for (Item thing : items) {
            w += thing.getWeight();
        }
        return w;
    }

    //  стоимость предметов
    public int getValueCurrentSet(ArrayList<Item> items) {
        int v = 0;
        for (Item thing : items) {
            v += thing.getCost();
        }
        return v;
    }

    // сравнить набор предметов с сохраненным
    public void compare(ArrayList<Item> items) {
        if (bestResult == null) {
            if (getWeightCurrentSet(items) <= maxBackPackWeight) {
                bestResult = items;
                maxPrice = getValueCurrentSet(items);
            }
        } else {
            if(getWeightCurrentSet(items) <= maxBackPackWeight && getValueCurrentSet(items) > maxPrice) {
                bestResult = items;
                maxPrice = getValueCurrentSet(items);
            }
        }
    }

    // перебор всех вариантов
    public void combinate(ArrayList<Item> objects) {
        if (objects.size() > 0) compare(objects);
        for (int i = 0; i < objects.size(); i++) {
            ArrayList<Item> newSet = new ArrayList<Item>(objects);
            newSet.remove(i);
            combinate(newSet);
        }
    }

    public ArrayList<Item> getBestResult() {
        return bestResult; //  лучший вариант
    }
}
