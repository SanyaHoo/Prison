package me.prison.prisonplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class CreateAndSetGUI implements Listener {

    private void setToInv(Inventory inv, Material material, String name, Integer slot, String lore){
        ItemStack mode = new ItemStack(material, 1);
        ItemMeta meta = mode.getItemMeta();
        List<String> loreList = new ArrayList<>();
        String[] lores = lore.split("\n");
        Collections.addAll(loreList, lores);
        meta.setLore(loreList);
        meta.setDisplayName(name);
        mode.setItemMeta(meta);
        inv.setItem(slot, mode);
    }

    private void addToInv(Inventory inv, Material material, String name, Integer slot, String lore){
        if (!inv.contains(material)) {
        ItemStack mode = new ItemStack(material, 1);
        ItemMeta meta = mode.getItemMeta();
        List<String> loreList = new ArrayList<>();
        String[] lores = lore.split("\n");
        Collections.addAll(loreList, lores);
        meta.setLore(loreList);
        meta.setDisplayName(name);
        mode.setItemMeta(meta);
        inv.addItem(mode);
        }
    }

    public void PrisonGui(Player player){
        Inventory inventory = player.getInventory();
        addToInv(inventory, Material.COMPASS, "§6Меню", 8, "");
    }

    private void setGlass(Inventory inventory, Integer slot) {
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(" ");
        glass.setItemMeta(meta);
        inventory.setItem(slot, glass);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Inventory inventory = player.getInventory();
        addToInv(inventory, Material.COMPASS, "§6Меню", 8, "");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        Inventory inventory = player.getInventory();
        addToInv(inventory, Material.COMPASS, "§6Меню", 8, "");

    }

    @EventHandler
    public void createGui(PlayerInteractEvent e) {
        Player player = e.getPlayer();
            Inventory inventory = player.getInventory();
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) ||  e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                switch (player.getInventory().getItemInMainHand().getType()) {
                    case COMPASS:
                        openGuiMenu(player);
                        break;
                }
            }

    }

    @EventHandler
    public void onGuiClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
            switch (e.getInventory().getName()) {
                case "Выбор карты":
                    switch (e.getSlot()) {
                        case 50:
                            openGuiBosses(p);
                            break;
                        case 45:
                            p.closeInventory();
                            break;
                        case 53:
                            openGuiMenu(p);
                            break;
                    }
                    e.setCancelled(true);
                    return;
                case "Выбор босса":
                    switch (e.getSlot()) {
                        case 48:
                            openGuiMap(p);
                            break;
                        case 45:
                            p.closeInventory();
                            break;
                        case 53:
                            openGuiMenu(p);
                            break;
                    }
                    e.setCancelled(true);
                    return;
                case "Меню быстрого доступа":
                    switch (e.getSlot()) {
                        case 22:
                            openGuiMap(p);
                            break;
                        case 13:
                            openGuiBosses(p);
                            break;
                        case 36:
                            p.closeInventory();
                            break;
                        case 21:
                            openSkillsGui(p);
                            break;
                        case 23:
                            openFractionGui(p);
                            break;
                        case 31:
                            openStatisticGui(p);
                            break;
                    }

                    e.setCancelled(true);
                    return;
                case "Умения":
                    switch (e.getSlot()) {
                        case 53:
                            openGuiMenu(p);
                            break;
                    }
                    e.setCancelled(true);
                    return;
                case "Фракции":
                    switch (e.getSlot()) {
                        case 26:
                            openGuiMenu(p);
                            break;
                    }
                    e.setCancelled(true);
                    return;
                case "Статистика":
                    switch (e.getSlot()) {
                        case 53:
                            openGuiMenu(p);
                            break;
                    }
                    e.setCancelled(true);
                    return;
            }
    }

    public void openGuiMap(Player player){
            Inventory guiCompass1 = Bukkit.createInventory(player, 9*6, "Выбор карты");

            //Borders
            IntStream.range(0, 9).forEachOrdered(n -> {
                setGlass(guiCompass1, n);
            });
            setGlass(guiCompass1, 9);
            setGlass(guiCompass1, 17);
            setGlass(guiCompass1, 18);
            setGlass(guiCompass1, 26);
            setGlass(guiCompass1, 27);
            setGlass(guiCompass1, 35);
            setGlass(guiCompass1, 36);
            setGlass(guiCompass1, 44);
            IntStream.range(45, 54).forEachOrdered(n -> {
                setGlass(guiCompass1, n);
            });
            //Borders

            setToInv(guiCompass1, Material.ARROW, "§6Выбор босса", 50, "");
            setToInv(guiCompass1, Material.BARRIER, "§6Выйти из меню", 45, "");
            setToInv(guiCompass1, Material.BLAZE_ROD, "§6Выйти в главное меню", 53, "");

            player.openInventory(guiCompass1);
    }



    public void openGuiBosses(Player player){
            Inventory guiCompass2 = Bukkit.createInventory(player, 9*6, "Выбор босса");

            //Borders
            IntStream.range(0, 9).forEachOrdered(n -> {
                setGlass(guiCompass2, n);
            });
            setGlass(guiCompass2, 9);
            setGlass(guiCompass2, 17);
            setGlass(guiCompass2, 18);
            setGlass(guiCompass2, 26);
            setGlass(guiCompass2, 27);
            setGlass(guiCompass2, 35);
            setGlass(guiCompass2, 36);
            setGlass(guiCompass2, 44);
            IntStream.range(45, 54).forEachOrdered(n -> {
                setGlass(guiCompass2, n);
            });
            //Borders

            setToInv(guiCompass2, Material.ARROW, "§6Выбор карты", 48, "");
            setToInv(guiCompass2, Material.BARRIER, "§6Выйти из меню", 45, "");
            setToInv(guiCompass2, Material.BLAZE_ROD, "§6Выйти в главное меню", 53, "");
            player.openInventory(guiCompass2);
        }



    public void openGuiMenu(Player player) {
            Inventory guiMenu = Bukkit.createInventory(player, 9*5, "Меню быстрого доступа");
            //Borders
            IntStream.range(0, 45).forEachOrdered(n -> {
                setGlass(guiMenu, n);
            });
            //Borders
            setToInv(guiMenu, Material.EMPTY_MAP, "§6Выбор карты", 22, "");
            setToInv(guiMenu, Material.SKULL_ITEM, "§6Выбор босса", 13, "");
            setToInv(guiMenu, Material.BARRIER, "§6Выйти из меню", 36, "");
            setToInv(guiMenu, Material.GOLD_PICKAXE, "§6Умения", 21, "");
            setToInv(guiMenu, Material.SHIELD, "§6Фракции", 23, "");
            setToInv(guiMenu, Material.GRASS, "§6Статистика", 31, "");
            player.openInventory(guiMenu);


        }



    public void openSkillsGui(Player player) {
            Inventory guiSkills = Bukkit.createInventory(player, 9*6, "Умения");
            setToInv(guiSkills, Material.BLAZE_ROD, "§6Выйти в главное меню", 53, "");
            player.openInventory(guiSkills);



    }

    public void openFractionGui(Player player) {
            Inventory guiFraction = Bukkit.createInventory(player, 9*3, "Фракции");
            setToInv(guiFraction, Material.BLAZE_ROD, "§6Выйти в главное меню", 26, "");
            player.openInventory(guiFraction);
    }

    public void openStatisticGui(Player player) {
            Inventory guiStstistic = Bukkit.createInventory(player, 9*6, "Статистика");
            setToInv(guiStstistic, Material.BLAZE_ROD, "§6Выйти в главное меню", 53, "");
            player.openInventory(guiStstistic);
    }
}
