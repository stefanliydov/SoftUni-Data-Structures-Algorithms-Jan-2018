package chainblock.java.correctness;

import chainblock.Chainblock;
import chainblock.IChainblock;
import chainblock.Transaction;
import chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

public class Test06 {
    @Test
    public void Contains_OnExistingElement_ShouldReturnTrue()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        Transaction tx1 = new Transaction(5, TransactionStatus.Successfull, "joro", "pesho", 5);
        Transaction tx2 = new Transaction(6, TransactionStatus.Successfull, "joro", "pesho", 5);
        Transaction tx3 = new Transaction(7, TransactionStatus.Successfull, "joro", "pesho", 5);
        //Act
        cb.add(tx1);
        cb.add(tx2);
        cb.add(tx3);
        //Assert
        Assert.assertTrue(cb.contains(5));
        Assert.assertFalse(cb.contains(3));
        Assert.assertTrue(cb.contains(tx2));
        Assert.assertFalse(cb.contains(new Transaction(0, TransactionStatus.Failed, "b", "b", 5)));
    }
}
