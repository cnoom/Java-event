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


        linkedList.addNode(1,1,node(1,2000));
        linkedList.addNode(1,2,node(2,4000));
        linkedList.addNode(2,1,node(3,2000));
        linkedList.addNode(2,2,node(4,4000));
        linkedList.addNode(3,3,node(5,6000));
        linkedList.addNode(4,1,node(6,5000));
        linkedList.invoke();
    }

    private static ListNode node(final int i,final long t){
        return new ListNode() {
            @Override
            protected void event() {
                System.out.println("链式事件"+i);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("链式事件"+i+"无事发生");
                        eventEnd();
                    }
                },t);
            }
        };
    }
}
