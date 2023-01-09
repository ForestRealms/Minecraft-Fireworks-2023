package space.glowberry.fireworks.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.utils;

import java.util.List;

public class fw implements TabExecutor {

    private CommandSender sender;

    private void showHelps(){
        ConfigurationSection helps = Factory.getLanguage().getConfigurationSection("helps");
        assert helps != null;
        for (String key : helps.getKeys(false)) {
            String message = helps.getString(key);
            assert message != null;
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        // fw ......
        if(args.length == 0){
            showHelps();
        }else{
            for (CommandHandler commandHandler : Factory.getCommandHandlers()) {
                if(commandHandler.canHandle(args)){
                    return commandHandler.onCommand(sender, command, label, args);
                }
            }
            showHelps();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        // fw ......
        if(args.length == 0){
            showHelps();
        }else{
            for (CommandHandler commandHandler : Factory.getCommandHandlers()) {
                if(commandHandler.canHandle(args)){
                    return commandHandler.onTabComplete(sender, command, label, args);
                }
            }
        }
        return null;
    }
}
