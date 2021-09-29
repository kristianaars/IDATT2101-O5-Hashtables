package no.ntnu.idi.krisvaa.idatt2101;

import com.sun.jdi.InvalidTypeException;

import java.util.Arrays;
import java.util.LinkedList;

public class HashTable {

    private LinkedList<String>[] elements;
    private int size;
    private int x;
    private int count = 0;

    private int collisionCounter = 0;

    public HashTable(int size) {
        this.size = findNearestPowerOfTwo(size);
        this.x = (int) (Math.log(this.size) / Math.log(2));
        elements = new LinkedList[this.size];

        for(int i = 0; i < this.size; i++) { elements[i] = new LinkedList<>(); };
    }

    private int findNearestPowerOfTwo(int n) {
        int power = 2;
        while (power < n) {
            power = power << 1;
        }

        return power;
    }

    public int insert(String s) {
        int k = Hash.multhash(s, x);

        boolean collision = !elements[k].isEmpty();
        if (collision) collisionCounter++;

        elements[k].addLast(s);
        count++;

        return k;
    }

    public int delete() {
        return -1;
    }

    public Object find(int k) {
        return elements[k];
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
