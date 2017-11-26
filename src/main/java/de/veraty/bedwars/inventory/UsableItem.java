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
import org.bukkit.inventory.ItemStack;

/**
 * Implements the Usable Interface
 *
 * @author Merlin
 */
@Getter
public abstract class UsableItem implements Usable {

    protected final ItemStack itemStack;

    /**
     * Constructs a UsableItem
     *
     * @param itemStack
     */
    public UsableItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack getItemStack(PlayerWrapper playerWrapper) {
        return itemStack;
    }

    @Override
    public boolean matches(ItemStack itemStack, PlayerWrapper playerWrapper) {
        return itemStack.getItemMeta().getDisplayName().equals(this.itemStack.getItemMeta().getDisplayName());
    }

}
