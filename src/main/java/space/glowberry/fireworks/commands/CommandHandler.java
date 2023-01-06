package space.glowberry.fireworks.commands;

import org.bukkit.command.TabExecutor;

import java.util.Objects;

public interface CommandHandler extends TabExecutor {
    default boolean canHandle(String command) {
        String[] split = this.getClass().getName().split("\\.");
        String name = split[split.length - 1];
        return (Objects.equals(command, name));
    }


}
