package models;

import java.util.ArrayList;

public class Player {
    private ArrayList<Creature> creatures = new ArrayList<>();
    private int highestRegion;

    // Getters and setters
    public ArrayList<Creature> getCreatures() {return creatures;}
    public void setCreatures(ArrayList<Creature> creatures) {this.creatures = creatures;}
    public int getHighestRegion() {return highestRegion;}
    public void setHighestRegion(int highestRegion) {this.highestRegion = highestRegion;}

    // Brand-new player
    public Player() {
        this.creatures = new ArrayList<>();
        this.highestRegion = 0;
    }

    // Full-arg Constructor
    public Player(ArrayList<Creature> creatures, int highestRegion) {
        this.creatures = creatures;
        this.highestRegion = highestRegion;
    }

    // Adds a creature to players personal collection
    public boolean addCreature(Creature creature) {
        return creatures.add(creature);
    }

}
