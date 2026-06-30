package collinear_points;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pointsCopy = new Point[points.length];
        for(int i = 0; i < points.length; i++){
            if (points[i] == null) throw new IllegalArgumentException();
            pointsCopy[i] = points[i];
        }
        checkNoDuplicates(pointsCopy);
        calculateSegments(pointsCopy);
    }

    private void checkNoDuplicates(Point[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                Point A = array[i];
                Point B = array[j];
                if (A.compareTo(B) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private void compareFourPoints(int i, int j, int k, int l, Point[] points) {
        Point A = points[i];
        Point B = points[j];
        Point C = points[k];
        Point D = points[l];
        double slopeAtoB = A.slopeTo(B);
        double slopeAtoC = A.slopeTo(C);
        double slopeAtoD = A.slopeTo(D);
        if (slopeAtoB == slopeAtoC && slopeAtoC == slopeAtoD) {
            Point[] collinearPoints = {A, B, C, D};
            Arrays.sort(collinearPoints);
            LineSegment newSegment = new LineSegment(collinearPoints[0], collinearPoints[3]);
            segments.add(newSegment);
        }

    }

    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[0]);
    }

    private void calculateSegments(Point[] points) {
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        compareFourPoints(i, j, k, l, points);
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments().length;
    }
}
