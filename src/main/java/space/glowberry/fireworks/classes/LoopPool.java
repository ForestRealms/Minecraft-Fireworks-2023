package space.glowberry.fireworks.classes;

import java.util.List;

public class LoopPool extends Pool<Loop>{

    private List<Loop> loops;
    private static final LoopPool instance = new LoopPool();

    @Override
    public void add(Loop loop) {
        this.loops.add(loop);
    }

    @Override
    public void remove(Loop loop) {
        this.loops.remove(loop);
    }

    @Override
    public List<Loop> getAll() {
        return this.loops;
    }

    @Override
    public void addAll(List<Loop> loops) {
        this.loops.addAll(loops);
    }

    public static LoopPool getInstance() {
        return instance;
    }
}
