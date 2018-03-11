package pitfortress.test.main.models;

import pitfortress.test.main.interfaces.IPlayer;

public class Player implements IPlayer {

    private int radius;
    private String name;
    private int score;

    public Player( String name ,int radius) {
        this.radius = radius;
        this.name = name;
        this.setScore(0);
    }
    public void incrementScore(){
        this.score++;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score = value;
    }

    @Override
    public int compareTo(Player o) {

        int cmp = this.score - o.score;
        if (cmp == 0) {
            return this.name.compareTo(o.name);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return String.format("Name:%s Score:%s",this.name,this.score);
    }
}
