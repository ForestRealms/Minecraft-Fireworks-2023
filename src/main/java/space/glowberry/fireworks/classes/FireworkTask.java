package space.glowberry.fireworks.classes;

import java.util.Iterator;
import java.util.List;

public class FireworkTask implements Runnable {

    private final Loop loop;
    private Iterator<Point> iterator;

    public FireworkTask(Loop loop) {
        this.loop = loop;
        List<Point> points = this.loop.getPoints();
        iterator = points.iterator();
    }

    @Override
    public void run() {
        List<Point> points = this.loop.getPoints();
        Point point;
        if (!iterator.hasNext()) {
            iterator = points.iterator();
        }
        point = iterator.next();
        point.launch();
    }
}
