import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {


    private final Point[] points;
    private ArrayList<LineSegment> lineSegments;
    private int segmentCount;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkNull(points);
        this.points = points;
        this.segmentCount = 0;
        lineSegments = new ArrayList<>();

        findSegments();
    }

    private void checkNull(Point[] points) {
        if (points == null || points.length == 0) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i+1]) == 0) throw new IllegalArgumentException();
        }
    }

    private void findSegments() {

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        checkDuplicatedEntries(pointsCopy);

        for (int i=0; i<points.length-3;i++) {
            for (int j=i+1; j<points.length-2; j++) {
                for (int k=j+1;k<points.length-1; k++) {
                    for (int l=k+1; l<points.length;l++) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[k]) &&
                                pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[l])) {
                            lineSegments.add(new LineSegment(pointsCopy[i], pointsCopy[l]));
                            segmentCount++;
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segmentCount;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numberOfSegments()]);
    }

    public static void main(String[] args) {


        Point[] points = new Point[16];
        points[0] = new Point(0,0);
        points[1] = new Point(2000,2000);
        points[2] = new Point(5000,5000);
        points[3] = new Point(1000,1000);
        points[4] = new Point(1000,3000);
        points[5] = new Point(0,3000);
        points[6] = new Point(1500,2500);
        points[7] = new Point(300,500);
        points[8] = new Point(400,600);
        points[9] = new Point(500,700);
        points[10] = new Point(200,400);
        points[11] = new Point(50,1000);
        points[12] = new Point(700,400);
        points[13] = new Point(80,80);
        points[14] = new Point(6000,2000);
        points[15] = new Point(700,1500);

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