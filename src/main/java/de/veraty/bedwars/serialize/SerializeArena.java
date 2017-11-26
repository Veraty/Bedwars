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
package de.veraty.bedwars.serialize;

import de.veraty.bedwars.game.arena.Arena;
import de.veraty.bedwars.game.arena.Arena.Base;
import de.veraty.bedwars.game.arena.Arena.Spawner;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Serializable version of the {@linkplain de.veraty.bedwars.game.arena.Arena}
 * class
 *
 * @author Merlin
 */
public final class SerializeArena implements SerializeObject<Arena> {

    private static final long serialVersionUID = 1L;

    private final String name, author;
    private final SerializeBase[] bases;
    private final SerializeSpawner[] spawner;

    /**
     * Constructs a SerializeArena
     *
     * @param arena
     */
    public SerializeArena(Arena arena) {
        this.name = arena.getName();
        this.author = arena.getAuthor();

        this.bases = new SerializeBase[arena.getBases().size()];
        for (int i = 0; i < bases.length; i++) {
            bases[i] = new SerializeBase(arena.getBase(i));
        }

        this.spawner = new SerializeSpawner[arena.getSpawner().size()];
        for (int i = 0; i < spawner.length; i++) {
            spawner[i] = new SerializeSpawner(arena.getSpawner().get(i));
        }
    }

    @Override
    public Arena convert() {
        List<Base> baseList = new ArrayList<>(bases.length);
        for (int i = 0; i < bases.length; i++) {
            baseList.set(i, baseList.get(i));
        }
        List<Spawner> spawnerList = new ArrayList<>(spawner.length);
        for (int i = 0; i < spawner.length; i++) {
            spawnerList.add(spawner[i].convert());
        }
        return new Arena(name, author, baseList, spawnerList);
    }

    /**
     * Serializable version of the {@linkplain
     * de.veraty.bedwras.game.arena.Arena.Base} class
     */
    public static final class SerializeBase implements SerializeObject<Base> {

        private final SerializeLocation spawn, shop;
        private final SerializeVector bed;
        private final int blockFaceOrdinal;

        /**
         * Constructs a SerializeBase
         *
         * @param base
         */
        public SerializeBase(Base base) {
            this.spawn = new SerializeLocation(base.getSpawn());
            this.shop = new SerializeLocation(base.getShop());
            this.bed = new SerializeVector(base.getBed().toVector());
            this.blockFaceOrdinal = base.getBlockFace().ordinal();
        }

        @SneakyThrows
        @Override
        public Base convert() {
            BlockFace blockFace = BlockFace.values()[blockFaceOrdinal];
            return new Base(spawn.convert(), shop.convert(), bed.convert(spawn.getWorldName()), blockFace);
        }
    }

    /**
     * Serializable version of the {@linkplain Spawner} class
     */
    public static final class SerializeSpawner implements SerializeObject<Spawner> {

        private final SerializeLocation location;
        private final int itemId, ticksPerSpawn;
        private final String itemName;

        /**
         * Constructs a SerializeSpawner
         *
         * @param spawner
         */
        public SerializeSpawner(Spawner spawner) {
            this.location = new SerializeLocation(spawner.getLocation());
            this.itemId = spawner.getItemStack().getTypeId();
            this.ticksPerSpawn = spawner.getTicksPerSpawn();
            this.itemName = spawner.getItemStack().getItemMeta().getDisplayName();
        }

        @Override
        public Spawner convert() {
            Material material = Material.getMaterial(itemId);
            return new Spawner(location.convert(), ticksPerSpawn, material, itemName);
        }

    }

}
