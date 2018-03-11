package pitfortress.test.main.interfaces;

import pitfortress.test.main.models.Player;

public interface IPlayer extends Comparable<Player> {

    String getName();

    int getRadius();

    int getScore();
}
