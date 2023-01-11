package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.commands.CommandHandler;

import java.util.List;

public class stopLoop implements CommandHandler {
    private CommandSender sender;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
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
        return false;
    }

    @Override
    public void sendHelp() {

    }
}
