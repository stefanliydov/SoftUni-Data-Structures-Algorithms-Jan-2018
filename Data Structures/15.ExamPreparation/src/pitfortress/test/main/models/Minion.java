package pitfortress.test.main.models;

import pitfortress.test.main.interfaces.IMinion;

public class Minion implements IMinion {

    private int id;
    private int x;
    private int health;

    public Minion(int id, int x) {
        this.id = id;
        this.x = x;
        this.setHealth(100);
    }
    public void takeDamage(int damage){
        this.health-=damage;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int value) {
        this.health = value;
    }

    @Override
    public int compareTo(Minion o) {
        int cmp =this.x - o.x;
        if(cmp ==0){
            return this.id - o.id;
        }
        return cmp;
    }

    @Override
    public String toString() {
        return String.format("X:%s Hp:%s",this.getX(),this.getHealth());
    }
}
