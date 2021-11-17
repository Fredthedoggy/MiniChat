package me.fredthedoggy.minichat;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.fredthedoggy.minichat.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Map;

public class ChatListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onAsyncChat(AsyncChatEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        ChatFormat format = MiniChat.getFormats().entrySet().stream().filter(e -> player.isPermissionSet("minichat.group." + e.getKey()) && player.hasPermission("minichat.group." + e.getKey())).map(Map.Entry::getValue).findFirst().orElse(MiniChat.getFormats().lastEntry().getValue());
        if (format == null) return;
        event.setCancelled(true);
        String message = "";
        if (format.getPrefix() != null) {
            message = message + format.getPrefix();
        }
        if (format.getName() == null) {
            if (player.getCustomName() == null) {
                message = message + player.getName();
            } else {
                message = message + player.getCustomName();
            }
        } else {
            message = message + format.getName();
        }
        if (format.getSuffix() != null) {
            message = message + format.getSuffix();
        }
        MessageUtils.sendMessage(Bukkit.getOnlinePlayers(), player, message, event.message());
    }
}
