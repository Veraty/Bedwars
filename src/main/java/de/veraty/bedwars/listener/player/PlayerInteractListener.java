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
package de.veraty.bedwars.listener.player;

import de.veraty.bedwars.BedwarsPlugin;
import de.veraty.bedwars.inventory.Usable;
import de.veraty.bedwars.player.PlayerWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Merlin
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    private void onCall(PlayerInteractEvent event) {

        PlayerWrapper playerWrapper = PlayerWrapper.getWrapper(event.getPlayer());

        if (event.getItem() != null
                && event.getItem().hasItemMeta()
                && event.getItem().getItemMeta().hasDisplayName()) {
            Usable usable = BedwarsPlugin.getInstance().getUsable(event.getItem(), playerWrapper);
            if (usable != null) {
                usable.use(event, playerWrapper);
                event.setCancelled(true);
            }
        }
    }

}
