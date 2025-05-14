package models;

import java.util.ArrayList;

public class Player {
    private ArrayList<Creature> creatures = new ArrayList<>();
    private int highestOrigin;

    // Getters and setters
    public ArrayList<Creature> getCreatures() {return creatures;}
    public void setCreatures(ArrayList<Creature> creatures) {this.creatures = creatures;}
    public int getHighestOrigin() {return highestOrigin;}
    public void setHighestOrigin(int highestOrigin) {this.highestOrigin = highestOrigin;}

    // Brand-new player
    public Player() {
        this.creatures = new ArrayList<>();
        this.highestOrigin = 0;
    }

    // Full-arg Constructor
    public Player(ArrayList<Creature> creatures, int highestOrigin) {
        this.creatures = creatures;
        this.highestOrigin = highestOrigin;
    }

    // Adds a creature to players personal collection
    public boolean addCreature(Creature creature) {
        return creatures.add(creature);
    }

}
