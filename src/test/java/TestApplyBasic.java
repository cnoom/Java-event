import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 测试应用
 */
public class TestApplyBasic extends TestBasic {

    @Test
    public void basic() {
        e.addNode(false, plusNode());
        e.invoke();
        Assertions.assertEquals(1, value);
        e.invoke();
        Assertions.assertEquals(2, value);
    }

    @Test
    public void basicOnce() {
        e.addNode(true, plusNode());
        e.invoke();
        Assertions.assertEquals(1, value);
        e.invoke();
        Assertions.assertEquals(1, value);
    }

    @Test
    public void units() {
        e.addNode(true, plusNode());
        e.invoke();
        Assertions.assertEquals(0, e.size());
        Assertions.assertEquals(1, value);
        e.addNode(false, minusNode());
        e.invoke();
        Assertions.assertEquals(1, e.size());
        Assertions.assertEquals(0, value);
        e.invoke();
        Assertions.assertEquals(1, e.size());
        Assertions.assertEquals(-1, value);
    }
}
