package space.glowberry.fireworks.controller;

import org.bukkit.configuration.file.YamlConfiguration;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.commands.CommandHandler;
import space.glowberry.fireworks.commands.commandHandler.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Factory {
    public static File getConfigFile(){
        return new File(Main.DataFolder, "config.yml");
    }

    public static YamlConfiguration getConfig(){
        return YamlConfiguration.loadConfiguration(getConfigFile());
    }
    private static Set<CommandHandler> CommandHandlers;

    public static File getLanguageFile(){
        return new File(Main.DataFolder, getConfig().getString("language") + ".yml");
    }

    public static YamlConfiguration getLanguage(){
        return YamlConfiguration.loadConfiguration(getLanguageFile());
    }

    /**
     * Get all Command Handlers.
     * @return A set of command handlers;
     */
    public static Set<CommandHandler> getCommandHandlers(){
        if(CommandHandlers == null){
            Set<CommandHandler> handlers = new HashSet<>();
            handlers.add(new create());
            handlers.add(new createLoop());
            handlers.add(new launch());
            handlers.add(new loops());
            handlers.add(new reload());
            handlers.add(new startLoop());
            handlers.add(new stopLoop());
            handlers.add(new update());
            CommandHandlers = handlers;
        }

        return CommandHandlers;
    }
}
