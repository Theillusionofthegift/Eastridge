import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// import statements
public class BruteCollinearPoints {

    private LineSegment[] segList;
    private int cnt = 0;

    // find all the lines containing 2 points
    public BruteCollinearPoints(Point[] point) {

        checkPoint(point);
        Point[] p = point.clone();

        this.segList = new LineSegment[1];

        // sort Array
        Arrays.sort(p);
        int s = p.length;

        // search through points array looking for line segments
        for (int i = 0; i < s - 3; i++) {
            for (int j = i + 1; j < s - 2; j++) {
                for (int k = j + 1; k < s - 1; k++) {
                    for (int l = k + 1; l < s; l++) {
                        if (p[i].slopeTo(p[j]) == p[j].slopeTo(p[k]) && p[j].slopeTo(p[k]) == p[k].slopeTo(p[l])) {
                            add(new LineSegment(p[i], p[l]));
                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        //returns the size of the Segment List array
        return cnt;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segList, cnt);
    }

    private void add(LineSegment l) {

        if (cnt == segList.length) {
            resize(2 * segList.length);
        }

        if (l == null) {
            throw new java.lang.IllegalArgumentException();
        } else {
            segList[cnt++] = l;
        }
    }


    private void resize(int capacity) {
        assert capacity >= cnt;

        // textbook implementation
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(segList, 0, temp, 0, cnt);
        segList = temp;
    }

    private void checkPoint(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int k = 0; k < i; k++) {
                if (points[k].compareTo(points[i]) == 0) {
                    throw new IllegalArgumentException();
                }
            }


        }
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
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
