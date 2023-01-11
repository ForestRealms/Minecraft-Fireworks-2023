package space.glowberry.fireworks.classes;

import org.bukkit.Bukkit;
import space.glowberry.fireworks.Exceptions.ZeroIntervalError;
import space.glowberry.fireworks.Main;

import java.util.ArrayList;
import java.util.List;

public class Loop {
    private final List<Point> points;

    /**
     * The interval between each point. Unit: ticks
     */
    private long interval;
    private LoopState loopState;
    private String name;

    private int taskId;

    public Loop(String name, List<Point> points, int interval) throws NullPointerException, IllegalArgumentException, ZeroIntervalError {
        if(name == null){
            throw new NullPointerException("Name of the loop is NULL");
        }
        if(points == null){
            throw new NullPointerException("Points of the loop \"" + name + " \"is NULL");
        }
        if(interval <= 0){
            throw new ZeroIntervalError("The interval of loop \"" + name + "\" cannot be 0");
        }
        this.name = name;
        this.points = points;
        this.interval = interval;
        this.loopState = LoopState.STOPPED;
    }

    public Loop() {
        this.points = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public long getInterval() {
        return interval;
    }

    public LoopState getLoopState() {
        return loopState;
    }

    public void setLoopState(LoopState loopState) {
        if(loopState == LoopState.WORKING){
            this.taskId = Bukkit.getScheduler().runTaskTimer(Main.plugin, new FireworkTask(this), 0L, this.interval).getTaskId();
        }else{
            Bukkit.getScheduler().cancelTask(this.taskId);
        }

        this.loopState = loopState;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void addPoint(Point point){
        this.points.add(point);
    }

    @Override
    public String toString() {
        return "Loop{" +
                "points=" + points +
                ", interval=" + interval +
                ", loopState=" + loopState +
                ", taskId=" + taskId +
                '}';
    }
}

