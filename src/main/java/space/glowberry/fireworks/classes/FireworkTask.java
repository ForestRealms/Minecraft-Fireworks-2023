package space.glowberry.fireworks.classes;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Iterator;
import java.util.List;

public class FireworkTask implements Runnable {

    private final Loop loop;

    public FireworkTask(Loop loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        List<Point> points = this.loop.getPoints();
        Iterator<Point> iterator = points.iterator();
        Point point;
        if (!iterator.hasNext()) {
            iterator = points.iterator();
        }
        point = iterator.next();
        point.launch();
    }
}
