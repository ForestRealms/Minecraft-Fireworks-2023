package space.glowberry.fireworks.classes;

import org.bukkit.Location;

public class Point {
    /**
     * Name of the firework point
     */
    private final String name;

    /**
     * The location of the firework point
     */
    private final Location location;

    /**
     * The specific property of the firework point
     */
    private final FireworkProperty property;

    /**
     * Create a firework point instance
     * @param name Name of the firework point
     * @param location Location of firework point
     * @param property Property of firework point
     */
    public Point(String name, Location location, FireworkProperty property) {
        this.name = name;
        this.location = location;
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public FireworkProperty getProperty() {
        return property;
    }
}
