package pitfortress.test.main.models;

import pitfortress.test.main.interfaces.IMine;

public class Mine implements IMine {

    private int id;
    private int delay;
    private int x;
    private Player player;
    private int damage;

    public Mine(int id, int delay, int x, Player player, int damage) {
        this.id = id;
        this.delay = delay;
        this.x = x;
        this.player = player;
        this.damage = damage;
    }

    public void nextTurn(){
        this.delay--;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    public void setDelay(int value) {
        this.delay = value;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int compareTo(Mine o) {
        int cmp =this.delay - o.delay;
        if(cmp ==0){
                return this.id - o.id;
        }
        return cmp;
    }

    @Override
    public String toString() {
        return String.format("Id:%s Player:%s Radius:%s",this.id,this.player.getName(),this.player.getRadius());
    }
}
