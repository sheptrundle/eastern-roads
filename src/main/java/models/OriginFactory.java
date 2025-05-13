package models;

import java.util.Hashtable;

@SuppressWarnings("StringTemplateMigration")

public class OriginFactory {
    private Hashtable<String, Integer> originIDs = new Hashtable<>() {{
        put("Germany", 1);
        put("Hungary", 2);
    }};

    public OriginFactory() {}

    public int getOriginID(String origin) {
        return originIDs.get(origin);
    }

    public CreatureGroup getCreatureGroup(String origin) {
        switch (origin) {
            case "Germany":
                return new GermanyCreatures();
        }
        throw new IllegalArgumentException("Unknown creature group: " + origin);
    }
}