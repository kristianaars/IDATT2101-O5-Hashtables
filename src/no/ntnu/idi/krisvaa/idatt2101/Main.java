package no.ntnu.idi.krisvaa.idatt2101;

import java.net.URL;
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
    }
}
