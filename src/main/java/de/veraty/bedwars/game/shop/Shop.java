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

import de.veraty.bedwars.inventory.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Merlin
 */
public class Shop {

    private final ShopCategory[] categories;
    private final Inventory listInventory;
    private final Inventory[] inventories;

    /**
     * Constructs a Shop
     *
     * @param title
     * @param categories
     */
    public Shop(String title, ShopCategory[] categories) {
        this.categories = categories;
        this.inventories = new Inventory[categories.length];
        this.listInventory = Bukkit.createInventory(null, 9 * 2, title);

        for (int i = 0; i < 9; i++) {

            if (categories.length > i) {
                listInventory.setItem(i, new ItemBuilder()
                        .setMaterial(categories[i].getMaterial())
                        .setName("§r§l".concat(categories[i].getName()))
                        .build());
                createInventory(i, categories[i]);
            }

            listInventory.setItem(i + 9, ItemBuilder.NULL);

        }

    }

    private void createInventory(int index, ShopCategory category) {
        inventories[index] = Bukkit.createInventory(null, 9 * 4, "§7§l".concat(category.getName()));
        for (int i = 0; i < category.size(); i++) {
            ShopEntry shopEntry = category.get(index);

            if (categories.length > i) {
                inventories[i].setItem(i, new ItemBuilder()
                        .setMaterial(categories[i].getMaterial())
                        .setName("§r§l".concat(categories[i].getName()))
                        .build());
            }

            inventories[index].setItem(i + 9, ItemBuilder.NULL);

            inventories[index].setItem(i + 18, shopEntry.getItem());

            inventories[index].setItem(i + (9 * 3), new ItemBuilder()
                    .setMaterial(shopEntry.getMaterial())
                    .setAmount(shopEntry.getPrice())
                    .setName(String.format("§r§lPreis: §b%d", shopEntry.getPrice()))
                    .build());

        }
    }

}
