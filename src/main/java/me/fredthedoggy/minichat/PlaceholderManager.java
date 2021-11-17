package me.fredthedoggy.minichat;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderManager {
    public static String parsePlaceholder(Player player, String input) {
        if (MiniChat.papiExists()) {
            return PlaceholderAPI.setPlaceholders(player, input);
        }
        return input;
    }

    public static String parseRelationalPlaceholder(Player viewer, Player player, String input) {
        if (MiniChat.papiExists()) {
            return PlaceholderAPI.setRelationalPlaceholders(player, viewer, input);
        }
        return input;
    }
}
