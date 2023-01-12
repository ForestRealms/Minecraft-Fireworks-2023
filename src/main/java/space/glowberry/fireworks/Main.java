package space.glowberry.fireworks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import space.glowberry.fireworks.classes.LoopPool;
import space.glowberry.fireworks.classes.PointPool;
import space.glowberry.fireworks.commands.fw;
import space.glowberry.fireworks.controller.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public final class Main extends JavaPlugin {
    public static File DataFolder;
    public static Plugin plugin;
    public static ConfigManager configManager;

    @Override
    public void onEnable() {
        utils.OutputConsoleMessage(utils.translate("&aEnabling Minecraft Fireworks Plugin"));
        plugin = this;
        DataFolder = getDataFolder();
        saveResource("config.yml", false);
        saveResource("zh-cn.yml", false);
        saveResource("default.yml", false);
        utils.OutputConsoleMessage(Factory.getLanguage().getString("CheckingConfigurationFile"));
        configManager = new ConfigManager(Factory.getConfig());
        if(!configManager.isValid()){
            utils.OutputConsoleMessage(Factory.getLanguage().getString("InvalidConfig-ShutDown"));
            this.onDisable();
        }
        PointPool.getInstance().addAll(configManager.getAllPoints());
        LoopPool.getInstance().addAll(configManager.getAllLoops());
        RegisterCommands();
    }

    @Override
    public void onDisable() {
        utils.OutputConsoleMessage(utils.translate("&cDisabling Fireworks Plugin"));
        Bukkit.getScheduler().cancelTasks(this);

        try {
            configManager.saveAllPoints();
            configManager.saveAllLoops();
        } catch (IOException e) {
            String message = Factory.getLanguage().getString("IO-Exception");
            utils.OutputConsoleMessage(utils.translate(message));
        }
    }

    private void RegisterCommands(){
        Objects.requireNonNull(Bukkit.getPluginCommand("fw")).setExecutor(new fw());
        Objects.requireNonNull(Bukkit.getPluginCommand("fireworks")).setExecutor(new fw());
    }
}
