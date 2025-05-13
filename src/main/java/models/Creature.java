package models;

public class Creature {
    private String name;
    private String origin;
    private String description;
    private int health;
    private Attack primaryAttack;
    private Attack secondaryAttack;
    private Ability ability;
    private Passive passive;

    // Full argument constructor
    public Creature(String name, String origin, String description, int health,
                    Attack primaryAttack, Attack secondaryAttack, Ability ability, Passive passive) {
        this.name = name;
        this.origin = origin;
        this.description = description;
        this.health = health;
        this.primaryAttack = primaryAttack;
        this.secondaryAttack = secondaryAttack;
        this.ability = ability;
        this.passive = passive;
    }

    // Getters
    public String getName() {return name;}
    public String getOrigin() {return origin;}
    public String getDescription() {return description;}
    public int getHealth() {return health;}
    public Attack getPrimaryAttack() {return primaryAttack;}
    public Attack getSecondaryAttack() {return secondaryAttack;}
    public Ability getAbility() {return ability;}
    public Passive getPassive() {return passive;}
}
