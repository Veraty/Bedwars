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
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.bukkit.block.BlockFace;

/**
 * Serializable version of the {@linkplain de.veraty.bedwars.game.arena.Arena}
 * class
 *
 * @author Merlin
 */
public final class SerializeArena implements SerializeObject<Arena> {

    private final String name, author;
    private final SerializeBase[] bases;

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
    }

    @Override
    public Arena convert() {
        List<Base> baseList = new ArrayList<>(bases.length);
        for (int i = 0; i < bases.length; i++) {
            baseList.set(i, baseList.get(i));
        }

        return new Arena(name, author, baseList);
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

}
