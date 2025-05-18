package models;

import creatureGroups.CreatureGroup;
import creatureGroups.GermanyCreatures;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("StringTemplateMigration")

public class OriginFactory {
    private static final List<String> CREATURE_GROUPS = List.of("Germany", "Hungary");

    public static int getRegionID(String region) {
        for (int i = 0; i < CREATURE_GROUPS.size(); i++) {
            if (CREATURE_GROUPS.get(i).equals(region)) {
                return i;
            }
        }
        throw new IllegalArgumentException("ID out of bounds for origins");
    }

    public static CreatureGroup getCreatureGroup(String origin) {
        switch (origin) {
            case "Germany":
                return new GermanyCreatures();
        }
        throw new IllegalArgumentException("Unknown creature group: " + origin);
    }

    public static List<String> getRegions(Player player) throws IllegalArgumentException {
        try {
            int highest = player.getHighestRegion();
            if (highest < 0 || highest >= CREATURE_GROUPS.size()) {
                throw new IllegalArgumentException("Highest Origins must be >= 0 and < total origins");
            }
            return new ArrayList<>(CREATURE_GROUPS.subList(0, highest + 1));
        } catch (Exception e) {
            throw new IllegalArgumentException("Error retrieving regions for player");
        }
    }

    public static String getHighestRegion(Player player) throws IllegalArgumentException {
        try {
            int highest = player.getHighestRegion();
            if (highest < 0 || highest >= CREATURE_GROUPS.size()) {
                throw new IllegalArgumentException("Highest Origins must be >= 0 and < total origins");
            }
            return CREATURE_GROUPS.get(highest);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error retrieving highest region for player");
        }
    }
}