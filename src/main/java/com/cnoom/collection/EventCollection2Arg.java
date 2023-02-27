package com.cnoom.collection;


import com.cnoom.base.Event2Arg;
import com.cnoom.node.EventNode2Arg;

public class EventCollection2Arg<T1, T2> extends BaseNodeCollection<EventNode2Arg<T1, T2>> implements Event2Arg<T1, T2> {
    public void invoke(T1 t1, T2 t2) {
        for (Event2Arg<T1, T2> t1T2Event2Arg : getInvokeCollection()) {
            t1T2Event2Arg.invoke(t1, t2);
        }
    }
}
