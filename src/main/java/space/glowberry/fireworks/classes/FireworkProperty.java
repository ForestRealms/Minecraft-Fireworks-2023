package space.glowberry.fireworks.classes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.List;

/**
 * Define the property for a specific firework instance
 */
public class FireworkProperty {
    private boolean flicker;
    private boolean trail;
    private FireworkEffect.Type type;
    private List<Color> colors;
    private List<Color> fadeColors;
    private int power;

    public FireworkProperty() {
    }

    public boolean hasFlicker() {
        return flicker;
    }

    public void setFlicker(boolean flicker) {
        this.flicker = flicker;
    }

    public boolean hasTrail() {
        return trail;
    }

    public void setTrail(boolean trail) {
        this.trail = trail;
    }

    public FireworkEffect.Type getType() {
        return type;
    }

    public void setType(FireworkEffect.Type type) {
        this.type = type;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Color> getFadeColors() {
        return fadeColors;
    }

    public void setFadeColors(List<Color> fadeColors) {
        this.fadeColors = fadeColors;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Build a Firework Metadata with specific properties.
     * @param meta The metadata of firework entity that expected to be modified.
     * @return The modified firework metadata
     */
    public FireworkMeta build(FireworkMeta meta){
        meta.setPower(this.power);
        FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.flicker(this.flicker);
        builder.trail(this.trail);
        builder.with(this.type);
        builder.withColor(this.colors);
        builder.withFade(this.fadeColors);
        FireworkEffect effect = builder.build();
        meta.addEffect(effect);
        return meta;
    }
}
