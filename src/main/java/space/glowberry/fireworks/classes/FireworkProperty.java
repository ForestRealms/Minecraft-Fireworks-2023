package space.glowberry.fireworks.classes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.*;

/**
 * Define the property for a specific firework instance
 */
public class FireworkProperty {
    private boolean flicker;
    private boolean trail;
    private FireworkEffect.Type type;
    private Map<String, Color> colors;
    private Map<String, Color> fadeColors;
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


    public Map<String, Color> getColors() {
        return colors;
    }

    public void setColors(Map<String, Color> colors) {
        this.colors = colors;
    }

    public Map<String, Color> getFadeColors() {
        return fadeColors;
    }

    public void setFadeColors(Map<String, Color> fadeColors) {
        this.fadeColors = fadeColors;
    }

    public void setColor(Color color){
        Map<String, Color> c = new HashMap<>();
        c.put("color", color);
        this.colors = c;
    }

    public void setFadeColor(Color fadeColor){
        Map<String, Color> c = new HashMap<>();
        c.put("color", fadeColor);
        this.colors = c;
        this.fadeColors = c;
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
        for (String colorName : this.colors.keySet()) {
            builder.withColor(this.colors.get(colorName));
        }
        for (String colorName : this.fadeColors.keySet()) {
            builder.withFade(this.colors.get(colorName));
        }
        FireworkEffect effect = builder.build();
        meta.addEffect(effect);
        return meta;
    }


    /**
     * Randomly generate the fireworks property
     * @param colorNum The number of colors
     * @param fadeColorNum The number of fade colors
     */
    public void randomize(int colorNum, int fadeColorNum){
        Map<String, Color> colorMap = new HashMap<>();
        for (int i = 1; i < colorNum + 1; i++) {
            String name = "color_" + i;
            Color color = Color.fromRGB(
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            colorMap.put(name, color);
        }
        this.colors = colorMap;
        colorMap.clear();
        for (int i = 1; i < fadeColorNum + 1; i++) {
            String name = "fade_color_" + i;
            Color color = Color.fromRGB(
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            colorMap.put(name, color);
        }
        this.fadeColors = colorMap;
        this.setPower((int) (1 + Math.random() * 5));
        this.setTrail(new Random().nextBoolean());
        this.setFlicker(new Random().nextBoolean());
        int x = new Random().nextInt(5);
        switch (x){
            case 0:
                this.setType(FireworkEffect.Type.BALL);
            case 1:
                this.setType(FireworkEffect.Type.BALL_LARGE);
            case 2:
            default:
                this.setType(FireworkEffect.Type.BURST);
            case 3:
                this.setType(FireworkEffect.Type.STAR);
            case 4:
                this.setType(FireworkEffect.Type.CREEPER);
        }

    }

    public void randomize(boolean RandomColorNumber, boolean RandomFadeColorNumber){
        int colorNum;
        int fadeColorNum;
        if (RandomColorNumber){
            colorNum = (int) (Math.random() * 20);
        }else{
            colorNum = 10;
        }
        if (RandomFadeColorNumber){
            fadeColorNum = (int) (Math.random() * 20);
        }else {
            fadeColorNum = 10;
        }
        randomize(colorNum, fadeColorNum);
    }
}
