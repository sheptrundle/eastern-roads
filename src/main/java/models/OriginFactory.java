package models;

import creatureGroups.GermanyCreatures;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("StringTemplateMigration")

public class OriginFactory {
    private static final List<String> CREATURE_GROUPS = List.of("Germany", "Hungary");

    public static int getOriginID(String origin) {
        for (int i = 0; i < CREATURE_GROUPS.size(); i++) {
            if (CREATURE_GROUPS.get(i).equals(origin)) {
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

    public static List<String> getOrigins(Player player) throws IllegalArgumentException {
        try {
            int highest = player.getHighestOrigin();
            if (highest < 0 || highest >= CREATURE_GROUPS.size()) {
                throw new IllegalArgumentException("Highest Origins must be >= 0 and < total origins");
            }
            return new ArrayList<>(CREATURE_GROUPS.subList(0, highest + 1));
        } catch (Exception e) {
            throw new IllegalArgumentException("Error retrieving origins for player");
        }
    }
}