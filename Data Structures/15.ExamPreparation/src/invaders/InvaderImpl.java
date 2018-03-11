package invaders;

public class InvaderImpl implements Invader {

    private int damage;
    private int distance;


    public InvaderImpl(int damage, int distance) {
       this.damage =damage;
       this.distance = distance;

    }

    public int getDamage() {
       return this.damage;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int compareTo(Invader o) {
        int crit = this.getDistance() - o.getDistance();
        if(crit==0){
            return o.getDamage() - this.getDamage();
        }
        return crit;
    }
}
