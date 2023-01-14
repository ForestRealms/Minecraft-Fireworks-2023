package space.glowberry.fireworks.controller;

import org.bukkit.Location;
import space.glowberry.fireworks.classes.FireworkProperty;
import space.glowberry.fireworks.classes.Point;

public class RandomPointGenerator {
    public RandomPointGenerator() {
    }

    public Point generate(String name, Location location){
        Point point = new Point(name, location);
        FireworkProperty property = new FireworkProperty();

        point.setProperty(property);
        return point;
    }
}
