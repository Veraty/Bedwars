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

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * Serializable version of the {@linkplain Vector} class
 *
 * @author Merlin
 */
@Getter
public final class SerializeVector implements SerializeObject<Vector> {

    private final double x, y, z;

    /**
     * Constructs a SerializeVector
     *
     * @param vector
     */
    public SerializeVector(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
    }

    @Override
    public Vector convert() {
        return new Vector(x, y, z);
    }

    /**
     * Converts the serialize vector to a {@linkplain Location}
     *
     * @param worldName
     * @return location
     */
    public Location convert(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            throw new NullPointerException(String.format("Could'nt find world: %s", worldName));
        }

        return new Location(world, x, y, z);
    }

}
