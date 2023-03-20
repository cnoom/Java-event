import com.cnoom.collection.EventCollection;
import com.cnoom.node.EventNode;
import com.cnoom.node.EventNodeFactory;


public class TestBasic {

    EventCollection e = new EventCollection();
    int value = 0;

    EventNode plusNode() {
        return EventNodeFactory.createEventNode(() -> value++);
    }

    EventNode minusNode() {
        return EventNodeFactory.createEventNode(() -> value--);
    }

}
