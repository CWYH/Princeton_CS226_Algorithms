/**
 * Created by cwyh on 4/10/17.
 */

// Brute force. Write a program BruteCollinearPoints.java that examines 4 points at a time and checks
// whether they all lie on the same line segment, returning all such line segments. To check whether
// the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between
// p and r, and between p and s are all equal.

import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.*;

// import javax.swing.text.Segment;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        Point p,q,r,s;
        int N = points.length;
        for (int i = 0; i != N-3; i++) {
            p = points[i];
            for (int k = i + 1; k != N-2 && k > i; k++) {
                q = points[k];
                for (int j = k + 1; j != N-1 && j > k; j++) {
                    r = points[j];
                    for (int l = j + 1; l != N && l > j; l++) {
                        s = points[l];
                        double Slopepq = p.slopeTo(q);
                        double Slopeqr = q.slopeTo(r);
                        double Slopers = r.slopeTo(s);
                        if (Slopepq == Slopeqr && Slopeqr == Slopers)
                            foundSegments.add(new LineSegment(p,s));
                    }
                }
            }
        }
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        //StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
