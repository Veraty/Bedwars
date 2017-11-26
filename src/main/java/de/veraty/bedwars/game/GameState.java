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

/**
 * State of a game
 *
 * @author Merlin
 */
@Getter
public abstract class GameState {

    private final Countdown countdown;

    private final String name;
    private final String[] scoreboardScores;

    /**
     * Constructs a GameState
     *
     * @param name
     * @param countdown
     * @param scoreboardScores
     */
    public GameState(final String name, Countdown countdown, String[] scoreboardScores) {
        this.countdown = countdown;
        this.name = name;
        this.scoreboardScores = scoreboardScores;
    }

    /**
     * Initiates the state
     */
    public void initiate() {
        countdown.schedule();
    }

    /**
     * Called when the game has been initiated
     */
    public abstract void onInitiated();

}
