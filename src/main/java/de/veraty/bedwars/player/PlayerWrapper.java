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
package de.veraty.bedwars.player;

import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Player Wrapper class that provides important data and functions
 *
 * @author Merlin
 */
@Getter
@Setter
public class PlayerWrapper {

    @Getter
    private static volatile Map<UUID, PlayerWrapper> wrappers;

    private Player player;

    // Selected Voting entry
    private int votingId = -1;

    /**
     * Constructs a PlayerWrapper
     *
     * @param player
     */
    public PlayerWrapper(Player player) {
        this.player = player;
    }

    /**
     * Plays a sound to the player
     *
     * @param sound
     */
    public void play(Sound sound) {
        this.player.playSound(player.getEyeLocation(), sound, 1f, 1f);
    }

    /**
     * Gets the wrapped player from a map or creates a new instance
     *
     * @param player
     * @return wrapper
     */
    public static synchronized PlayerWrapper getWrapper(Player player) {
        if (wrappers.containsKey(player.getUniqueId())) {
            return wrappers.get(player.getUniqueId());
        }

        PlayerWrapper playerWrapper = new PlayerWrapper(player);
        wrappers.put(player.getUniqueId(), playerWrapper);
        return playerWrapper;

    }

}
