package me.prison.prisonplugin.listeners;

import me.prison.prisonplugin.Prison;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PrisonCore implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        ItemStack item = new ItemStack(e.getBlock().getType(), 1, e.getBlock().getData());
        Inventory inventory = e.getPlayer().getInventory();
        e.setDropItems(false);
        inventory.addItem(item);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        Prison.getInstance().qubiqueBoard.setScoreBoard(player);
        Bukkit.getScheduler().runTaskTimer(Prison.getInstance(), () -> {
            Prison.getInstance().qubiqueBoard.setScoreBoard(player);
        },0,20);
    }

    @EventHandler
    public void EXPTake(PlayerExpChangeEvent e) {e.setAmount(0);}

}
