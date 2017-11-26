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
import lombok.Getter;
import org.bukkit.inventory.Inventory;

/**
 * Implements the clickable Interface
 *
 * @author Merlin
 */
@Getter
public abstract class ClickableInventory implements Clickable {

    protected final Inventory inventory;

    /**
     * Constructs a ClickableItem
     *
     * @param inventory
     */
    public ClickableInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory(PlayerWrapper playerWrapper) {
        return inventory;
    }

    @Override
    public boolean matches(Inventory inventory, PlayerWrapper playerWrapper) {
        return inventory.getTitle().equals(inventory.getTitle());
    }

}
