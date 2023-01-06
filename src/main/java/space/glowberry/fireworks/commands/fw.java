package space.glowberry.fireworks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import space.glowberry.fireworks.controller.Factory;
import space.glowberry.fireworks.utils;

import java.util.List;

public class fw implements TabExecutor {

    private void showHelps(){
        ConfigurationSection helps = Factory.getLanguage().getConfigurationSection("helps");
        assert helps != null;
        for (String key : helps.getKeys(false)) {
            utils.OutputConsoleMessage(helps.getString(key));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // fw ......
        if(args.length == 0){
            showHelps();
        }else{
            for (CommandHandler commandHandler : Factory.getCommandHandlers()) {
                System.out.println(commandHandler.getClass().getName() + "   " + commandHandler.canHandle(args[0]));
//                if(commandHandler.canHandle(args[0])){
//                    return commandHandler.onCommand(sender, command, label, args);
//                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
