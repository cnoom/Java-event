package com.cnoom.node;


import com.cnoom.base.Event1Arg;

public class EventNode1Arg<T> extends BaseNode implements Event1Arg<T> {

    final Event1Arg<T> event;

    EventNode1Arg(Event1Arg<T> event) {
        this.event = event;
    }

    @Override
    public void invoke(T t) {
        event.invoke(t);
    }
}
