package creatureGroups;

import models.*;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("StringTemplateMigration")

public class GermanyCreatures implements CreatureGroup {
    private final ArrayList<Creature> creatures;

    // Drude
    Attack drudePrimary = new Attack("Slash", 3, 90);
    Attack drudeSecondary = new Attack("Dream Invasion", 7, 60);
    Ability drudeAbility = new Ability("Bad Luck", "Enemy is twice as likely to miss on next 2 turns");
    Passive drudePassive = new Passive();
    Creature drude = new Creature("Drude", "Germany",
            "A nocturnal spirit associated with nightmares and the bringing of bad luck", 15,
            drudePrimary, drudeSecondary, drudeAbility, drudePassive);

    public GermanyCreatures() {
        creatures = new ArrayList<>();
        creatures.add(drude);
    }

    public String getOrigin() {
        return "Germany";
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public Creature getCreature(String name) throws IllegalArgumentException {
        for (Creature creature : creatures) {
            if (creature.getName().equalsIgnoreCase(name)) {
                return creature;
            }
        }
        throw new IllegalArgumentException("models.Creature with name " + name + " not found");
    }
}
