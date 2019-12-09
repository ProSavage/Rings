package me.oribuin.gui;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;

// Implements stuffs :)
public class RingsMenu implements InventoryHolder, Listener {
    private final Inventory inv;

    public RingsMenu() {
        // Create GUI with Size and Name.
        inv = Bukkit.createInventory(this, 27, ChatColor.DARK_GRAY + "Rings Plugin by Oribuin.");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    @EventHandler
    public void initializeItems() {
        // Info Book
        createGuiItem(4, Material.KNOWLEDGE_BOOK, "§eInfo Book", "§7Welcome to the Rings Menu.", "§eClick me for more Info!");
        // Healing Ring
        createGuiItem(10, Material.GHAST_TEAR, "§eHealing Ring", "§7Click me to Select.", "§aAvailable.");
        // Agility Ring
        createGuiItem(11, Material.FEATHER, "§eAgility Ring", "§7Click me to Select.", "§aAvailable.");
        // Fire Ring
        createGuiItem(12, Material.BLAZE_POWDER, "§eFire Ring", "§7Click me to Select.", "§aAvailable.");
        // Vision Ring
        createGuiItem(13, Material.DIAMOND_HELMET, "§eVision Ring", "§7Click me to Select.", "§aAvailable.");
        // Brute Ring
        createGuiItem(14, Material.DIAMOND_SWORD, "§eBrute Ring", "§7Click me to Select.", "§aAvailable.");
        // Spring Ring
        createGuiItem(15, Material.RABBIT_FOOT, "§eSpring Ring", "§7Click me to Select.", "§aAvailable.");
        // Aqua Ring
        createGuiItem(16, Material.PUFFERFISH, "§eAqua Ring", "§7Click me to Select.", "§aAvailable.");
        // Remove Effects
        createGuiItem(22, Material.POTION, "§cClear Ring", "§7Click me to clear Ring.");
    }

    private void createGuiItem(int slot, Material material, String name, String... lore) {
        // Set the Material and amount
        ItemStack item = new ItemStack(material, 1);
        // Get the meta of Item.
        ItemMeta meta = item.getItemMeta();
        // Set the name
        if (meta != null) {
            meta.setDisplayName(name);
        }

        // Get ArrayList of Lore with Item Meta
        // For every each lore line add a new line :)
        ArrayList<String> metalore = new ArrayList<String>(Arrays.asList(lore));

        // Set the lore above
        if (meta != null) {
            meta.setLore(metalore);
        }

        // Complete The Meta.
        item.setItemMeta(meta);

        // Set the item slot.
        inv.setItem(slot, item);
    }

    public void onInventory(Player p) {
        // Open Inventory
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getInventory().getHolder() != inv.getHolder()) {
            return;
        }
        // If you try to quick grab items using number keys cancel it
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            e.setCancelled(true);
        }

        // Get p who clicked.
        Player p = (Player) e.getWhoClicked();
        // Get the item that was clicked
        ItemStack clickedItem = e.getCurrentItem();
        // If clicked item is gone or the item is air
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        // Plays Bell note at players location
        // if Book is clicked give link to github.
        if (clickedItem.getType() == Material.KNOWLEDGE_BOOK) {

            p.sendMessage("§ehttps://github.com/§6Oribuin§e/Rings/");

        } else if (clickedItem.getType() == Material.GHAST_TEAR) {

            p.addPotionEffect(PotionEffectType.REGENERATION.createEffect(1000000, 0));
            p.sendMessage("§8[§bRings§8] §7You have equipped the §eHealing§7 Ring.");

            Boolean regen = true;

        } else if (clickedItem.getType() == Material.FEATHER) {

            p.addPotionEffect(PotionEffectType.SPEED.createEffect(1000000, 0));
            p.sendMessage("§8[§bRings§8] §7You have equipped the §eSpeed§7 Ring.");

        } else if (clickedItem.getType() == Material.BLAZE_POWDER) {

            p.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(1000000, 0));
            p.sendMessage("§8[§bRings§8] §7You have equipped the §eFire§7 Ring.");

        } else if (clickedItem.getType() == Material.DIAMOND_HELMET) {

            p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(1000000, 0));
            p.sendMessage("§8[§bRings§8] §7You have equipped the §eVision§7 Ring.");

        } else if (clickedItem.getType() == Material.DIAMOND_SWORD) {

            p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(1000000, 0));
            p.sendMessage("§8[§bRings§8] §7You have equipped the §eBrute§7 Ring.");

        } else if (clickedItem.getType() == Material.RABBIT_FOOT) {

            p.addPotionEffect(PotionEffectType.JUMP.createEffect(1000000, 0));
            p.sendMessage("§8[§bRings§8] §7You have equipped the §eSpring§7 Ring.");

        } else if (clickedItem.getType() == Material.PUFFERFISH) {

            p.addPotionEffect(PotionEffectType.WATER_BREATHING.createEffect(1000000, 0));
            p.sendMessage("§8[§bRings§8] §7You have equipped the §eAqua§7 Ring.");

        } else if (clickedItem.getType() == Material.POTION) {

            p.sendMessage("§6lol just drink milk.");
        }

        p.closeInventory();
        p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
        e.setCancelled(true);
    }
}