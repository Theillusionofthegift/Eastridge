import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;


public class FastCollinearPoints {

    private LineSegment[] segments;
    private int segmentCount;


    public FastCollinearPoints(Point[] point) {

        // check to see if argument matches constraints
        checkPoint(point);

        segments = new LineSegment[1];
        segmentCount = 0;
        Point[] collinearPoints = new Point[point.length];
        double prevSlope;

        // check to see if argument matches constraints
        for (Point p1 : point) {

            Point[] sort = point.clone();
            Arrays.sort(sort, p1.slopeOrder());

            prevSlope = -0.0;
            int cnt = 0;

            for (int i = 0; i < sort.length; i++) {
                collinearPoints[cnt++] = p1;
                double currentSlope = p1.slopeTo(sort[i]);
                if (currentSlope == prevSlope) {
                    collinearPoints[cnt++] = sort[i];
                }

                if (cnt >= 3) {
                    Arrays.sort(collinearPoints, 0, cnt);
                    add(new LineSegment(collinearPoints[0], collinearPoints[cnt - 1]));
                    break;
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
        assert capacity >= segmentCount;

        // textbook implementation
        LineSegment[] aux = new LineSegment[capacity];
        System.arraycopy(segments, 0, aux, 0, segmentCount);
        segments = aux;

    }


    private void add(LineSegment s) {

        if (segmentCount == segments.length) {
            resize(2 * segments.length);
        }
        boolean isDupe = true;
        for (int i = 0; i < segmentCount; i++) {
            if (segments[i].equals(s)) {
                isDupe = true;
                break;
            } else {
                isDupe = false;
            }
        }

        if (!isDupe) {
            segments[segmentCount++] = s;
        }


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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
