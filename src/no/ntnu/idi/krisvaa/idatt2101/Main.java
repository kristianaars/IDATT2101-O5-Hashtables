package no.ntnu.idi.krisvaa.idatt2101;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        int m = 118;
        HashTable h = new HashTable(m);

        Scanner s = new Scanner(new URL("http://www.iie.ntnu.no/fag/_alg/hash/navn.txt").openStream());

        while (s.hasNext()) {
            h.insert(s.nextLine());
        }

        System.out.println(h);

    }
}
