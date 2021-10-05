import java.util.Arrays;

public abstract class OpenAddressHashTable {

    protected Integer[] elements;

    protected int size;
    //Power of two to get to size (size = 2^x)
    protected int x;

    protected int count = 0;
    protected int collisionCounter = 0;

    public OpenAddressHashTable(int size) {
        this.size = findNearestPowerOfTwo(size);
        this.x = (int) (Math.log(this.size) / Math.log(2));

        elements = new Integer[this.size];
    }

    public int getSize() {
        return size;
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

    public int getCount() {
        return count;
    }

    public int getCollisionCounter() {
        return collisionCounter;
    }

    @Override
    public String toString() {
        return "OpenAddressHashTable{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                ", x=" + x +
                ", count=" + count +
                ", collisionCounter=" + collisionCounter +
                '}';
    }
}

class LinearProbeHashTable extends OpenAddressHashTable {

    public LinearProbeHashTable(int size) {
        super(size);
    }

    public int insert(int e) {
        int k = Hash.multhash(e, x);

        for (int i = 0; i < size; i++) {
            int j = probe(k, i);
            if(elements[j] == null) {
                elements[j] = e;
                count++;
                return j;
            } else {
                collisionCounter++;
            }
        }
        return -1;
    }

    @Override
    public int findPos(int e) {
        return 0;
    }

    public int probe(int k, int i) {
        return (k + i) % size;
    }

}

class SquaredProbeHashTable extends OpenAddressHashTable {

    public SquaredProbeHashTable(int size) {
        super(size);
    }

    public int insert(int e) {
        int k = Hash.multhash(e, x);

        for (int i = 0; i < size; i++) {
            int j = probe(k, i);

            if(elements[j] == null) {
                elements[j] = e;
                count++;
                return j;
            } else {
                collisionCounter++;
            }
        }
        return -1;
    }

    @Override
    public int findPos(int e) {
        return 0;
    }

    private int probe(int k, int i) {
        return (int) ((k + (i + (long) i*i)/2L) % (long) size);
    }

}

class DoubleHashProbeHashTable extends OpenAddressHashTable {

    public DoubleHashProbeHashTable(int size) {
        super(size);
    }

    @Override
    public int insert(int e) {
        int j = Hash.divhash(e, size);
        int k_2 = 0;

        for (int i = 0; i < size; i++) {
            j = (j + k_2) % size;
            if(elements[j] == null) {
                elements[j] = e;
                count++;
                return j;
            } else if (k_2 == 0){
                k_2 = Hash.divhash((2 * e) + 1, size);
                collisionCounter++;
            } else {
                collisionCounter++;
            }
        }
        return -1;
    }

    @Override
    public int findPos(int e) {
        return 0;
    }
}
