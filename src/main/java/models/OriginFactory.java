package models;

import creatureGroups.GermanyCreatures;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("StringTemplateMigration")

public class OriginFactory {
    private final ArrayList<String> creatureGroups = new ArrayList<>() {{
        add("Germany");
        add("Hungary");
    }};

    public OriginFactory() {}

    public int getOriginID(String origin) {
        for (int i = 0; i < creatureGroups.size(); i++) {
            if (creatureGroups.get(i).equals(origin)) {
                return i;
            }
        }
        throw new IllegalArgumentException("ID out of bounds for origins");
    }

    public CreatureGroup getCreatureGroup(String origin) {
        switch (origin) {
            case "Germany":
                return new GermanyCreatures();
        }
        throw new IllegalArgumentException("Unknown creature group: " + origin);
    }

    public List<String> getOrigins(Player player) throws IllegalArgumentException {
        try {
            ArrayList<String> origins = new ArrayList<>();
            for (int i = 0; i <= player.getHighestOrigin(); i++) {
                origins.add(creatureGroups.get(i));
            }
            return origins;
        } catch (Exception e) {
            throw new IllegalArgumentException("Highest Origins must be greater than zero and less than total origins");
        }
    }
}