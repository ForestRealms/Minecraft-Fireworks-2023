package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.commands.CommandHandler;

import java.util.List;
import java.util.Objects;

public class loops implements CommandHandler {

    private CommandSender sender;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // fw loops
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
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name) && args.length == 1);
    }

    @Override
    public void sendHelp() {

    }
}
