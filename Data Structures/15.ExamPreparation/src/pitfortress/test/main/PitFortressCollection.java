package pitfortress.test.main;

import pitfortress.test.main.interfaces.IPitFortress;
import pitfortress.test.main.models.Mine;
import pitfortress.test.main.models.Minion;
import pitfortress.test.main.models.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PitFortressCollection implements IPitFortress {

    private int minionId = 1;
    private int mineId = 1;

    private HashMap<String, Player> players;
    private TreeSet<Player> playersScore;
    private TreeMap<Integer, TreeSet<Minion>> minions;
    private TreeSet<Mine> mines;

    public PitFortressCollection() {
        this.players = new HashMap<>();
        this.playersScore = new TreeSet<>();
        this.minions = new TreeMap<>();
        this.mines = new TreeSet<>();
    }

    @Override
    public int getPlayerCount() {
        return players.size();
    }

    @Override
    public int getMinionCount() {
        return this.minions.values().stream().mapToInt(TreeSet::size).sum();
    }

    @Override
    public int getMineCount() {
        return mines.size();
    }

    @Override
    public void addPlayer(String name, int mineRadius) {
        if (this.players.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        if (mineRadius < 0) {
            throw new IllegalArgumentException();
        }
        Player player = new Player(name, mineRadius);

        this.players.put(name, player);
        this.playersScore.add(player);
    }

    @Override
    public void addMinion(int xCoordinate) {
        if (xCoordinate < 0 || xCoordinate > 1000000) {
            throw new IllegalArgumentException();
        }
        Minion minion = new Minion(this.minionId++, xCoordinate);
        if (!this.minions.containsKey(xCoordinate)) {
            this.minions.put(xCoordinate, new TreeSet<>());
        }
        this.minions.get(xCoordinate).add(minion);
    }

    @Override
    public void setMine(String playerName, int xCoordinate, int delay, int damage) {
        if (!this.players.containsKey(playerName)) {
            throw new IllegalArgumentException();
        }
        if (xCoordinate < 0 || xCoordinate > 1000000) {
            throw new IllegalArgumentException();
        }
        if (delay < 1 || delay > 10000) {
            throw new IllegalArgumentException();
        }
        if (damage < 0 || damage > 1000) {
            throw new IllegalArgumentException();
        }

        Mine mine = new Mine(this.mineId++, delay, xCoordinate, this.players.get(playerName), damage);
        this.mines.add(mine);
    }

    @Override
    public Iterable<Minion> reportMinions() {

        List<Minion> result = new LinkedList<>();
        for (TreeSet<Minion> minionTreeSet : this.minions.values()) {
            result.addAll(minionTreeSet);
        }
        return result;
    }

    @Override
    public Iterable<Player> top3PlayersByScore() {
        if (this.players.size() < 3) {
            throw new IllegalArgumentException();
        }
        TreeSet<Player> newSet = new TreeSet<>();
        for (Player player : playersScore) {
            newSet.add(player);
        }

        return newSet.descendingSet().stream().limit(3).collect(Collectors.toList());
    }

    @Override
    public Iterable<Player> min3PlayersByScore() {
        if (this.players.size() < 3) {
            throw new IllegalArgumentException();
        }

        return playersScore.stream().sorted(Player::compareTo).limit(3).collect(Collectors.toList());

    }

    @Override
    public Iterable<Mine> getMines() {
        return this.mines;
    }

    @Override
    public void playTurn() {
        List<Mine> minesToDetonate = new LinkedList<>();
        for (Mine mine : mines) {
            mine.nextTurn();
            if (mine.getDelay() <= 0) {
                minesToDetonate.add(mine);
            }
        }

        for (Mine mine : minesToDetonate) {
            int start = mine.getX() - mine.getPlayer().getRadius();
            int end = mine.getX() + mine.getPlayer().getRadius();
            Player player= mine.getPlayer();
            SortedMap<Integer, TreeSet<Minion>> minionsToUpdate = this.minions.subMap(start, end + 1);
            for (TreeSet<Minion> minions : minionsToUpdate.values()) {
                for (Minion minion : minions) {
                    minion.takeDamage(mine.getDamage());
                    if (minion.getHealth() <= 0) {
                        player.incrementScore();
                        this.minions.get(minion.getX()).remove(minion);
                    }
                }
            }
        }
        for (Mine mine : minesToDetonate) {
            this.mines.remove(mine);
        }
    }
}
