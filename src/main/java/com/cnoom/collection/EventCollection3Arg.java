package com.cnoom.collection;


import com.cnoom.base.Event3Arg;
import com.cnoom.collection.node.EventNode3Arg;

public class EventCollection3Arg<T1, T2, T3> extends BaseEventCollection<EventNode3Arg<T1, T2, T3>> implements Event3Arg<T1, T2, T3> {
    @Override
    public void invoke(T1 t1, T2 t2, T3 t3) {
        for (EventNode3Arg<T1, T2, T3> t1T2T3EventNode3Arg : getInvokeCollection()) {
            t1T2T3EventNode3Arg.invoke(t1, t2, t3);
        }
    }
}
