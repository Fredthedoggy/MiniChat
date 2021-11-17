package me.fredthedoggy.minichat;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.NavigableMap;
import java.util.TreeMap;

public final class MiniChat extends JavaPlugin {

    private static boolean papiExists = false;
    private static final NavigableMap<String, ChatFormat> formats = new TreeMap<>();

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            papiExists = true;
        }
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        for (String group : config.getConfigurationSection("groups").getKeys(false)) {
            ConfigurationSection section = config.getConfigurationSection("groups." + group);
            if (section == null) continue;
            formats.put(group, new ChatFormat(section.getString("prefix"), section.getString("name"), section.getString("suffix")));
        }
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginCommand("minichat").setExecutor(new ChatCommand());
    }

    public static boolean papiExists() {
        return papiExists;
    }

    public static NavigableMap<String, ChatFormat> getFormats() {
        return formats;
    }

    public class ChatCommand implements CommandExecutor {
        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if (args.length >= 1 && args[0].equals("reload") && sender.hasPermission("minichat.admin")) {
                sender.sendMessage(MiniMessage.get().parse("<green>Success! <gray>Reloading"));
                reloadConfig();
                FileConfiguration config = getConfig();
                formats.clear();
                for (String group : config.getConfigurationSection("groups").getKeys(false)) {
                    ConfigurationSection section = config.getConfigurationSection("groups." + group);
                    if (section == null) continue;
                    formats.put(group, new ChatFormat(section.getString("prefix"), section.getString("name"), section.getString("suffix")));
                }
                return true;
            }
            sender.sendMessage(MiniMessage.get().parse("<yellow>MiniChat by Fredthedoggy <gray>v" + getDescription().getVersion()));
            return true;
        }
    }
}
