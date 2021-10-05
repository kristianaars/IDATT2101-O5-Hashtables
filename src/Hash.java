public class Hash {

    /**
     * @param s String to be hashed
     * @param x Number of bits in tablesize (Where tablesize m = 2^x)
     * @return Hashed key-value
     */
    public static int multhash(String s, int x) {
        int k = 1;
        for(int i = 1; i < s.length(); i++) {
            k = s.charAt(i - 1) + 113 * k;
        }
        return multhash(k, x);
    }

    /**
     * @param k Key to be hashed
     * @param x Number of bits in tablesize (Where tablesize m = 2^x)
     * @return Hashed key-value
     */
    private static long A = 2654435769L;
    public static int multhash(int k, int x) {
        int kA = (int) (k * A);
        return kA > 0 ? kA >> (31 - x) : (kA * -1) >> (31 - x);
    }


    /**
     * @param k Key to be hashed
     * @param m NUmber of elements in hashtable
     * @return
     */
    public static int divhash(int k, int m) {
        return k % m;
    }

}
