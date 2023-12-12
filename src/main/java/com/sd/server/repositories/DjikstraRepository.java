package com.sd.server.repositories;

import com.sd.server.DAO.PointDAO;
import com.sd.server.DAO.SegmentDAO;
import com.sd.server.Exceptions.NoRouteException;
import com.sd.server.Models.Point;
import com.sd.server.Models.RouteSegment;
import com.sd.server.Models.Segment;

import java.util.*;

public class DjikstraRepository{
    private List<Point> points;
    private List<Segment> segments;

     static PointDAO pointDAO = new PointDAO();
     static SegmentDAO segmentDAO = new SegmentDAO();

    public DjikstraRepository(List<Point> points, List<Segment> segments) {
        this.points = points;
        this.segments = segments;
    }

    public List<Segment> findShortestPath(Point source, Point destination) throws NoRouteException {
        Map<Point, Integer> distanceMap = new HashMap<>();
        Map<Point, Boolean> visitedMap = new HashMap<>();
        Map<Point, Segment> parentMap = new HashMap<>();

        for (Point point : points) {
            distanceMap.put(point, Integer.MAX_VALUE);
            visitedMap.put(point, false);
        }
        distanceMap.put(source, 0);

        for (int i = 0; i < points.size() - 1; i++) {
            Point currentPoint = getMinDistancePoint(distanceMap, visitedMap);
            visitedMap.put(currentPoint, true);

            for (Segment segment : getAdjacentSegments(currentPoint)) {
                Point neighbor = segment.getDestination();
                int newDistance = distanceMap.get(currentPoint) + segment.getDistance();

                if (!visitedMap.get(neighbor) && newDistance < distanceMap.get(neighbor)) {
                    distanceMap.put(neighbor, newDistance);
                    parentMap.put(neighbor, segment);
                }
            }
        }

        return buildPathSegments(parentMap, source, destination);
    }

    private List<Segment> buildPathSegments(Map<Point, Segment> parentMap, Point source, Point destination) throws NoRouteException {
        List<Segment> pathSegments = new ArrayList<>();
        Point current = destination;

        while (current != null && !current.equals(source)) {
            Segment segment = parentMap.get(current);
            if(segment ==null){
                throw new NoRouteException();
            }
            pathSegments.add(segment);
            current = segment.getOrigin();
        }

        Collections.reverse(pathSegments);
        return pathSegments;
    }

    private Point getMinDistancePoint(Map<Point, Integer> distanceMap, Map<Point, Boolean> visitedMap) {
        int minDistance = Integer.MAX_VALUE;
        Point minDistancePoint = null;

        for (Point point : points) {
            int distance = distanceMap.get(point);
            if (!visitedMap.get(point) && distance < minDistance) {
                minDistance = distance;
                minDistancePoint = point;
            }
        }

        return minDistancePoint;
    }

    private List<Segment> getAdjacentSegments(Point point) {
        List<Segment> adjacentSegments = new ArrayList<>();
        for (Segment segment : segments) {
            if (segment.getOrigin().equals(point)) {
                adjacentSegments.add(segment);
            }
        }
        return adjacentSegments;
    }

    public static List<RouteSegment> findPath(Point sourcePoint, Point destinationPoint) throws NoRouteException {
        // Create sample points and segments

        List<Point> points = pointDAO.getAllPoints();
        List<Segment> segments = segmentDAO.getAllUnblockedSegments();

        DjikstraRepository dijkstra = new DjikstraRepository(points, segments);

        List<RouteSegment> routeSegments = new ArrayList<>();

        for(Segment segment : dijkstra.findShortestPath(sourcePoint, destinationPoint)){
            routeSegments.add(new RouteSegment(segment));
        }

        return routeSegments;
    }
}
