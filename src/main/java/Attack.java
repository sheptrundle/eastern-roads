public class Attack {
    private String name;
    private int damage;
    private int successRate;

    public String getName() {return name;}
    public int getDamage() {return damage;}
    public int getSuccessRate() {return successRate;}

    public Attack(String name, int damage, int successRate) {
        this.name = name;
        this.damage = damage;
        this.successRate = successRate;
    }
}
