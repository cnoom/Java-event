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

    @Test
    public void swap(){
        e.addNode("a",2,strANode());
        e.addNode("b",1,strBNode());
        e.invoke();
        Assertions.assertEquals("BA", stringBuilder.toString());
        e.swapByTag("a","b");
        e.invoke();
        Assertions.assertEquals("BAAB", stringBuilder.toString());
    }
}
