package me.oribuin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

// Implements stuffs :)
public class RingsMenu implements InventoryHolder, Listener {
    private final Inventory inv;

    public RingsMenu() {
        // Create GUI with Size and Name.
        inv = Bukkit.createInventory(this, 27, ChatColor.DARK_GRAY + "Rings Menu by Oribuin.");
    }

    public void initializeItems() {
        // Info Book
        inv.addItem(createGuiItem(4,  Material.KNOWLEDGE_BOOK, "§eInfo Book", "§7Welcome to the Rings Menu.", "§eClick me for more Info!"));
        // Healing Ring
        inv.addItem(createGuiItem(10, Material.GHAST_TEAR, "§eHealing Ring", "§7Click me to Select.", "\n§Available."));
        // Agility Ring
        inv.addItem(createGuiItem(11, Material.FEATHER, "§eAgility Ring", "§7Click me to Select.", "\n§Available."));
        // Fire Ring
        inv.addItem(createGuiItem(12, Material.BLAZE_POWDER, "§eFire Ring", "§7Click me to Select.", "\n§Available."));
        // Vision Ring
        inv.addItem(createGuiItem(13, Material.DIAMOND_HELMET, "§eVision Ring", "§7Click me to Select.", "\n§Available."));
        // Brute Ring
        inv.addItem(createGuiItem(14, Material.DIAMOND_SWORD, "§eBrute Ring", "§7Click me to Select.", "\n§Available."));
        // Spring Ring
        inv.addItem(createGuiItem(15, Material.RABBIT_FOOT, "§eSpring Ring", "§7Click me to Select.", "\n§Available."));
        // Aqua Ring
        inv.addItem(createGuiItem(16, Material.PUFFERFISH, "§eAqua Ring", "§7Click me to Select.", "\n§Available."));
        // Remove Effects
        inv.addItem(createGuiItem(22, Material.POTION, "§cClear Ring", "§7Click me to clear Ring."));
    }

    private ItemStack createGuiItem(int slot, Material material, String name, String...lore) {
        // Set the Material and amount
        ItemStack item = new ItemStack(material, 1);
        // Get the meta of Item.
        ItemMeta meta = item.getItemMeta();
        // Set the name
        meta.setDisplayName(name);
        // Get ArrayList of Lore with Item Meta?
        ArrayList<String> metalore = new ArrayList<String>();

        // For every each lore line add a new line :)
        for(String lorecomments : lore) {
            metalore.add(lorecomments);
        }

        // Set the lore above
        meta.setLore(metalore);
        // Complete The Meta.
        item.setItemMeta(meta);
        inv.setItem(slot, item);
        return item;
    }

    public void onInventory(Player p) {
        // Open Inventory
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        // Gets the block or entity belonging to open inventory
        if (e.getInventory().getHolder() != this) {
            return;
        }
        // If you try to quick grab items using number keys cancel it
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            e.setCancelled(true);
        }

        // Cancel Any click event.
        e.setCancelled(true);

        // Get player who clicked.
        Player p = (Player) e.getWhoClicked();
        // Get the item that was clicked
        ItemStack clickedItem = e.getCurrentItem();

        // If clicked item is gone or the item is air
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
        if (e.getRawSlot() == 10) p.sendMessage("You clicked slot " + 10);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }
}