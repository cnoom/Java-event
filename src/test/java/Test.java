import com.cnoom.collection.EventCollection;
import com.cnoom.node.EventNode;
import com.cnoom.node.EventNodeFactory;

public class Test {
    public static void main(String[] args) {
//        testOrder();
//        testGet();
//        testRemove();
        testSwap();
    }

    private static void testOrder() {
        EventCollection e = new EventCollection();

        System.out.println(e.addNode("tag", 1, false, EventNodeFactory.createEventNode(() -> System.out.println("test"))));
        System.out.println(e.addNode("1", 1, true, EventNodeFactory.createEventNode(() -> System.out.println("1"))));
        System.out.println(e.addNode("2", 2, true, EventNodeFactory.createEventNode(() -> System.out.println("2"))));
        System.out.println(e.addNode("3", 2, true, EventNodeFactory.createEventNode(() -> System.out.println("3"))));
        System.out.println(e.addNode("4", 2, true, EventNodeFactory.createEventNode(() -> System.out.println("4"))));

        System.out.println(" ");
        e.invoke();
        e.invoke();
        /*
            true
            true
            true
            true
            true


            test
            1
            2
            3
            4
            test
            1
         */
    }

    private static void testGet() {
        EventCollection e = new EventCollection();
        e.addNode("1", 2, false, EventNodeFactory.createEventNode(() -> System.out.println("1")));
        e.addNode("2", 2, true, EventNodeFactory.createEventNode(() -> System.out.println("2")));
        e.addNode("3", 2, true, EventNodeFactory.createEventNode(() -> System.out.println("3")));

        e.getNodeByTag("1").invoke();
        e.getNodeByTag("2").invoke();

        e.invoke();

        System.out.println("1:" + e.containTagNode("1"));
        System.out.println("2:" + e.containTagNode("2"));
        /*
            1
            2
            2
            3
            1
            1:true
            2:false
         */
    }

    private static void testRemove() {
        EventCollection e = new EventCollection();
        EventNode eventNode1 = EventNodeFactory.createEventNode(() -> System.out.println("1"));
        EventNode eventNode2 = EventNodeFactory.createEventNode(() -> System.out.println("2"));
        EventNode eventNode3 = EventNodeFactory.createEventNode(() -> System.out.println("3"));
        e.addNode("1", 2, false, eventNode1);
        e.addNode("2", 2, false, eventNode2);
        e.addNode("3", 2, false, eventNode3);

        e.invoke();
        System.out.println(" ");
        System.out.println(e.removeNodeByTag("1"));
        e.invoke();
        System.out.println(e.removeNode(eventNode2));
        e.invoke();
        /*
            1
            2
            3

            true
            2
            3
            true
            3
         */
    }

    private static void testSwap(){
        EventCollection e = new EventCollection();
        EventNode eventNode1 = EventNodeFactory.createEventNode(() -> System.out.println("1"));
        EventNode eventNode2 = EventNodeFactory.createEventNode(() -> System.out.println("2"));
        EventNode eventNode3 = EventNodeFactory.createEventNode(() -> System.out.println("3"));
        e.addNode("1", 2, false, eventNode1);
        e.addNode("2", 2, false, eventNode2);
        e.addNode("3", 2, false, eventNode3);
        e.invoke();
        e.swapByTag("1","3");
        e.swapByTag("1","2");
        e.invoke();
        /*
            1
            2
            3
            3
            2
            1
         */
    }
}
