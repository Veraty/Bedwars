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
package de.veraty.bedwars.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * BukkitEvent that is called whenever the Games GameState changes
 *
 * @author Merlin
 */
@Getter
@Setter
public class GameStateChangeEvent implements Cancellable {

    @Getter
    private static final HandlerList handerList = new HandlerList();

    private final GameState from;
    private GameState to;
    private boolean cancelled;

    /**
     * Constructs a GameStateChangeEvent
     *
     * @param from
     * @param to
     */
    public GameStateChangeEvent(final GameState from, final GameState to) {
        this.from = from;
        this.to = to;
        this.cancelled = false;
    }

    /**
     * Gets the HandlerList
     *
     * @return handlers
     */
    public HandlerList getHandlers() {
        return handerList;
    }

}
