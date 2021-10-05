package no.ntnu.idi.krisvaa.idatt2101;

public class SquaredProbeHashTable extends OpenAddressHashTable {

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
        System.out.println(i);
        return (k + (i + i*i)/2) % size;
    }

}
