/*
 * Copyright (C) 2017 Merlin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.veraty.bedwars.game.arena;

import de.veraty.bedwars.inventory.ClickableInventory;
import de.veraty.bedwars.inventory.ItemBuilder;
import de.veraty.bedwars.inventory.UsableItem;
import de.veraty.bedwars.listener.player.AsyncPlayerChatListener;
import de.veraty.bedwars.player.PlayerWrapper;
import de.veraty.bedwars.storage.Callback;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Bed;

/**
 * Class that has usables, used to setups an arena
 *
 * @author Merlin
 */
@Getter
public class ArenaSetup {

    private final List<UsableItem> items;

    //Setup Components
    private Location baseSpawn;
    private Location shopSpawn;
    private Location bed;
    private BlockFace blockFace;

    private Location baseEntry;

    private Location spawnerLocation;

    //Finished Components
    private List<Arena.Spawner> spawner;
    private List<Arena.Base> bases;

    /**
     * Constructs a ArenaSetup
     */
    public ArenaSetup() {
        this.items = new ArrayList<>();

        //Spawner Usable
        this.items.add(new UsableItem(new ItemBuilder().setMaterial(Material.MOB_SPAWNER).setName("").build()) {

            private final ClickableInventory selector = new ClickableInventory(Bukkit.createInventory(null, 9 * 3, "§bSelect an Item")) {

                {
                    inventory.setItem(9 + 2, new ItemBuilder().setName("§r§aBronze").setMaterial(Material.CLAY_BRICK).build());
                    inventory.setItem(9 + 4, new ItemBuilder().setName("§r§7Eisen").setMaterial(Material.IRON_INGOT).build());
                    inventory.setItem(9 + 6, new ItemBuilder().setName("§r§bGold").setMaterial(Material.GOLD_INGOT).build());
                }

                @Override
                public void click(InventoryClickEvent event, PlayerWrapper playerWrapper) {
                    AsyncPlayerChatListener.getChatInterceptor().put(playerWrapper.getPlayer().getUniqueId(), new Callback<AsyncPlayerChatEvent>() {

                        private final ItemStack item = event.getCurrentItem();

                        @Override
                        public void callback(AsyncPlayerChatEvent object) {
                            int ticksPerSpawn;
                            try {
                                ticksPerSpawn = Integer.parseInt(object.getMessage());
                            } catch (NumberFormatException exception) {
                                object.getPlayer().sendMessage("§4§lCould'nt parse the message to a numbers");
                                return;
                            }

                            spawner.add(new Arena.Spawner(spawnerLocation, ticksPerSpawn, item.getType(), item.getItemMeta().getDisplayName()));
                            object.getPlayer().sendMessage("§3§lAdded the spawner");
                        }
                    });
                    playerWrapper.getPlayer().closeInventory();
                }

            };

            @Override
            public void use(PlayerInteractEvent event, PlayerWrapper playerWrapper) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (event.getClickedBlock() != null) {
                        spawnerLocation = event.getClickedBlock().getLocation();
                        playerWrapper.play(Sound.ITEM_PICKUP);
                        playerWrapper.getPlayer().openInventory(selector.getInventory());
                    }
                }
            }

        });
        this.items.add(new UsableItem(new ItemBuilder().setMaterial(Material.BED).setName("").build()) {

            private final ClickableInventory selector = new ClickableInventory(Bukkit.createInventory(null, 9 * 3, "§bSelect an Entry")) {

                {
                    inventory.setItem(9 + 2, new ItemBuilder().setMaterial(Material.ARMOR_STAND).setName("§l§aShop").build());
                    inventory.setItem(9 + 4, new ItemBuilder().setMaterial(Material.ENDER_PEARL).setName("§l§aSpawn").build());
                    inventory.setItem(9 + 6, new ItemBuilder().setMaterial(Material.BED).setName("§l§aBed").build());
                }

                @Override
                public void click(InventoryClickEvent event, PlayerWrapper playerWrapper) {
                    String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());

                    if (name.equalsIgnoreCase("Shop")) {
                        shopSpawn = baseEntry;
                    } else if (name.equalsIgnoreCase("Spawn")) {
                        baseSpawn = baseEntry;
                    } else if (name.equalsIgnoreCase("Bed")) {
                        if (baseEntry.getBlock().getState() instanceof Bed) {
                            Bed bedState = (Bed) baseEntry.getBlock();

                            bed = baseEntry;
                            blockFace = bedState.getFacing();
                        } else {
                            playerWrapper.getPlayer().sendMessage("§4§lYou have to select a bed");
                            return;
                        }
                    }

                    playerWrapper.getPlayer().sendMessage("§bDu hast den Spawn gesetzt");
                    playerWrapper.getPlayer().closeInventory();
                }
            };

            @Override
            public void use(PlayerInteractEvent event, PlayerWrapper playerWrapper) {

                playerWrapper.play(Sound.ITEM_PICKUP);
                playerWrapper.getPlayer().openInventory(selector.getInventory());
            }
        });
    }

    /**
     * Returns whether the base is finished
     *
     * @return finished
     */
    public boolean isBaseFinished() {
        return baseSpawn != null
                && shopSpawn != null
                && bed != null
                && blockFace != null;
    }

    /**
     * Adds a base to the list and clears all components
     */
    public void addBase() {
        if (isBaseFinished()) {
            this.bases.add(new Arena.Base(baseSpawn, shopSpawn, bed, blockFace));
            this.baseSpawn = null;
            this.shopSpawn = null;
            this.bed = null;
            this.blockFace = null;
        }
    }

    /**
     * Returns whether the setup is finished
     *
     * @return finished
     */
    public boolean isFinished() {
        return true;
    }

    /**
     * Creates an arena
     *
     * @return arena
     */
    public Arena finish() {
        return null;
    }
}
