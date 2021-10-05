package no.ntnu.idi.krisvaa.idatt2101;

public class DoubleHashProbeHashTable extends OpenAddressHashTable {

    public DoubleHashProbeHashTable(int size) {
        super(size);
    }

    @Override
    public int insert(int e) {
        int k_1 = Hash.divhash(e, size);
        int k_2 = Hash.divhash(e, size - 1) + 1;

        for (int i = 0; i < size; i++) {
            int j = probe(k_1, k_2, i);
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


    public int probe(int k_1, int k_2, int i) {
        return (k_1 + i * k_2) % size;
    }
}
