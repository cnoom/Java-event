package com.cnoom.collection;


import com.cnoom.base.Event;
import com.cnoom.node.EventNode;

public class EventCollection extends BaseNodeCollection<EventNode> implements Event {

    @Override
    public void invoke() {
        for (EventNode node : getInvokeCollection()) {
            node.invoke();
        }
    }
}
