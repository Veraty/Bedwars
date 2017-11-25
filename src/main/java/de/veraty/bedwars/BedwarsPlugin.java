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
package de.veraty.bedwars;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Merlin
 */
public class BedwarsPlugin extends JavaPlugin {

    @Getter
    private static BedwarsPlugin instance;

    /**
     * Constructs a BedwarsPlugin
     */
    public BedwarsPlugin() {
        instance = this;
    }

    @Override
    public void onEnable() {
        initialize();
    }

    private void initialize() {

    }

}
