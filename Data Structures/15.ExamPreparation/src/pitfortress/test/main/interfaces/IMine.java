package pitfortress.test.main.interfaces;

import pitfortress.test.main.models.Mine;
import pitfortress.test.main.models.Player;

public interface IMine extends Comparable<Mine> {

    int getId();

    int getDelay();

    int getDamage();

    int getX();

    Player getPlayer();
}
