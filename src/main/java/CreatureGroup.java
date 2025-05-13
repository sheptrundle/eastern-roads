import java.util.List;

public interface CreatureGroup {
    List<Creature> getCreatures();
    Creature getCreature(String name) throws IllegalArgumentException;
}
