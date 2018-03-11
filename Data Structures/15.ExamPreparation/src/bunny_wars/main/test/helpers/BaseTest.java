package bunny_wars.main.test.helpers;

import bunny_wars.main.IBunnyWarsStructure;
import org.junit.Before;
import bunny_wars.main.test.BunnyWarsStructureInitializer;

public class BaseTest {
    protected IBunnyWarsStructure BunnyWarCollection;

    @Before
    public void setUp() {
        this.BunnyWarCollection = BunnyWarsStructureInitializer.create();
    }
}
