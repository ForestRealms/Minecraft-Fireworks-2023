package space.glowberry.fireworks.Exceptions;

/**
 * Triggered when the ConfigChecker found that no points in configuration file.
 * @see space.glowberry.fireworks.controller.ConfigChecker
 */
public class NoPointsFound extends InvalidConfigurationFile{
    public NoPointsFound(String message) {
        super(message);
    }
}
