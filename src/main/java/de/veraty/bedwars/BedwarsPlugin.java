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
package de.veraty.bedwars;

import de.veraty.bedwars.inventory.Clickable;
import de.veraty.bedwars.inventory.Usable;
import de.veraty.bedwars.player.PlayerWrapper;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Merlin
 */
@Getter
public class BedwarsPlugin extends JavaPlugin {

    @Getter
    private static BedwarsPlugin instance;

    private final List<Usable> usables;
    private final List<Clickable> clickables;

    /**
     * Constructs a BedwarsPlugin
     */
    public BedwarsPlugin() {
        instance = this;

        this.usables = new ArrayList<>();
        this.clickables = new ArrayList<>();
    }

    @Override
    public void onEnable() {
        initialize();
    }

    private void initialize() {

    }

    /**
     * Gets an usable
     *
     * @param item
     * @param playerWrapper
     * @return usable
     */
    public Usable getUsable(ItemStack item, PlayerWrapper playerWrapper) {
        for (int i = 0; i < usables.size(); i++) {
            if (usables.get(i).matches(item, playerWrapper)) {
                return usables.get(i);
            }
        }
        return null;
    }

    /**
     * Gets a clickable
     *
     * @param inventory
     * @param playerWrapper
     * @return clickable
     */
    public Clickable getClickable(Inventory inventory, PlayerWrapper playerWrapper) {
        for (int i = 0; i < clickables.size(); i++) {
            if (clickables.get(i).matches(inventory, playerWrapper)) {
                return clickables.get(i);
            }
        }
        return null;
    }
    
    
    

}
