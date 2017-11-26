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

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ItemBuilder class that builds items
 *
 * @author Merlin
 */
@Getter
public class ItemBuilder {

    public static ItemStack NULL = new ItemBuilder().spacer().build();

    private Material material;
    private int amount = 1, data = 0;
    private Color color;
    private boolean unbreakable;
    private List<String> lore;
    private List<ItemFlag> itemFlags;
    private String name;
    private Map<Enchantment, Integer> enchantments;
    private SkullMeta skullMeta;

    public ItemBuilder() {
        this.material = Material.BARRIER;
        this.amount = 1;
        this.data = 0;
        this.unbreakable = false;
        this.name = null;
        this.color = null;
        this.skullMeta = null;
        this.enchantments = new HashMap<>();
        this.lore = new ArrayList<>();
        this.itemFlags = new ArrayList<>();
    }

    public ItemBuilder setSkullMeta(SkullMeta skullMeta) {
        this.skullMeta = skullMeta;
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        if (itemFlags != null) {
            itemFlags.add(flag);
        }
        return this;
    }

    public ItemBuilder addLore(String string) {
        if (lore != null) {
            lore.add(string);
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        if (enchantments != null) {
            enchantments.put(enchantment, level);
        }
        return this;
    }

    public ItemBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setData(int data) {
        this.data = data;
        return this;
    }

    public ItemBuilder glow() {
        this.itemFlags.add(ItemFlag.HIDE_ENCHANTS);
        this.addEnchantment(Enchantment.DEPTH_STRIDER, 1);
        return this;
    }

    public ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder setItemFlags(List<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemStack build() {
        if (material == null) {
            return null;
        }
        ItemStack itemStack = new ItemStack(material, amount, (short) data);

        if (material == Material.SKULL_ITEM) {
            itemStack.setItemMeta(skullMeta);
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemFlags != null) {
            for (ItemFlag flag : itemFlags) {
                itemMeta.addItemFlags(flag);
            }
        }

        if (lore != null) {
            itemMeta.setLore(lore);
        }

        if (name != null) {
            itemMeta.setDisplayName(name);
        }

        if (unbreakable) {
            itemMeta.spigot().setUnbreakable(true);
        }

        if (itemMeta instanceof LeatherArmorMeta && color != null) {
            ((LeatherArmorMeta) itemMeta).setColor(color);
        }

        if (enchantments != null) {
            for (Enchantment enchantment : enchantments.keySet()) {
                itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true);
            }
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemBuilder spacer() {
        this.material = Material.STAINED_GLASS_PANE;
        this.data = 7;
        this.amount = 1;
        this.name = "§r";
        return this;
    }
}
