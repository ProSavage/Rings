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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

// Implements stuffs :)
public class RingsMenu implements InventoryHolder, Listener {
    private final Inventory inv;

    public RingsMenu() {
        // Create GUI with Size and Name.
        inv = Bukkit.createInventory(this, 27, ChatColor.DARK_GRAY + "Rings Plugin by Oribuin.");
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }

    @EventHandler
    public void initializeItems() {
        // Info Book
        inv.addItem(createGuiItem(4, Material.KNOWLEDGE_BOOK, "§eInfo Book", "§7Welcome to the Rings Menu.", "§eClick me for more Info!"));
        // Healing Ring
        inv.addItem(createGuiItem(10, Material.GHAST_TEAR, "§eHealing Ring", "§7Click me to Select.", "\n§aAvailable."));
        // Agility Ring
        inv.addItem(createGuiItem(11, Material.FEATHER, "§eAgility Ring", "§7Click me to Select.", "\n§aAvailable."));
        // Fire Ring
        inv.addItem(createGuiItem(12, Material.BLAZE_POWDER, "§eFire Ring", "§7Click me to Select.", "\n§aAvailable."));
        // Vision Ring
        inv.addItem(createGuiItem(13, Material.DIAMOND_HELMET, "§eVision Ring", "§7Click me to Select.", "§aAvailable."));
        // Brute Ring
        inv.addItem(createGuiItem(14, Material.DIAMOND_SWORD, "§eBrute Ring", "§7Click me to Select.", "\n§aAvailable."));
        // Spring Ring
        inv.addItem(createGuiItem(15, Material.RABBIT_FOOT, "§eSpring Ring", "§7Click me to Select.", "\n§aAvailable."));
        // Aqua Ring
        inv.addItem(createGuiItem(16, Material.PUFFERFISH, "§eAqua Ring", "§7Click me to Select.", "\n§aAvailable."));
        // Remove Effects
        inv.addItem(createGuiItem(22, Material.POTION, "§cClear Ring", "§7Click me to clear Ring."));
    }

    private ItemStack createGuiItem(int slot, Material material, String name, String... lore) {
        // Set the Material and amount
        ItemStack item = new ItemStack(material, 1);
        // Get the meta of Item.
        ItemMeta meta = item.getItemMeta();
        // Set the name
        if (meta != null) {
            meta.setDisplayName(name);
        }
        // Get ArrayList of Lore with Item Meta?
        ArrayList<String> metalore = new ArrayList<String>();

        // For every each lore line add a new line :)
        for (String lorecomments : lore) {
            metalore.add(lorecomments);
        }

        // Set the lore above
        assert meta != null;
        meta.setLore(metalore);
        // Complete The Meta.
        item.setItemMeta(meta);
        // Set the item inside the GUI with the correct slot.
        inv.setItem(slot, item);;
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

        // Cancel Any click event
        e.setCancelled(true);

        // Get p who clicked.
        Player p = (Player) e.getWhoClicked();
        // Get the item that was clicked
        ItemStack clickedItem = e.getCurrentItem();
        // If clicked item is gone or the item is air
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        // Plays Bell note at players location
        // if Book is clicked give link to github.
        if (clickedItem.getType() == Material.KNOWLEDGE_BOOK) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.sendMessage(ChatColor.YELLOW + "https://github.com/Oribuin/Rings/");
        }

        // if ghast tear is clicked applies regeneration effect
        if (clickedItem.getType() == Material.GHAST_TEAR) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.addPotionEffect(PotionEffectType.REGENERATION.createEffect(1000000, 0));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", You have equipped the Healing Ring.");
        }

        // if feather is clicked applies speed effect
        if (clickedItem.getType() == Material.FEATHER) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.addPotionEffect(PotionEffectType.SPEED.createEffect(1000000, 0));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", You have equipped the Speed Ring.");
        }

        // if blaze powder is clicked applies fire resistance effect
        if (clickedItem.getType() == Material.BLAZE_POWDER) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(1000000, 0));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", You have equipped the Fire Ring.");
        }

        // if diamond helmet is clicked applies night vision effect
        if (clickedItem.getType() == Material.DIAMOND_HELMET) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(1000000, 0));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", You have equipped the Vision Ring.");
        }

        // if diamond sword is clicked applies strength effect
        if (clickedItem.getType() == Material.DIAMOND_SWORD) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(1000000, 0));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", You have equipped the Brute Ring.");
        }

        // If rabbit's foot is clicked it applies jump boost effect
        if (clickedItem.getType() == Material.RABBIT_FOOT) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.addPotionEffect(PotionEffectType.JUMP.createEffect(1000000, 0));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", You have equipped the Spring Ring.");
        }

        // if pufferfish is clicked it applies water breathing effect
        if (clickedItem.getType() == Material.PUFFERFISH) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.addPotionEffect(PotionEffectType.WATER_BREATHING.createEffect(1000000, 0));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", You have equipped the Aqua Ring.");
        }

        //If potion is clicked just play sound.
        //I will add this later but need the gui working first.

        if (clickedItem.getType() == Material.POTION) {
            p.playNote(p.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
            p.sendMessage(ChatColor.YELLOW + p.getName() + ", lol just drink milk.");
        }
        e.setCancelled(true);
    }
}