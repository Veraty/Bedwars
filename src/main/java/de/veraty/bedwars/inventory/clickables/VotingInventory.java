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
package de.veraty.bedwars.inventory.clickables;

import de.veraty.bedwars.game.Voting;
import de.veraty.bedwars.game.arena.Arena;
import de.veraty.bedwars.inventory.ClickableInventory;
import de.veraty.bedwars.inventory.ItemBuilder;
import de.veraty.bedwars.player.PlayerWrapper;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Merlin
 */
@Getter
public class VotingInventory extends ClickableInventory {

    private Voting voting;

    /**
     * Constructs a VotingInventory
     *
     * @param voting
     */
    public VotingInventory(Voting voting) {
        super(Bukkit.createInventory(null, 9 * 3, "§bMap Voting"));
        this.voting = voting;

        for (int i = 0; i < voting.getEntries().length; i++) {
            Arena arena = voting.getEntries()[i];
            inventory.setItem(i, ItemBuilder.NULL);
            inventory.setItem(i + 18, ItemBuilder.NULL);
            inventory.setItem(i + 9, new ItemBuilder()
                    .setMaterial(Material.NETHER_STAR)
                    .setName("§r§l".concat(arena.getName()))
                    .addLore("§6Author: §7".concat(arena.getAuthor()))
                    .setAmount(0)
                    .build());
        }

    }

    @Override
    public void click(InventoryClickEvent event, PlayerWrapper playerWrapper) {
        String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
        if (!name.isEmpty()) {
            for (int i = 0; i < voting.getEntries().length; i++) {
                Arena entry = voting.getEntries()[i];
                if (entry.getName().equalsIgnoreCase(name)) {
                    if (playerWrapper.getVotingId() >= 0) {
                        //Remove voting
                        voting.subVote(playerWrapper.getVotingId());

                        ItemStack itemStack = event.getInventory().getItem(playerWrapper.getVotingId() + 9);
                        if (itemStack != null) {
                            itemStack.setAmount(voting.getVotes(playerWrapper.getVotingId()));
                        }

                        if (playerWrapper.getVotingId() == i) {
                            playerWrapper.setVotingId(-1);
                            return;
                        }
                    }

                    voting.addVote(i);
                    event.getCurrentItem().setAmount(voting.getVotes(i));
                    playerWrapper.setVotingId(i);
                }
            }
        }
    }

}
