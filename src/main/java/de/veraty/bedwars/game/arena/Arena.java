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

import de.veraty.bedwars.inventory.ItemBuilder;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

/**
 * The Arena class has
 *
 * @author Merlin
 */
@Getter
@Setter
@AllArgsConstructor
public final class Arena {

    private String name, author;
    private List<Base> bases;
    private List<Spawner> spawner;

    /**
     * Gets a base by its id
     *
     * @param id
     * @return base
     */
    public Base getBase(int id) {
        if (id < bases.size()) {
            return bases.get(id);
        }
        return null;
    }

    /**
     * Represents an arenas base
     */
    @Getter
    @AllArgsConstructor
    public static final class Base {

        private final Location spawn, shop, bed;
        private final BlockFace blockFace;

    }

    /**
     * ItemSpawners that are all over the map
     */
    @Getter
    public static final class Spawner {

        private final Location location;
        private final int ticksPerSpawn;
        private final ItemStack itemStack;

        private int ticks;

        /**
         * Constructs a Spawner
         *
         * @param location
         * @param ticksPerSpawn
         * @param material
         * @param name
         */
        public Spawner(Location location, int ticksPerSpawn, Material material, String name) {
            this.location = location;
            this.ticksPerSpawn = ticksPerSpawn;
            this.itemStack = new ItemBuilder()
                    .setMaterial(material)
                    .setName(name)
                    .build();
        }

        /**
         * Updates the spawner
         */
        public void update() {
            ticks++;
            if (ticks > ticksPerSpawn) {
                //TODO: Spawn
                ticks = 0;
            }
        }

        private void spawnItem() {
            location.getWorld().dropItemNaturally(location, itemStack);
        }

    }

}
