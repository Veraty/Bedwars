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

import de.veraty.bedwars.game.arena.Arena;
import lombok.Getter;

/**
 * Arena Voting
 *
 * @author Merlin
 */
@Getter
public class Voting {

    private final Arena[] entries;
    private final int[] votes;
    private boolean active;

    /**
     * Constructs a Voting
     *
     * @param entries
     */
    public Voting(Arena[] entries) {
        this.entries = entries;
        this.votes = new int[entries.length];

        for (int i = 0; i < votes.length; i++) {
            votes[i] = 0;
        }

        this.active = false;
    }

    /**
     * Activates the voting
     */
    public void activate() {
        this.active = true;
    }

    /**
     * Deactivates the voting
     */
    public void deactivate() {
        this.active = false;
    }

    /**
     * Gets the entries
     *
     * @param index
     * @return arena
     */
    public Arena getEntry(int index) {
        return entries[index];
    }

    /**
     * Adds a vote
     *
     * @param index
     */
    public void addVote(int index) {
        votes[index]++;
    }

    /**
     * Subtracts a vote
     *
     * @param index
     */
    public void subVote(int index) {
        votes[index]--;
    }

    /**
     * Gets the votes
     *
     * @param index
     * @return votes
     */
    public int getVotes(int index) {
        return votes[index];
    }

    /**
     * Gets the entry that has the most votes
     *
     * @return winner
     */
    public int getSelected() {
        int mostVotes = -1;
        int id = 0;
        for (int i = 0; i < votes.length; i++) {
            if (mostVotes < votes[i]) {
                mostVotes = votes[i];
                id = 0;
            }
        }
        return id;
    }

}
