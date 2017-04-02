/**
 * Created by user on 2017/4/2.
 */

/**
 * Dequeue. A double-ended queue or deque(pronounced "deck") is a generalization of a stack and a queue
 *         that supports adding and removing items from either the front or the back of the data structure.
 */

/**
 * Corner cases. Throw a java.lang.NullPointerException if the client attempts to add a null item; throw a
 * java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; throw
 * a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator; throw
 * a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no
 * more items to return.
 */

import java.util.*;

public class Deque<Item> implements Iterable<Item> {

    private Node first;// 头部
    private Node last;// 尾部
    private int N;// 记录元素数目 the number of items

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item==null) throw new NullPointerException("Null pointer is not legal");
        Node OldFirst = first;
        first = new Node();
        first.item = item;
        first.next = OldFirst;
        if(isEmpty())
            last = first;
        N++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if(item==null) throw new NullPointerException("Null pointer is not legal");
        Node Oldlast = last;
        last = new Node(); // important
        last.item = item;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            Oldlast.next = last;

        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        for (Node i = first; i!=null;i = i.next) {
            if (i.next == last){
                last = i;
                last.next = null;
                break;
            }
        }
        N--;
        if(isEmpty()) first = null;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext() { return first != null; }
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
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                deque.addFirst(item);
            }
            else if (!deque.isEmpty()) {
                StdOut.print(deque.removeLast() + " ");
                StdOut.println("(" + deque.size() + " left on deque )");
            }
        }
        StdOut.println("(" + deque.size() + " left on queue)");
    }
}
