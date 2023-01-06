package space.glowberry.fireworks.controller;

import org.bukkit.configuration.file.YamlConfiguration;
import space.glowberry.fireworks.Main;

import java.io.File;

public class Factory {
    public static File getConfigFile(){
        return new File(Main.DataFolder, "config.yml");
    }

    public static YamlConfiguration getConfig(){
        return YamlConfiguration.loadConfiguration(getConfigFile());
    }

    public static File getLanguageFile(){
        return new File(Main.DataFolder, getConfig().getString("language") + ".yml");
    }

    public static YamlConfiguration getLanguage(){
        return YamlConfiguration.loadConfiguration(getLanguageFile());
    }
}
