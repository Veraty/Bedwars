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
package de.veraty.bedwars.inventory;

import de.veraty.bedwars.BedwarsPlugin;
import de.veraty.bedwars.player.PlayerWrapper;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * The Clickable interface is used to create inventories that execute a method
 * once theyre interacted on
 *
 * @author Merlin
 */
public interface Clickable {

    /**
     * Gets the inventory
     *
     * @param playerWrapper
     * @return inventory
     */
    Inventory getInventory(PlayerWrapper playerWrapper);

    /**
     * Gets whether the inventory matches the prameter
     *
     * @param inventory
     * @param playerWrapper
     * @return match
     */
    boolean matches(Inventory inventory, PlayerWrapper playerWrapper);

    /**
     * Called when the inventory is clicked
     *
     * @param event
     * @param playerWrapper
     */
    void click(InventoryClickEvent event, PlayerWrapper playerWrapper);

    /**
     * Registers the clickable
     */
    default void registerClickable() {
        BedwarsPlugin.getInstance().getClickables().add(this);
    }

}
