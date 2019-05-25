package week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints {
    //to store line segments
    private final ArrayList<LineSegment> LineSegmentList;
    //to store the given points
    private final Point[] points;

    //在构造函数中找出由4点构成的线段(作业说了:没有5点及更多点共线的情况)
    // finds all line segments containing 4 point
    public BruteCollinearPoints(Point[] pointsIn) {

        //1. if the argument to the constructor is null
        if (pointsIn == null) {
            throw new IllegalArgumentException("there is no point");
        }

        //2. if any point in the array is null
        int N = pointsIn.length;
        for (int i = 0; i < N; i++) {
            if (pointsIn[i] == null) throw new IllegalArgumentException("exist null point");
        }
        //3. :if the argument to the constructor contains a repeated point
        //检查是否有重复的点,先排序,再查重会方便,作业允许这样: For example, you may use Arrays.sort()
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = pointsIn[i];
        }

        Arrays.sort(points);

        for (int i = 1; i < N; i++) {
            if (points[i - 1].compareTo(points[i]) == 0) {
                throw new IllegalArgumentException("exist repeated point");
            }
        }

        //to save every required line segment
        LineSegmentList = new ArrayList<>();

        //find line segment
        for (int dot1 = 0; dot1 <= N - 4; dot1++) {
            for (int dot2 = dot1 + 1; dot2 <= N - 3; dot2++) {
                //k12:the slope between point[dot2] and point[dot1]
                double k12 = points[dot2].slopeTo(points[dot1]);
                for (int dot3 = dot2 + 1; dot3 <= N - 2; dot3++) {
                    //k13:the slope between point[dot3] and point[dot1]
                    double k13 = points[dot3].slopeTo(points[dot1]);
                    if (k13 != k12) continue;
                    for (int dot4 = dot3 + 1; dot4 <= N - 1; dot4++) {
                        //k14:the slope between point[dot4] and point[dot1]
                        double k14 = points[dot4].slopeTo(points[dot1]);
                        if (k14 != k12) continue;
                        //find a line segment
                        LineSegment linesegment = new LineSegment(points[dot1], points[dot4]);
                        LineSegmentList.add(linesegment);
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return LineSegmentList.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[LineSegmentList.size()];
        int index = 0;
        for (LineSegment Line : LineSegmentList) {
            segments[index++] = Line;
        }
        return segments;
    }
}
