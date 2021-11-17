package me.fredthedoggy.minichat.utils;

import me.fredthedoggy.minichat.PlaceholderManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class MessageUtils {
    public static void sendMessage(Collection<? extends Player> players, Player sender, String miniMessage, Component... other) {
        for (Player player : players) {
            player.sendMessage(appendComponents(MiniMessage.get().parse(PlaceholderManager.parseRelationalPlaceholder(player, sender, PlaceholderManager.parsePlaceholder(sender, miniMessage))), other));
        }
        Bukkit.getConsoleSender().sendMessage(appendComponents(MiniMessage.get().parse(PlaceholderManager.parsePlaceholder(sender, miniMessage)), other));
    }

    private static Component appendComponents(Component component, Component[] other) {
        Component newComponent = component;
        for (Component component1 : other) {
            newComponent = newComponent.append(component1);
        }
        return newComponent;
    }
}
