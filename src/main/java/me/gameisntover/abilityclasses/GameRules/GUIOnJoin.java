package me.gameisntover.abilityclasses.GameRules;

import fr.xephi.authme.AuthMe;
import fr.xephi.authme.api.v3.AuthMeApi;
import me.gameisntover.abilityclasses.configurationfiles.PlayerConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIOnJoin implements Listener {
    public static Inventory classGUI = Bukkit.createInventory(null, 9, "§6Classes");
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerConfiguration.create(player);
        PlayerConfiguration.get().addDefault("Class", null);
        PlayerConfiguration.save();
        PlayerConfiguration.load(player);
        if (PlayerConfiguration.get().getString("Class") == null) {
            player.sendMessage("§cYou have no class, please choose one!");
            ItemStack enderman = new ItemStack(Material.ENDER_PEARL, 1);
            ItemMeta endermanMeta = enderman.getItemMeta();
            ItemStack heaterMan = new ItemStack(Material.BLAZE_POWDER, 1);
            ItemMeta heaterManMeta = heaterMan.getItemMeta();
            ItemStack griefer = new ItemStack(Material.TNT, 1);
            ItemMeta grieferMeta = griefer.getItemMeta();
            ItemStack sandMan = new ItemStack(Material.SAND, 1);
            ItemMeta sandManMeta = sandMan.getItemMeta();
            ItemStack frostMaster = new ItemStack(Material.ICE, 1);
            ItemMeta frostMasterMeta = frostMaster.getItemMeta();
            List<String> frostMasterLore = new ArrayList<>();
            List<String> sandManLore = new ArrayList<>();
            List<String> grieferLore = new ArrayList<>();
            grieferMeta.setDisplayName("§cGriefer");
            endermanMeta.setDisplayName("§2Enderman");
            heaterManMeta.setDisplayName("§6HeaterMan");
            sandManMeta.setDisplayName("§eSandMan");
            frostMasterMeta.setDisplayName("§bFrostMaster");
            List endermanLoreList = new ArrayList<String>();
            endermanLoreList.add(ChatColor.GRAY + "Enderman doesnt likes walking in the mountain. He prefers to teleport himself it would be really faster ");
            endermanLoreList.add("Type : " + ChatColor.RED + "Offensive");
            endermanLoreList.add(ChatColor. YELLOW + "⭐⭐⭐");
            endermanMeta.setLore(endermanLoreList);
            enderman.setItemMeta(endermanMeta);
            grieferLore.add("§7Griefers are really great at creating explosions!");
            grieferLore.add("Type : " + ChatColor.RED + "Offensive");
            grieferLore.add(ChatColor. YELLOW + "⭐⭐⭐");
            grieferMeta.setLore(grieferLore);
            griefer.setItemMeta(grieferMeta);
            List heaterManLoreList = new ArrayList<String>();
            heaterManLoreList.add(ChatColor.GRAY + "Heaterman doesnt hurts in fire and his abilites are related to fire,");
            heaterManLoreList.add("Type : " + ChatColor.RED + "Defensive");
            heaterManLoreList.add(ChatColor. YELLOW + "⭐⭐⭐⭐");
            heaterManMeta.setLore(heaterManLoreList);
            heaterMan.setItemMeta(heaterManMeta);
            sandManLore.add("§7Sandman can get invisible very easy or even make you blindless!");
            sandManLore.add("Type : " + ChatColor.RED + "Supporter");
            sandManLore.add(ChatColor. YELLOW + "⭐⭐");
            sandManMeta.setLore(sandManLore);
            sandMan.setItemMeta(sandManMeta);
            frostMasterLore.add("§7ForstMaster is really experienced in freezing!");
            frostMasterLore.add("Type : " + ChatColor.RED + "Defensive");
            frostMasterLore.add(ChatColor. YELLOW + "⭐⭐⭐⭐⭐");
            frostMasterMeta.setLore(frostMasterLore);
            frostMaster.setItemMeta(frostMasterMeta);
            classGUI.addItem(frostMaster);
            classGUI.addItem(enderman);
            classGUI.addItem(heaterMan);
            classGUI.addItem(griefer);

            if (AuthMeApi.getInstance().isAuthenticated(player)){
                player.openInventory(classGUI);
        } else if (AuthMeApi.getInstance().getPlugin().equals(null)){
                player.openInventory(classGUI);
            }
        } else if (PlayerConfiguration.get().getString("Class") != null){
            player.sendMessage("§bYour Class : "+ PlayerConfiguration.get().getString("Class"));
        }
    }

    @EventHandler
    public void GUIClass(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§6Classes")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(Material.ENDER_PEARL) && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Enderman")) {
                Player player = (Player) e.getWhoClicked();
                PlayerConfiguration.load(player);
                PlayerConfiguration.get().set("Class", "Enderman");
                PlayerConfiguration.get().set("Ability1","true");
                PlayerConfiguration.save();
                player.closeInventory();
            } else         if (e.getCurrentItem().getType().equals(Material.BLAZE_POWDER) && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6HeaterMan")) {
                Player player = (Player) e.getWhoClicked();
                PlayerConfiguration.load(player);
                PlayerConfiguration.get().set("Class", "Heaterman");
                PlayerConfiguration.get().set("Ability1","true");
                PlayerConfiguration.save();
                player.closeInventory();
            }
            }
    }
}
