package no.ntnu.idi.krisvaa.idatt2101;

public abstract class OpenAddressHashTable {

    protected int collisionCounter = 0;
    protected Integer[] elements;
    protected int size;
    //Power of two to get to size (size = 2^x)
    protected int x;

    public OpenAddressHashTable(int size) {
        this.size = findNearestPowerOfTwo(size);
        this.x = (int) (Math.log(this.size) / Math.log(2));

        elements = new Integer[size];
    }

    private int findNearestPowerOfTwo(int n) {
        int power = 2;
        while (power < n) {
            power = power << 1;
        }

        return power;
    }

    public abstract int insert(int e);

    public abstract int findPos(int e);

}
