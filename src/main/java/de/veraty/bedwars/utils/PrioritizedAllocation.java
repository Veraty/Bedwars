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
package de.veraty.bedwars.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.Getter;

@Getter
public final class PrioritizedAllocation<E> {

    private final int groupsize, groupAmount;

    private final E[][] groups;

    private final Queue<Entry<E>> entries;

    /**
     * Utility class that allocates groups.
     * <p>
     * It takes a List of Entries and allocates them based on their priority
     * group.
     *
     * @param groups
     * @param groupsize
     * @param entries
     */
    @SuppressWarnings("unchecked")
    public PrioritizedAllocation(int groups, int groupsize, Queue<Entry<E>> entries) {
        this.groupsize = groupsize;
        this.groupAmount = groups;
        this.groups = (E[][]) new Object[groups][groupsize];
        this.entries = entries;
        allocate(entries, 0);
    }

    /**
     * Allocates the entries
     *
     * @param entries
     * @param level
     */
    public void allocate(Queue<Entry<E>> entries, int level) {
        Queue<Entry<E>> next = new LinkedList<>();
        while (!entries.isEmpty()) {
            Entry<E> entry = entries.remove();
            if (level == 0) {
                if (entry.getPriority() >= 0 && !isGroupFull(entry.getPriority())) {
                    add(entry.getPriority(), entry.getEntry());
                } else {
                    next.add(entry);
                }
            } else {
                for (int i = 0; i < groupAmount; i++) {
                    if (!isGroupFull(i)) {
                        add(i, entry.getEntry());
                        break;
                    }
                }
            }
        }
        if (!next.isEmpty()) {
            allocate(next, level + 1);
        }
    }

    private void add(int index, E e) {
        if (index < groupAmount) {
            E[] group = groups[index];
            for (int i = 0; i < group.length; i++) {
                if (group[i] == null) {
                    group[i] = e;
                    return;
                }
            }
        }
    }

    /**
     * Gets a group
     *
     * @param index
     * @return
     */
    public E[] getGroup(int index) {
        if (index < groupAmount) {
            return groups[index];
        }
        return null;
    }

    /**
     * Gets the groupList
     *
     * @param index
     * @return list
     */
    public List<E> getGroupList(int index) {
        if (index < groupAmount) {
            E[] groupArray = groups[index];
            List<E> list = new ArrayList<>();
            for (E element : groupArray) {
                if (element != null) {
                    list.add(element);
                }
            }
            return list;
        }
        return null;
    }

    private boolean isGroupFull(int index) {
        return getGroupList(index).size() == groupsize;
    }

    /**
     * Prints the groups
     */
    public void print() {
        for (int i = 0; i < groupAmount; i++) {
            List<E> group = getGroupList(i);
            for (E e : group) {
                System.out.println(i + " : " + e);
            }
            System.out.println();
        }
    }

    /**
     * Allocation Entry
     *
     * @param <E>
     */
    @Getter
    public static class Entry<E> {

        private final E entry;
        private final int priority;

        /**
         * Constructs an Entry
         *
         * @param entry
         * @param priority
         */
        public Entry(E entry, int priority) {
            this.entry = entry;
            this.priority = priority;
        }

    }

}
