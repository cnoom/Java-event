package com.cnoom.list;

import com.cnoom.base.Event;
import com.cnoom.list.node.ListNode;

import java.util.ArrayList;

public class EventLinkedList extends BaseEventLinkedList<ListNode> implements Event {


    @Override
    public void invoke() {
        start();
    }

    @Override
    void invokeList(ArrayList<ListNode> list) {
        for (ListNode eventNode : list) {
            eventNode.invoke();
        }
    }
}
