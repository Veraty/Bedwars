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
import org.bukkit.scheduler.BukkitRunnable;

/**
 * The Countdown class is a BukkitRunnable that terminates
 *
 * @author Merlin
 */
@Getter
@Setter
public abstract class Countdown extends BukkitRunnable {

    private final int totalMinutes, totalSeconds;
    private int minutes, seconds, minutesLeft, secondsLeft;

    /**
     * Constructs a Countdown
     *
     * @param totalMinutes
     * @param totalSeconds
     */
    public Countdown(int totalMinutes, int totalSeconds) {
        this.totalMinutes = totalMinutes;
        this.totalSeconds = totalSeconds;
    }

    @Override
    public void run() {

        if (this.seconds == 0 && this.minutes == 0) {
            this.onBegin();
        }

        this.seconds++;

        if (this.seconds > 60) {
            this.seconds = 0;
            this.minutes++;
        }

        this.secondsLeft = this.totalSeconds - this.seconds;
        this.minutesLeft = this.totalMinutes - this.minutes;

        if (this.secondsLeft < 0) {
            this.secondsLeft = 0;
        }

        if (this.minutesLeft < 0) {
            this.minutesLeft = 0;
        }

        if (this.minutes > this.totalMinutes || (this.minutes >= this.totalMinutes && this.seconds > this.totalSeconds)) {
            if (onStop()) {
                this.cancel();
            } else {
                this.restart();
            }
            return;
        }

        this.onTick();
    }

    /**
     * Restarts the countdown
     */
    public void restart() {
        this.minutes = 0;
        this.seconds = 0;
    }

    /**
     * Called every update
     */
    public abstract void onTick();

    /**
     * Called when the Countdown starts
     */
    public abstract void onBegin();

    /**
     * Called when the Countdown is finished
     *
     * @return should be cancelled
     */
    public abstract boolean onStop();

}
