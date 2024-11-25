package dev.splityosis.sysengine.configlib.mappers;

import dev.splityosis.sysengine.configlib.configuration.AbstractMapper;
import dev.splityosis.sysengine.configlib.manager.ConfigManager;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;

public class ColorMapper implements AbstractMapper<Color> {

    @Override
    public Color getFromConfig(ConfigManager manager, ConfigurationSection section, String path) {
        String colorStr = section.getString(path);
        if (colorStr == null || colorStr.isEmpty()) {
            return null;
        }

        String[] parts = colorStr.split(" ");
        if (parts.length != 3) {
            return null;
        }

        int red = Integer.parseInt(parts[0]);
        int green = Integer.parseInt(parts[1]);
        int blue = Integer.parseInt(parts[2]);

        return Color.fromRGB(red, green, blue);
    }

    @Override
    public void setInConfig(ConfigManager manager, Color instance, ConfigurationSection section, String path) {
        if (instance == null) {
            section.set(path, "");
            return;
        }

        String colorString = String.format("%d %d %d", instance.getRed(), instance.getGreen(), instance.getBlue());
        section.set(path, colorString);
    }
}