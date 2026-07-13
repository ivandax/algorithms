package org.collinear;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> segments = new ArrayList<>();
    private final ArrayList<Double> slopes = new ArrayList<>();
    private final ArrayList<Integer> streaks = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
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

    private void getLineSegmentOnStreak(double slope, Point point, int streak, int from, Point[] sortedPoints) {
        int slopeAlreadyProcessedIndex = slopes.indexOf(slope);
        boolean slopeAlreadyProcessed = slopeAlreadyProcessedIndex >= 0;
        int maxStreak = slopeAlreadyProcessed ? streaks.get(slopeAlreadyProcessedIndex) : 0;
        if (slopeAlreadyProcessed && streak < maxStreak) {
            return;
        }
        Point[] completeArray = new Point[streak + 2];
        completeArray[0] = point;
        int count = 1;
        for (int i = from; i < from + streak + 1; i++) {
            completeArray[count] = sortedPoints[i];
            count++;
        }
        Arrays.sort(completeArray);
        LineSegment newSegment = new LineSegment(completeArray[0], completeArray[completeArray.length - 1]);
        if (!slopeAlreadyProcessed) {
            this.segments.add(newSegment);
            this.slopes.add(slope);
            this.streaks.add(streak);
        } else {
            this.segments.set(slopeAlreadyProcessedIndex, newSegment);
            this.slopes.set(slopeAlreadyProcessedIndex, slope);
            this.streaks.set(slopeAlreadyProcessedIndex, streak);
        }

    }

    private void compareOtherPointsToPoint(Point point, Point[] others) {
        Arrays.sort(others, point.slopeOrder());
        int from = 0;
        int streak = 0;
        double firstSlope = others[0].slopeTo(point);
        for (int i = 1; i < others.length - 1; i++) {
            double currentSlope = others[i].slopeTo(point);
            if (currentSlope == firstSlope) {
                streak++;
            } else {
                // streak breaks
                if (streak >= 2) {
                    getLineSegmentOnStreak(firstSlope, point, streak, from, others);
                }
                streak = 0;
                from = i;
                firstSlope = currentSlope;
            }
        }
    }

    private Point[] makeCopy(int excludeIndex, Point[] points) {
        Point[] copy = new Point[points.length - 1];
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            if (i == excludeIndex) continue;
            copy[count] = points[i];
            count++;
        }
        return copy;
    }

    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[0]);
    }

    private void calculateSegments(Point[] points) {

        for (int i = 0; i < points.length - 1; i++) {
            Point[] currentCopy = makeCopy(i, points);
            Point currentPoint = points[i];
            compareOtherPointsToPoint(currentPoint, currentCopy);
        }
    }

    public int numberOfSegments() {
        return segments().length;
    }
}
