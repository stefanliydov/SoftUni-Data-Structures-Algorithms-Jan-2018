package pitfortress.test.main.interfaces;

import pitfortress.test.main.models.Mine;
import pitfortress.test.main.models.Minion;
import pitfortress.test.main.models.Player;

public interface IPitFortress {

    int getPlayerCount();

    int getMinionCount();

    int getMineCount();

    void addPlayer(String name, int mineRadius);

    void addMinion(int xCoordinate);

    void setMine(String playerName, int xCoordinate, int delay, int damage);

    Iterable<Minion> reportMinions();

    Iterable<Player> top3PlayersByScore();

    Iterable<Player> min3PlayersByScore();

    Iterable<Mine> getMines();

    void playTurn();
}
