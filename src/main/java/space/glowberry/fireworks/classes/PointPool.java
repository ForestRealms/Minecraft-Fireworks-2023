package space.glowberry.fireworks.classes;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class PointPool extends Pool<Point>{

    private List<Point> points = new ArrayList<>();
    private static final PointPool instance = new PointPool();

    private PointPool() { }

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

    @Override
    public void addAll(List<Point> points) {
        this.points.addAll(points);
    }

    public static PointPool getInstance() {
        return instance;
    }

    /**
     * Obtain the point object by name
     * @param point_name Name of the point
     * @return Point
     * @since 2.0-Beta
     */
    public Point getPoint(String point_name){
        for (Point point : this.points) {
            if(point.getName().equals(point_name)){
                return point;
            }
        }
        return null;
    }

    public List<Point> getPoints(Collection<String> point_names){
        List<Point> p = new ArrayList<>();
        for (String pointName : point_names) {
            if(PointIsExist(pointName)){
                p.add(getPoint(pointName));
            }
        }
        return p;
    }

    public List<Point> getPoints(Collection<String> point_names, Class<? extends List> return_type) throws InstantiationException, IllegalAccessException {
        List<Point> p = return_type.newInstance();
        for (String pointName : point_names) {
            if(PointIsExist(pointName)){
                p.add(getPoint(pointName));
            }
        }
        return p;
    }


    public boolean PointIsExist(String PointName){
        for (Point point : getInstance().getAll()) {
            if(point.getName().equals(PointName)){
                return true;
            }
        }
        return false;
    }

    public List<String> getNameList(){
        List<String> result = new ArrayList<>();
        for (Point point : getAll()) {
            result.add(point.getName());
        }
        return result;
    }

    public void ReInitialize() {
        this.points = new ArrayList<>();
    }
}
