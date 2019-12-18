package me.oribuin.rings.gui;

import me.oribuin.rings.utils.ChatUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

// Implements stuffs :)
public class RingsMenu implements Listener {
    private static RingsMenu INSTANCE;
    private Inventory inv;

    List<UUID> regenRing = new ArrayList<>();
    List<UUID> speedRing = new ArrayList<>();
    List<UUID> fireRing = new ArrayList<>();
    List<UUID> visionRing = new ArrayList<>();
    List<UUID> strengthRing = new ArrayList<>();
    List<UUID> springRing = new ArrayList<>();
    List<UUID> waterRing = new ArrayList<>();

    private RingsMenu() {
        // Create GUI with Size and Name.
        inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Rings Plugin by Oribuin.");
    }

    public static RingsMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RingsMenu();
        }
        return INSTANCE;
    }

    @EventHandler
    public void initializeItems() {
        // Info Book
        createGuiItem(4, Material.KNOWLEDGE_BOOK,"§eInfo Book", "§7Welcome to the Rings Menu.", "§eLeft Click me for Info!", "§eRight Click for github link!");
        // Healing Ring
        createGuiItem(10, Material.GHAST_TEAR, "§eHealing Ring", "§7Click me to Select.");
        // Agility Ring
        createGuiItem(11, Material.FEATHER,"§eAgility Ring", "§7Click me to Select.");
        // Fire Ring
        createGuiItem(12, Material.BLAZE_POWDER, "§eFire Ring", "§7Click me to Select.");
        // Vision Ring
        createGuiItem(13, Material.DIAMOND_HELMET, "§eVision Ring", "§7Click me to Select.");
        // Brute Ring
        createGuiItem(14, Material.DIAMOND_SWORD,"§eBrute Ring", "§7Click me to Select.");
        // Spring Ring
        createGuiItem(15, Material.RABBIT_FOOT, "§eSpring Ring", "§7Click me to Select.");
        // Aqua Ring
        createGuiItem(16, Material.PUFFERFISH, "§eAqua Ring", "§7Click me to Select.");
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

        if (e.getView().getTopInventory() != inv) {
            return;
        }
        // If you try to quick grab items using number keys cancel it
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            e.setCancelled(true);
        }

        // Get p who clicked.
        Player player = (Player) e.getWhoClicked();
        // Get the item that was clicked
        ItemStack clickedItem = e.getCurrentItem();
        // If clicked item is gone or the item is air
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        // Plays Bell note at players location
        // if Book is clicked give link to github.
        if (clickedItem.getType() == Material.KNOWLEDGE_BOOK) {

            // Send link to github
            if (e.getClick().isLeftClick()) {
                player.sendMessage(ChatUtil.color("Coming Soon <3"));
            }

            if (e.getClick().isRightClick()) {
                player.sendMessage(ChatUtil.color("&ehttps://github.com/Oribuin&e/Rings/"));
            }
            // if they click the ghast tear
        } else if (clickedItem.getType() == Material.GHAST_TEAR) {

            // If array list contains player uuid
            if (regenRing.contains(player.getUniqueId())) {
                // remove them from the list
                regenRing.remove(player.getUniqueId());
                // remove particle effect
                player.removePotionEffect(PotionEffectType.REGENERATION);
                // tell them it was removed
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eHealing&7 Ring!"));
            } else {
                // add ring effect
                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(1000000, 0));
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have equipped the &eHealing&7 Ring."));
            }

        } else if (clickedItem.getType() == Material.FEATHER) {
            if (speedRing.contains(player.getUniqueId())) {
                speedRing.remove(player.getUniqueId());
                // remove ring effect
                player.removePotionEffect(PotionEffectType.SPEED);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eAgility&7 Ring!"));
            } else {
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(1000000, 0));
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have equipped the &eAgility&7 Ring."));

                speedRing.add(player.getUniqueId());

            }
        } else if (clickedItem.getType() == Material.BLAZE_POWDER) {
            if (fireRing.contains(player.getUniqueId())) {
                fireRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eFire&7 Ring!"));
            } else {
                player.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(1000000, 0));
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have equipped the &eFire&7 Ring."));

                fireRing.add(player.getUniqueId());
            }
        } else if (clickedItem.getType() == Material.DIAMOND_HELMET) {
            if (visionRing.contains(player.getUniqueId())) {
                visionRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eVision&7 Ring!"));
            } else {
                player.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(1000000, 0));
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have equipped the &eVision&7 Ring."));

                visionRing.add(player.getUniqueId());
            }
        } else if (clickedItem.getType() == Material.DIAMOND_SWORD) {
            if (strengthRing.contains(player.getUniqueId())) {
                strengthRing.remove(player.getUniqueId());
                // remove ring effect
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eBrute&7 Ring!"));
            } else {
                player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(1000000, 0));
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have equipped the &eBrute&7 Ring."));

                strengthRing.add(player.getUniqueId());
            }
        } else if (clickedItem.getType() == Material.RABBIT_FOOT) {

            if (springRing.contains(player.getUniqueId())) {
                springRing.remove(player.getUniqueId());
                // remove ring effect
                player.removePotionEffect(PotionEffectType.JUMP);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eSpring&7 Ring!"));

            } else {
                player.addPotionEffect(PotionEffectType.JUMP.createEffect(1000000, 0));
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have equipped the &eSpring&7 Ring."));
                springRing.add(player.getUniqueId());
            }

        } else if (clickedItem.getType() == Material.PUFFERFISH) {
            if (waterRing.contains(player.getUniqueId())) {
                waterRing.remove(player.getUniqueId());
                // remove ring effect

                player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eAqua&7 Ring!"));
            } else {
                player.addPotionEffect(PotionEffectType.WATER_BREATHING.createEffect(1000000, 0));
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have equipped the &eAqua&7 Ring."));

                waterRing.add(player.getUniqueId());
            }
        } else if (clickedItem.getType() == Material.POTION) {
            if (regenRing.contains(player.getUniqueId())) {
                regenRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.HEAL);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eHealing&7 ring."));
            } else if (speedRing.contains(player.getUniqueId())) {
                speedRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.SPEED);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eAgility&7 ring."));
            } else if (fireRing.contains(player.getUniqueId())) {
                fireRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eFire&7 ring."));
            } else if (visionRing.contains(player.getUniqueId())) {
                visionRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eVision&7 ring."));
            } else if (strengthRing.contains(player.getUniqueId())) {
                strengthRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eBrute&7 ring."));
            } else if (springRing.contains(player.getUniqueId())) {
                springRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.JUMP);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eSpring&7 ring."));
            } else if (waterRing.contains(player.getUniqueId())) {
                waterRing.remove(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You have cleared your &eAqua&7 ring."));
            } else {
                player.sendMessage(ChatUtil.color("&8[&bRings&8] &7You do not have any active Rings."));
            }
        }

        player.closeInventory();
        player.playNote(player.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.A));
        e.setCancelled(true);
    }
}