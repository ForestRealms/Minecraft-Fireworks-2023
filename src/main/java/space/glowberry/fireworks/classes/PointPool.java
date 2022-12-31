package space.glowberry.fireworks.classes;

import java.util.List;

public class PointPool extends Pool<Point>{

    private List<Point> points;
    private static final PointPool instance = new PointPool();

    private PointPool() {}

    @Override
    public void add(Point point) {
        points.add(point);
    }

    @Override
    public void remove(Point point) {
        points.remove(point);
    }

    @Override
    public List<Point> getAll() {
        return points;
    }

    public static PointPool getInstance() {
        return instance;
    }
}
