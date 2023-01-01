package space.glowberry.fireworks.classes;

import org.bukkit.Bukkit;
import space.glowberry.fireworks.Main;

import java.util.List;

public class Loop {
    private final List<Point> points;
    private final long interval;
    private LoopState loopState;

    private int taskId;

    public Loop(List<Point> points, int interval) {
        this.points = points;
        this.interval = interval;
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

}

