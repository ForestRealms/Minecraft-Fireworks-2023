package space.glowberry.fireworks.commands.commandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.glowberry.fireworks.Factory;
import space.glowberry.fireworks.classes.Loop;
import space.glowberry.fireworks.classes.LoopPool;
import space.glowberry.fireworks.classes.LoopState;
import space.glowberry.fireworks.commands.CommandHandler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class startLoop implements CommandHandler {

    private CommandSender sender;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /fw startLoop <循环名称> [循环名称] ...
        this.sender = sender;

        if(args.length == 2 && args[1].equals("")){
            sendHelp();
            return true;
        }

        List<String> loopNames = new CopyOnWriteArrayList<>(Arrays.asList(args));
        loopNames.remove(0);

        for (String loopName : loopNames) {
            if(!LoopPool.getInstance().LoopIsExist(loopName)){
                String message = Factory.getLanguage().getString("LoopNotExist");
                assert message != null;
                message = message.replaceAll("%loopName%", loopName);
                sender.sendMessage(translate(message));
                loopNames.remove(loopName);
            }
        }

        for (String loopName : loopNames) {
            Loop loop = LoopPool.getInstance().getLoop(loopName);
            assert loop != null;
            loop.setLoopState(LoopState.WORKING);
            String message = Factory.getLanguage().getString("success-on-startLoop");
            assert message != null;
            message = message.replaceAll("%loopName%", loopName);
            sender.sendMessage(translate(message));
        }



        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // /fw startLoop <循环名称> [循环名称] ...
        List<String> result = LoopPool.getInstance().getNameList();
        List<String> loopNames = new LinkedList<>(Arrays.asList(args));
        loopNames.remove(0);
        for (String loopName : loopNames) {
            result.remove(loopName);
        }
        return result;
    }


    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public boolean canHandle(String[] args) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(args[0], name) && (args.length >= 2));
    }

    @Override
    public void sendHelp() {

    }
}
