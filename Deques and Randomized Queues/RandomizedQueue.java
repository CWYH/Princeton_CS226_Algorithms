/**
 * Created by cwyh on 4/3/17.
 */

/**
 *  A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random
 *  from items in the data structure.
 */

/**
 * Corner cases. The order of two or more iterators to the same randomized queue must be mutually independent;
 * each iterator must maintain its own random order. Throw a java.lang.NullPointerException if the client attempts
 * to add a null item; throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item
 * from an empty randomized queue; throw a java.lang.UnsupportedOperationException if the client calls the remove()
 * method in the iterator; throw a java.util.NoSuchElementException if the client calls the next() method in the
 * iterator and there are no more items to return.
 */

/**
 * Performance requirements. Your randomized queue implementation must support each randomized queue operation
 * (besides creating an iterator) in constant amortized time. That is, any sequence of m randomized queue operations
 * (starting from an empty queue) should take at most cm steps in the worst case, for some constant c. A randomized
 * queue containing n items must use at most 48n + 192 bytes of memory. Additionally, your iterator implementation
 * must support operations next() and hasNext() in constant worst-case time; and construction in linear time; you may
 * (and will need to) use a linear amount of extra memory per iterator.
 */


import java.util.*;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N;
    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        N = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        Node Oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else          Oldlast.next = last;
        N++;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        Item item = first.item;
        int id = StdRandom.uniform(N);
        Node node = first;

        if ( id == 0 ) { // remove first
            item = first.item;
            first = first.next;
        } else {
            for(int i = 0; i != N; i++){
                if( i == id-1 && id != N-1){
                    item = node.next.item;
                    node.next = node.next.next;
                    break;
                } else if (i == id-1 && id == N-1) {
                    item = last.item;
                    last = node;
                    last.next = null;
                    break;
                }
                node = node.next;
            }
            node = first; // 跳出循环后回到初始状态
        }

        N--;
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if(isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        Item item;
        int id = StdRandom.uniform(N);
        int i = 0;
        Node node = first;
        while ( i != id ) {
            i ++;
            node = node.next;
        }
        item = node.item;
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }

}

