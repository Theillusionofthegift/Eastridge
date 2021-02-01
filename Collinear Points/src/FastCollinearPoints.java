import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;


public class FastCollinearPoints {

    private LineSegment[] segments;
    private int segmentCount;


    public FastCollinearPoints(Point[] point) {

        // check to see if argument matches constraints
        checksPoints(point);

        Point[] points = point.clone();
        segments = new LineSegment[1];
        segmentCount = 0;
        LinkedList<Point> collinearPoints = new LinkedList<Point>();
        double prevSlope = 0.0;

        // check to see if argument matches constraints
        for (Point p1 : points) {

            Arrays.sort(points, p1.slopeOrder());

            for (Point p2 : points) {
                double currentSlope = p1.slopeTo(p2);
                collinearPoints.add(p1);
                if (currentSlope == prevSlope) {
                    collinearPoints.add(p2);
                } else if (collinearPoints.size() >= 3) {
                    add(new LineSegment(collinearPoints.getFirst(), collinearPoints.getLast()));
                } else {
                    collinearPoints.clear();
                }

                prevSlope = currentSlope;

            }
        }

    }

    public int numberOfSegments() {
        return this.segmentCount;
    }


    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segmentCount);
    }


    private void resize(int capacity) {
        assert capacity >= this.segmentCount;

        // textbook implementation
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(this.segments, 0, temp, 0, this.segmentCount);
        this.segments = temp;

    }


    private void add(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (segmentCount == segments.length) {
            resize(2 * segments.length);
        }

        boolean isDupe = false;
        for (LineSegment l : segments) {
            if (l == item) {
                isDupe = true;
                break;
            }
        }

        if (!isDupe) {
            segments[segmentCount++] = item;
        }

    }


    private void checksPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {

                if (points[i] == null || points[j] == null) {
                    throw new IllegalArgumentException();
                }

                if (i != j && points[i].compareTo(points[j]) == 0) {
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
