package space.glowberry.fireworks.classes;

import space.glowberry.fireworks.Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoopPool extends Pool<Loop>{

    private List<Loop> loops = new ArrayList<>();
    private static final LoopPool instance = new LoopPool();

    private LoopPool() {
    }

    public void ReInitialize(){
        for (Loop loop : this.loops) {
            loop.setLoopState(LoopState.STOPPED);
        }
        this.loops.clear();
    }


    /**
     * Obtain a loop by name
     * @param loopName The name of a loop
     * @return Null if the loop not exist.
     */
    public Loop getLoop(String loopName){
        for (Loop loop : this.loops) {
            if (loop.getName().equals(loopName)){
                return loop;
            }
        }
        return null;
    }

    public List<String> getNameList(){
        List<String> result = new ArrayList<>();
        for (Loop loop : this.loops) {
            result.add(loop.getName());
        }
        return result;
    }

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

    public void saveAllLoops() throws IOException {
        for (Loop loop : this.loops) {
            String loopName = loop.getName();
            List<String> pointNames = new ArrayList<>(loops.size());
            for (Point point : loop.getPoints()) {
                pointNames.add(point.getName());
            }
            Factory.getConfig().set("loops." + loopName + ".points", pointNames);
            Factory.getConfig().set("loops." + loopName + ".interval", loop.getInterval());
            Factory.getConfig().set("loops." + loopName + ".status", loop.getLoopState().toString());
            Factory.saveConfig();
        }
    }

    /**
     * To check if the loop with a specific name is existed
     * @param loopName The name of a loop
     * @return true if the loop exist in the LoopPool
     */
    public boolean LoopIsExist(String loopName){
        for (Loop loop : this.loops) {
            if (loop.getName().equals(loopName)){
                return true;
            }
        }
        return false;
    }

    public static LoopPool getInstance() {
        return instance;
    }
}
