package space.glowberry.fireworks;

import org.bukkit.configuration.file.YamlConfiguration;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.classes.PointPool;
import space.glowberry.fireworks.commands.CommandHandler;
import space.glowberry.fireworks.commands.commandHandler.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Factory {

    private static YamlConfiguration configuration;
    public static File getConfigFile(){
        return new File(Main.DataFolder, "config.yml");
    }

    public static YamlConfiguration getConfig(){
        if(configuration == null){
            configuration = YamlConfiguration.loadConfiguration(getConfigFile());
        }
        return configuration;
    }

    public static void saveConfig() throws IOException {
        configuration.save(getConfigFile());
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

    public static File getDefaultFile(){
        return new File(Main.DataFolder, "default.yml");
    }

    public static YamlConfiguration getDefaultConfig(){
        return YamlConfiguration.loadConfiguration(getDefaultFile());
    }
}
