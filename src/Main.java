import java.net.URL;
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

        System.out.println("");
        System.out.println("TableSize;TableCount;ProbeType;FillGrade;FillTime;CollisionGrade;CollisionCount;");
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
            int res = hashTable.insert(sourceArray[i]);

            if(res < 0) {
                System.out.println("Table is full");
            }
        }

        long timeElapsed = System.currentTimeMillis() - startTime;
        double collisionGrade = hashTable.getCollisionCounter() / (double) hashTable.getCount();
        System.out.println(hashTable.getSize() + ";" + hashTable.getCount() + ";" + hashType + ";" + fillGrade+";" + timeElapsed+"ms;" + collisionGrade + ";" + hashTable.getCollisionCounter() + ";");
    }

    public static int[] generateRandomizedIntArray(int size) {
        //Generate numbers
        Random r = new Random();

        int lastNum = 100;
        int[] array = new int[size];
        for(int i = 0; i<array.length; i++) {
            lastNum = lastNum + r.nextInt(100);
            array[i] = lastNum;
        }

        //Shuffle numbers
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
