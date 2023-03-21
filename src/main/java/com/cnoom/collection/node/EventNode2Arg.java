package com.cnoom.collection.node;


import com.cnoom.base.BaseNode;
import com.cnoom.base.Event2Arg;

public class EventNode2Arg<T1, T2> extends BaseNode implements Event2Arg<T1, T2> {

    final Event2Arg<T1, T2> event;

    EventNode2Arg(Event2Arg<T1, T2> event) {
        this.event = event;
    }

    @Override
    public void invoke(T1 t1, T2 t2) {
        event.invoke(t1, t2);
    }
}
