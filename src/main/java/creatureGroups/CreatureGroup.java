package creatureGroups;

import models.Creature;

import java.util.List;

public interface CreatureGroup {
    List<Creature> getCreatures();
    Creature getCreature(String name) throws IllegalArgumentException;
    String getOrigin() throws IllegalArgumentException;
}
