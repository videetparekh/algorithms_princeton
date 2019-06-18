import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        Iterator<String> it = rq.iterator();
        while (it.hasNext() && k != 0) {
            String str = it.next();
            System.out.println(str);
            k--;
        }
    }
}
