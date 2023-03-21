package list;

import com.cnoom.list.EventLinkedList;
import com.cnoom.list.node.ListNode;
import org.junit.jupiter.api.Assertions;


import java.util.Timer;
import java.util.TimerTask;

public class TestApply {
    static EventLinkedList linkedList = new EventLinkedList();
    static int value = 0;

    public static void main(String[] arg){
        ListNode node1 = new ListNode() {
            @Override
            protected void event() {
                System.out.println("链式事件1");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("链式事件1无事发生");
                        eventEnd();
                    }
                },5000);
            }
        };
        ListNode node2 = new ListNode() {
            @Override
            protected void event() {
                System.out.println("链式事件2...");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("链式事件2 value++");
                        value++;
                        eventEnd();
                    }
                },5000);
            }
        };

        linkedList.addNode(1,1,node1);
        linkedList.addNode(2,1,node2);
//        linkedList.invoke();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Assertions.assertEquals(1,value);
            }
        },10000);
        System.out.println(    linkedList.removeNode(node1));
        linkedList.invoke();
    }
}
