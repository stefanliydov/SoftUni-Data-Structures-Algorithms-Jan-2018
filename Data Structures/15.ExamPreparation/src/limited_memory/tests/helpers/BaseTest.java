package limited_memory.tests.helpers;

import limited_memory.main.ILimitedMemoryCollection;
import limited_memory.main.LimitedMemoryCollection;
import org.junit.Before;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BaseTest {

    public <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
