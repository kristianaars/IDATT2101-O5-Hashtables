import java.util.Iterator;
import java.util.LinkedList;

public class HashTable {

    private LinkedList<String>[] elements;
    private int size;

    //Power of two to get to size (size = 2^x)
    private int x;

    private int count = 0;
    private int collisionCounter = 0;

    /**
     * Hash tabel for task 1
     * @param size Size of table, will be converted to closest 2^x
     */
    public HashTable(int size) {
        this.size = findNearestPowerOfTwo(size);
        this.x = (int) (Math.log(this.size) / Math.log(2));
        elements = new LinkedList[this.size];

        for(int i = 0; i < this.size; i++) { elements[i] = new LinkedList<>(); };
    }

    public int insert(String s) {
        int k = Hash.multhash(s, x);

        boolean collision = !elements[k].isEmpty();
        if (collision) collisionCounter++;

        elements[k].addLast(s);
        count++;

        return k;
    }

    private int findNearestPowerOfTwo(int n) {
        int power = 2;
        while (power < n) {
            power = power << 1;
        }

        return power;
    }

    public float getLoadFactor() {
        return (float) count / (float) size;
    }

    public int getCollisionCounter() {
        return collisionCounter;
    }

    public int getCount() {
        return count;
    }

    public String find(String s) {
        int k = Hash.multhash(s, x);
        LinkedList<String> l = elements[k];

        Iterator<String> it = l.iterator();
        while (it.hasNext()) {
            String sl = it.next();
            if(sl.equals(s)) return sl;
        }

        return null;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "size=" + size +
                ", x=" + x +
                ", count=" + count +
                ", collisions=" + collisionCounter +
                ", elements=" + toFormattedTable(true) +
                "\n}";
    }

    public String toFormattedTable(boolean showEmpty) {
        String s = "";
        for (int i = 0; i < size; i++) {
            LinkedList<String> l = elements[i];

            if(showEmpty || !l.isEmpty()) {
                s += "\n["+i+"]= ";
                for(String e : l) {
                    s += ""+e+"; ";
                }
            }
        }
        return s;
    }

}
