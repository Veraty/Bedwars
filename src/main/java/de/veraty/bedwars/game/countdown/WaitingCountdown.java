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
package de.veraty.bedwars.game.countdown;

import de.veraty.bedwars.game.Countdown;

/**
 *
 * @author Merlin
 */
public class WaitingCountdown extends Countdown {

    /**
     * Constructs the WaitingCountdown
     *
     * @param minutes
     * @param seconds
     */
    public WaitingCountdown(int minutes, int seconds) {
        super(minutes, seconds);
    }

    @Override
    public void onTick() {

    }

    @Override
    public void onBegin() {
    }

    @Override
    public boolean onStop() {
        return true;
    }

}
