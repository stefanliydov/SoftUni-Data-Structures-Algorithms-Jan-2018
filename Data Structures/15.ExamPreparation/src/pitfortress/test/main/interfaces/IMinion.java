package pitfortress.test.main.interfaces;

import pitfortress.test.main.models.Minion;

public interface IMinion extends Comparable<Minion> {

    int getId();

    int getX();

    int getHealth();
}
