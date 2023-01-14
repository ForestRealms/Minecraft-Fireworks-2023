package space.glowberry.fireworks.controller;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import space.glowberry.fireworks.Exceptions.*;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.classes.LoopState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigChecker {

    private final YamlConfiguration configuration;
    private final YamlConfiguration language;

    public ConfigChecker(YamlConfiguration configuration) {
        this.configuration = configuration;
        this.language = Factory.getLanguage();
    }


    /**
     * Check whether the point configuration in config.yml is valid
     */
    public List<InvalidConfigurationFile> checkPoint() {
        List<InvalidConfigurationFile> exceptions = new ArrayList<>();
        ConfigurationSection points = this.configuration.getConfigurationSection("points");
        /*
        Check if the "points" section is null
        If this section is null, there is no need to check continue.
         */
        if(points == null){
            exceptions.add(new NoPointsFound(this.language.getString("NoPointsFoundInConfigurationFile")));
        }else{
            // The "points" section != null
            for (String pointName : points.getKeys(false)) {

                // Check the coordinates for each point
                if (points.getDoubleList(pointName + ".coordinates").size() != 3) {
                    String message = this.language.getString("InvalidPointCoordinates");
                    assert message != null;
                    exceptions.add(new InvalidPointCoordinates(message.replaceAll("%pointName%", pointName)));
                }

                // Check if the world name is valid
                if (points.getString(pointName + ".world") == null) {
                    String message = this.language.getString("NullWorld");
                    assert message != null;
                    exceptions.add(new NullWorld(message.replaceAll("%pointName%", pointName)));
                }else{
                    String worldName = points.getString(pointName + ".world");
                    assert worldName != null;
                    if(Bukkit.getWorld(worldName) == null){
                        String message = this.language.getString("InvalidWorldName");
                        assert message != null;
                        exceptions.add(new InvalidWorldName(message.replaceAll("%pointName%", pointName).replaceAll("%worldName%", worldName)));
                    }
                }

                // Check the color
                ConfigurationSection colorsSection = points.getConfigurationSection(pointName + ".colors");
                if(colorsSection == null){
                    String message = this.language.getString("NoColorsConfigured");
                    assert message != null;
                    exceptions.add(new NoColorsConfigured(message.replaceAll("%pointName%", pointName)));
                }else{
                    for (String colorName : colorsSection.getKeys(false)) {
                        if (colorsSection.getIntegerList(colorName).size() != 3) {
                            String message = this.language.getString("InvalidColor");
                            assert message != null;
                            message = message
                                    .replaceAll("%pointName%", pointName)
                                    .replaceAll("%colorName%", colorName);
                            exceptions.add(new InvalidColorError(message));
                        }
                    }
                }

                // Check the fade color
                ConfigurationSection fadeColorsSection = points.getConfigurationSection(pointName + ".fade_colors");
                if(fadeColorsSection == null){
                    String message = this.language.getString("NoFadeColorsConfigured");
                    assert message != null;
                    exceptions.add(new NoFadeColorsConfigured(message.replaceAll("%pointName%", pointName)));
                }else{
                    for (String colorName : fadeColorsSection.getKeys(false)) {
                        if (fadeColorsSection.getIntegerList(colorName).size() != 3) {
                            String message = this.language.getString("InvalidFadeColor");
                            assert message != null;
                            message = message
                                    .replaceAll("%pointName%", pointName)
                                    .replaceAll("%colorName%", colorName);
                            exceptions.add(new InvalidColorError(message));
                        }
                    }
                }

                // Check the shape
                String shape = points.getString(pointName + ".shape");
                if (shape == null) {
                    String message = this.language.getString("NoShapeConfigured");
                    assert message != null;
                    message = message.replaceAll("%pointName%", pointName);
                    exceptions.add(new NoShapeConfigured(message));
                }else{
                    try {
                        FireworkEffect.Type.valueOf(shape);
                    }catch (Exception e){
                        String message = this.language.getString("InvalidShape");
                        assert message != null;
                        message = message.replaceAll("%pointName%", pointName).replaceAll("%shape%", shape);
                        exceptions.add(new InvalidShape(message));
                    }

                }




            }
        }





        return exceptions;
    }


    public List<InvalidConfigurationFile> checkLoop(){
        List<InvalidConfigurationFile> exceptions = new ArrayList<>();
        ConfigurationSection loopSection = this.configuration.getConfigurationSection("loops");
        if (loopSection == null) {
            String message = this.language.getString("NoLoopsFoundInConfigurationFile");
            exceptions.add(new NoLoopsFound(message));
        }else{
            for (String loopName : loopSection.getKeys(false)) {
                List<String> pointNameList = loopSection.getStringList(loopName + ".points");

                // Verify the validity of fireworks points
                if(pointNameList.size() == 0){
                    String message = this.language.getString("NoPointInLoop");
                    assert message != null;
                    message = message.replaceAll("%loopName%", loopName);
                    exceptions.add(new NoPointInLoop(message));
                }else{
                    for (String point_name : pointNameList) {
                        if(!Objects.requireNonNull(configuration.getConfigurationSection("points")).getKeys(false).contains(point_name)){
                            String message = this.language.getString("InvalidPoint");
                            assert message != null;
                            message = message.replaceAll("%pointName%", point_name);
                            message = message.replaceAll("%loopName%", loopName);
                            exceptions.add(new InvalidPoint(message));
                        }
                    }
                }

                // Check if the interval value is valid
                if(loopSection.getInt(loopName + ".interval") <= 0){
                    String message = this.language.getString("InvalidInterval");
                    assert message != null;
                    message = message.replaceAll("%interval%", String.valueOf(loopSection.getInt(loopName + ".interval")));
                    message = message.replaceAll("%loopName%", loopName);
                    exceptions.add(new InvalidInterval(message));
                }

                // Check if the status value is valid
                String status_value = loopSection.getString(loopName + ".status");
                if(status_value == null){
                    status_value = "null";
                }
                try{
                    LoopState.valueOf(status_value);
                }catch (IllegalArgumentException e){
                    String message = this.language.getString("InvalidLoopStatus");
                    assert message != null;
                    message = message.replaceAll("%loopName%", loopName);
                    message = message.replaceAll("%status_value%", status_value);
                    exceptions.add(new InvalidLoopStatus(message));
                }


            }
        }


        return exceptions;
    }

    public List<InvalidConfigurationFile> check() {
        List<InvalidConfigurationFile> exceptions = new ArrayList<>();
        exceptions.addAll(checkPoint());
        exceptions.addAll(checkLoop());
        return exceptions;
    }
}
