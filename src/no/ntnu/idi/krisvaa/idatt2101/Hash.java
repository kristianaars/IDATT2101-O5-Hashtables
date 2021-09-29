package no.ntnu.idi.krisvaa.idatt2101;

public class Hash {

    /**
     * @param k Key to be hashed
     * @param m NUmber of elements in hashtable
     * @return
     */
    public static int divhash(int k, int m) {
        return k % m;
    }


    /**
     * @param k Key to be hashed
     * @param x Number of bits in tablesize (Where tablesize m = 2^x)
     * @return Hashed key-value
     */
    private static int A = 1327217885;
    public static int multhash(int k, int x) {
        return k * A >> (31 - x);
    }
}
