package space.glowberry.fireworks.classes;

import java.util.List;

public class Loop {
    private final List<Point> points;
    private final int interval;
    private LoopState loopState;

    public Loop(List<Point> points, int interval) {
        this.points = points;
        this.interval = interval;
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getInterval() {
        return interval;
    }

    public LoopState getLoopState() {
        return loopState;
    }

    public void setLoopState(LoopState loopState) {
        this.loopState = loopState;
    }
}

enum LoopState{
    STOPPED,
    WORKING
}