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
package de.veraty.bedwars.game.shop;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * ShopEntrys are items in the shop
 *
 * @author Merlin
 */
@Getter
public final class ShopEntry {

    private final int price;
    private final Material material;
    private final ItemStack item;

    /**
     * Constructs a ShopEntry
     *
     * @param price
     * @param material
     * @param item
     */
    public ShopEntry(int price, Material material, ItemStack item) {
        this.price = price;
        this.material = material;
        this.item = item;
    }

    /**
     * Returns whether the player can buy the item
     *
     * @param player
     * @return
     */
    public boolean canBuy(Player player) {
        ItemStack inventoryItem;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            inventoryItem = player.getInventory().getItem(i);
            if (inventoryItem != null && inventoryItem.getType() == material) {
                amount += inventoryItem.getAmount();
            }
        }
        return amount >= price;
    }

}
