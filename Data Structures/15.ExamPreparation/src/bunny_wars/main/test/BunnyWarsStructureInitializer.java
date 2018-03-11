package bunny_wars.main.test;

import bunny_wars.main.BunnyWarsStructure;
import bunny_wars.main.IBunnyWarsStructure;

public class BunnyWarsStructureInitializer {

    public static IBunnyWarsStructure create() {
        return new BunnyWarsStructure();
    }
}
