package org.sorting;

import java.util.Arrays;
import java.util.Stack;
import edu.princeton.cs.algs4.Point2D;

public class ConvexHull {
    private Stack<Point2D> hull = new Stack<>();

    public ConvexHull(Point2D[] points) {
        if (points.length <= 1) return;

        // Sort by y-coordinate, breaking ties by x-coordinate
        Arrays.sort(points, Point2D.Y_ORDER);
        Point2D origin = points[0];

        // Sort the remaining points by polar angle with respect to origin
        Arrays.sort(points, 1, points.length, origin.polarOrder());

        hull.push(origin);     // p0 is always on hull

        int k1;
        for (k1 = 1; k1 < points.length; k1++) {
            if (!origin.equals(points[k1])) break;
        }
        if (k1 == points.length) return; // all points equal

        int k2;
        for (k2 = k1 + 1; k2 < points.length; k2++) {
            if (Point2D.ccw(origin, points[k1], points[k2]) != 0) break;
        }

        hull.push(points[k2 - 1]); // First non-collinear point

        for (int i = k2; i < points.length; i++) {
            Point2D top = hull.pop();
            while (!hull.isEmpty() && Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }
    }

    public Iterable<Point2D> hull() {
        return hull;
    }
}