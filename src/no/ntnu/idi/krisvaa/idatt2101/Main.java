package no.ntnu.idi.krisvaa.idatt2101;

import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        int m = 118;
        HashTable h = new HashTable(m);
        Scanner s = new Scanner(new URL("http://www.iie.ntnu.no/fag/_alg/hash/navn.txt").openStream());

        while (s.hasNext()) {
            h.insert(s.nextLine());
        }

        System.out.println("Table looks like this: \n" + h);
        float loadFactor = h.getLoadFactor();
        int collisions = h.getCollisionCounter();
        float collisionPerPerson = collisions / (float) h.getCount();

        System.out.println("Load factor: " + loadFactor);
        System.out.println("Collisions: " + collisions);
        System.out.println("Collisions/person: " + collisionPerPerson);

        System.out.println("");

        System.out.println("Attempting to find \"Kristian Valset Aars\" in table:");
        System.out.println("Result: " + h.find("Kristian Valset Aars"));
        System.out.println("");
        System.out.println("Attempting to find \"Mildrid Ljosland\" in table:");
        System.out.println("Result: " + h.find("Mildrid Ljosland"));

        LinearProbeHashTable linearProbe = new LinearProbeHashTable(1000000);
        DoubleHashProbeHashTable doubleHashProbe = new DoubleHashProbeHashTable(1000000);
        SquaredProbeHashTable squaredHashTable = new SquaredProbeHashTable(1000000);

        int size = linearProbe.getSize();
        int[] numberArray = generateRandomizedIntArray(size);

        performTest(numberArray, HashTypes.Linear, 0.5);
        performTest(numberArray, HashTypes.Linear, 0.8);
        performTest(numberArray, HashTypes.Linear, 0.9);
        performTest(numberArray, HashTypes.Linear, 0.99);
        performTest(numberArray, HashTypes.Linear, 1);

        performTest(numberArray, HashTypes.DoubleHash, 0.5);
        performTest(numberArray, HashTypes.DoubleHash, 0.8);
        performTest(numberArray, HashTypes.DoubleHash, 0.9);
        performTest(numberArray, HashTypes.DoubleHash, 0.99);
        performTest(numberArray, HashTypes.DoubleHash, 1);

        performTest(numberArray, HashTypes.SquaredHash, 0.5);
        performTest(numberArray, HashTypes.SquaredHash, 0.8);
        performTest(numberArray, HashTypes.SquaredHash, 0.9);
        performTest(numberArray, HashTypes.SquaredHash, 0.99);
        performTest(numberArray, HashTypes.SquaredHash, 1);


    }

    public enum HashTypes { Linear, DoubleHash, SquaredHash }

    public static void performTest(int[] sourceArray, HashTypes hashType, double fillGrade) {
        int size = sourceArray.length;
        int endRange = (int) (size * fillGrade);

        OpenAddressHashTable hashTable = null;
        switch (hashType) {
            case Linear -> hashTable = new LinearProbeHashTable(size);
            case DoubleHash -> hashTable = new DoubleHashProbeHashTable(size);
            case SquaredHash -> hashTable = new SquaredProbeHashTable(size);
        }

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < endRange; i++) {
            hashTable.insert(sourceArray[i]);
        }

        long timeElapsed = System.currentTimeMillis() - startTime;
        System.out.println("" + hashType + ";"+fillGrade+";"+timeElapsed+"ms;");
    }

    public static int[] generateRandomizedIntArray(int size) {
        //Generate numbers
        int[] array = new int[size];
        for(int i = 0; i<array.length; i++) {
            array[i] = i + 1;
        }

        //Shuffle numbers
        Random r = new Random();
        for(int i = 0; i < array.length; i++) {
            int swapIndex = r.nextInt(array.length - 1);
            swap(swapIndex, i, array);
        }

        return array;
    }

    public static void swap(int x, int y, int[] a) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }
}
