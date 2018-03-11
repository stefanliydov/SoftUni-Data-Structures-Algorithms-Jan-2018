package invaders;

import java.util.*;

public class ComputerImpl implements Computer {

    private int energy;
    private List<Invader> invaders;
    private List<Invader> orderOfInputInvaders;


    public ComputerImpl(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException();
        }
        this.energy = energy;
        this.invaders = new ArrayList<>();
        this.orderOfInputInvaders = new LinkedList<>();
    }


    public int getEnergy() {
        return this.energy;
    }

    public void addInvader(Invader invader) {
        this.invaders.add(invader);
        this.orderOfInputInvaders.add(invader);

    }

    public void skip(int turns) {


        List<Invader> deadOnes = new ArrayList<>();
        for (Invader invader : this.invaders) {
            int damageLeft = turns+1 - invader.getDistance();
            invader.setDistance(invader.getDistance() - turns);
            if (invader.getDistance() <= 0) {
                takeDamage(invader.getDamage());
                deadOnes.add(invader);
            }
        }
        this.invaders.removeAll(deadOnes);
        this.orderOfInputInvaders.removeAll(deadOnes);

    }

    public void destroyTargetsInRadius(int radius) {
        Collections.sort(invaders);
        List<Invader> deadOnes = new ArrayList<>();
        for (Invader invader : invaders) {
            if (invader.getDistance() <= radius) {
                deadOnes.add(invader);
            } else {
                break;
            }
        }
        this.invaders.removeAll(deadOnes);
        this.orderOfInputInvaders.removeAll(deadOnes);
    }

    public void destroyHighestPriorityTargets(int n) {
        Collections.sort(invaders);
        List<Invader> deadOnes = new ArrayList<>();
        if(this.invaders.size()<=n){
            this.invaders = new ArrayList<>();
            this.orderOfInputInvaders = new LinkedList<>();
            return;
        }

            deadOnes.addAll(this.invaders.subList(0,n));

        invaders.removeAll(deadOnes);
        this.orderOfInputInvaders.removeAll(deadOnes);
    }

    public Iterable<Invader> invaders() {
        return orderOfInputInvaders;
    }

    private void takeDamage(int damage) {
        this.energy -= damage;
        if (energy < 0) {
            energy = 0;
        }
    }

}
