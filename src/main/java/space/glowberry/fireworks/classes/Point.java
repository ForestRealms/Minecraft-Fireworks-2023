package space.glowberry.fireworks.classes;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;

import java.util.Objects;

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
    private FireworkProperty property;

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

    public Point(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public void setProperty(FireworkProperty property) {
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

    /**
     * Launch the point
     */
    public void launch(){
        Entity entity = Objects.requireNonNull(this.location.getWorld()).spawnEntity(location, EntityType.FIREWORK);
        Firework firework = (Firework) entity;
        firework.setFireworkMeta(this.property.build(firework.getFireworkMeta()));
    }
}
