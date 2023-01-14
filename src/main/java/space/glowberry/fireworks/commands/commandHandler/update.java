package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.commands.CommandHandler;

import java.util.List;
import java.util.Objects;

public class update implements CommandHandler {
    private CommandSender sender;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        sender.sendMessage(translate("&c暂未配备更新功能，请到官方仓库检查更新！"));
        sender.sendMessage(translate("&a http://gitlab.glowberry.space/glowberry-community/minecraft-fireworks-2023"));
        sender.sendMessage(translate("&e https://github.com/ForestRealms/Minecraft-Fireworks-2023"));
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
