package com.cnoom.collection.node;


import com.cnoom.base.BaseNode;
import com.cnoom.base.Event;

public class EventNode extends BaseNode implements Event {

    final Event event;

    EventNode(Event event) {
        this.event = event;
    }

    @Override
    public void invoke() {
        event.invoke();
    }
}
