package space.glowberry.fireworks.classes;

import java.util.List;

public abstract class Pool<T> {
    public abstract void add(T t);
    public abstract void remove(T t);
    public abstract List<T> getAll();
}
