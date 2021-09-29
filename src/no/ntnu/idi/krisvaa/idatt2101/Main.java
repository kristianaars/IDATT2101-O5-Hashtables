package no.ntnu.idi.krisvaa.idatt2101;

public class Main {

    public static void main(String[] args) {
        int m = 64;

        HashTable<String> h = new HashTable<>();

        int x = (int) (Math.log(m) / Math.log(2));

        System.out.println(Hash.multhash(654365478, x));
    }
}
