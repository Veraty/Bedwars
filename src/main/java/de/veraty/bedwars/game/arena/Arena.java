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

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

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

}
