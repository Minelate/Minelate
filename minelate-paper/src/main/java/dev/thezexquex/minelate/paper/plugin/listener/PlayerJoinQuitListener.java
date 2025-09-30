package dev.thezexquex.minelate.paper.plugin.listener;

import dev.thezexquex.minelate.api.service.LocaleService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {
    private final LocaleService localeService;

    public PlayerJoinQuitListener(LocaleService localeService) {
        this.localeService = localeService;
    }

    @EventHandler
    public void onPlayerJoin(AsyncPlayerPreLoginEvent event) {
        localeService.fetchLocale(event.getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        localeService.invalidateCache(event.getPlayer().getUniqueId());
    }
}
