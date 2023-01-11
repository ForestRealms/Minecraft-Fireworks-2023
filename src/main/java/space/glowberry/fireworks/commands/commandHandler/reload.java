package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.Main;
import space.glowberry.fireworks.classes.LoopPool;
import space.glowberry.fireworks.classes.PointPool;
import space.glowberry.fireworks.commands.CommandHandler;
import space.glowberry.fireworks.controller.ConfigManager;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class reload implements CommandHandler {
    private CommandSender sender;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        LoopPool.getInstance().ReInitialize();
        PointPool.getInstance().ReInitialize();
        Main.configManager = new ConfigManager(YamlConfiguration.loadConfiguration(new File(Main.DataFolder, "config.yml")));
        PointPool.getInstance().addAll(Main.configManager.getAllPoints());
        LoopPool.getInstance().addAll(Main.configManager.getAllLoops());
        sender.sendMessage(translate(Factory.getLanguage().getString("ReloadComplete")));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public boolean canHandle(String[] args) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name));
    }

    @Override
    public void sendHelp() {

    }
}
