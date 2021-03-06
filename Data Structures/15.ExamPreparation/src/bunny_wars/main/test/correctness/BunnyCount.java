package bunny_wars.main.test.correctness;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import bunny_wars.main.test.helpers.BaseTest;
import bunny_wars.main.test.types.CorrectnessTests;

public class BunnyCount extends BaseTest {

    @Test
    @Category(CorrectnessTests.class)
    public void BunnyCount_WithANewCollection_ShouldBeZero()
    {
        //Assert
        Assert.assertEquals("Collection constructed with an incorrect bunny count!",
                0,
                this.BunnyWarCollection.getBunnyCount());
    }
}
