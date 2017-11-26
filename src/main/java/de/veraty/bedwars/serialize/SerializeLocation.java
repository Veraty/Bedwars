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

/**
 * Serializable version of the {@linkplain Location} class
 *
 * @author Merlin
 *
 */
@Getter
public final class SerializeLocation implements SerializeObject<Location> {

    private static final long serialVersionUID = 1L;

    private final double x, y, z;
    private final float yaw, pitch;
    private final String worldName;

    /**
     * Constructs a SerializeLocation
     *
     * @param location
     */
    public SerializeLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
        this.worldName = location.getWorld().getName();
    }

    @Override
    public Location convert() {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            throw new NullPointerException(String.format("Could'nt find world: %s", worldName));
        }
        return new Location(world, x, y, z, yaw, pitch);
    }

}
