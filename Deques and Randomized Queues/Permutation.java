/**
 * Created by cwyh on 4/3/17.
 */

/**
 * Write a client program Permutation.java that takes a command-line integer k; reads in a sequence of strings
 * from standard input using StdIn.readString(); and prints exactly k of them, uniformly at random. Print each
 * item from the sequence at most once. You may assume that 0 ≤ k ≤ n, where n is the number of string on standard
 * input.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation { // 排列
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> RdQ = new RandomizedQueue<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            RdQ.enqueue(item);
        }
        for (int i=0; i!=k ; i++ ) {
            String s = RdQ.dequeue();
            StdOut.println(s);
        }
    }
}