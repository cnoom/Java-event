import com.cnoom.node.EventNodeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestApplyOrder extends TestBasic {
    @Test
    public void testBasic() {
        e.addNode(2, EventNodeFactory.createEventNode(() -> {
            value++;
            Assertions.assertEquals(2, value);
        }));
        e.addNode(1, plusNode());
        e.invoke();
    }

    @Test
    public void testOnce() {
        e.addNode(2, true, EventNodeFactory.createEventNode(() -> {
            value++;
            Assertions.assertEquals(2, value);
        }));
        e.addNode(1, plusNode());
        e.invoke();
        e.invoke();
        Assertions.assertEquals(3, value);
    }
}
