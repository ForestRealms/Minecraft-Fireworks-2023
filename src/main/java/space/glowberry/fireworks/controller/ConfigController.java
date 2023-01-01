package space.glowberry.fireworks.controller;

import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import space.glowberry.fireworks.classes.FireworkProperty;
import space.glowberry.fireworks.classes.Point;
import space.glowberry.fireworks.utils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ConfigController {
    private static final YamlConfiguration config = utils.config;

    /**
     * Get the color (or fade color) list in terms of the name of a fireworks point
     * @param PointName The name of the fireworks point
     * @param fade If the fade color is expected to be obtained
     * @return The color list
     */
    private static List<Color> getColorsOf(String PointName, boolean fade){
        List<Color> result = new ArrayList<>();
        ConfigurationSection ColorSection;
        if(!fade){
            ColorSection = config.getConfigurationSection("points." + PointName + ".colors");
        }else{
            ColorSection = config.getConfigurationSection("points." + PointName + ".fade_colors");
        }
        assert ColorSection != null;
        for (String colorName : ColorSection.getKeys(false)) {
            List<Integer> RGB = ColorSection.getIntegerList(colorName);
            result.add(Color.fromRGB(RGB.get(0), RGB.get(1), RGB.get(2)));
        }
        return result;
    }

    public static List<Point> getAllPoints(){
        List<Point> result = new ArrayList<>();
        Set<String> PointNames = Objects.requireNonNull(config.getConfigurationSection("points")).getKeys(false);
        for (String pointName : PointNames) {
            List<Double> coordinates = config.getDoubleList("points." + pointName + ".coordinates");
            String worldName = config.getString("points." + pointName + ".world");
            assert worldName != null;
            // Get the location of the fireworks point
            Location location = new Location(Bukkit.getWorld(worldName), coordinates.get(0), coordinates.get(1), coordinates.get(2));
            // Get the colors and fade_colors of the fireworks point
            List<Color> colors = getColorsOf(pointName, false);
            List<Color> fadeColors = getColorsOf(pointName, true);
            // Get other properties of the fireworks point
            boolean hasTrail = config.getBoolean("points." + pointName + ".trail");
            boolean hasFlicker = config.getBoolean("points." + pointName + ".flicker");
            int power = config.getInt("points." + pointName + ".power");
            String shape_string = config.getString("points." + pointName + ".shape");
            FireworkEffect.Type type = FireworkEffect.Type.valueOf(shape_string);

            // Construct
            FireworkProperty property = new FireworkProperty();
            property.setColors(colors);
            property.setFadeColors(fadeColors);
            property.setPower(power);
            property.setTrail(hasTrail);
            property.setFlicker(hasFlicker);
            property.setType(type);
            Point point = new Point(pointName, location);
            point.setProperty(property);

            // Add the point to the list
            result.add(point);
        }
        // Return the result
        return result;
    }
}
