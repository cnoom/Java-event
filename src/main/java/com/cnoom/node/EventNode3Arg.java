package com.cnoom.node;


import com.cnoom.base.Event3Arg;

public class EventNode3Arg<T1,T2,T3> extends BaseNode implements Event3Arg<T1,T2,T3> {

    final Event3Arg<T1,T2,T3> event;

    EventNode3Arg(Event3Arg<T1, T2, T3> event) {
        this.event = event;
    }


    @Override
    public void invoke(T1 t1, T2 t2, T3 t3) {

    }
}
