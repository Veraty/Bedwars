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

import de.veraty.bedwars.player.PlayerWrapper;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * The Usable interface is used to create items that execute a method once
 * theyre interacted on
 *
 * @author Merlin
 */
public interface Usable {

    /**
     * Gets the item
     *
     * @param playerWrapper
     * @return item
     */
    ItemStack getItemStack(PlayerWrapper playerWrapper);

    /**
     * Gets whether the item matches the param
     *
     * @param itemStack
     * @param playerWrapper
     * @return matches
     */
    boolean matches(ItemStack itemStack, PlayerWrapper playerWrapper);

    /**
     * Called when the item is interacted on
     *
     * @param event
     * @param playerWrapper
     */
    void use(PlayerInteractEvent event, PlayerWrapper playerWrapper);

}
