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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * Class that is used to manage teams in bedwars
 *
 * @author Merlin
 */
@Getter
public class Team extends ArrayList<UUID> {

    private final int id;
    private final byte dyeColor;
    private final ChatColor chatColor;

    /**
     * Constructs a Team
     *
     * @param id
     * @param members
     */
    public Team(int id, List<UUID> members) {
        super(members);
        this.id = id;
        this.dyeColor = 0;
        this.chatColor = ChatColor.WHITE;
    }

}
