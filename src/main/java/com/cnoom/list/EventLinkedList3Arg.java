package com.cnoom.list;

import com.cnoom.base.Event3Arg;
import com.cnoom.list.node.ListNode3Arg;

import java.util.ArrayList;

public class EventLinkedList3Arg<T1, T2, T3> extends BaseEventLinkedList<ListNode3Arg<T1, T2, T3>> implements Event3Arg<T1, T2, T3> {

    T1 t1;
    T2 t2;
    T3 t3;

    @Override
    public void invoke(T1 t1, T2 t2, T3 t3) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    @Override
    void invokeList(ArrayList<ListNode3Arg<T1, T2, T3>> invokeList) {
        for (ListNode3Arg<T1, T2, T3> eventNode : invokeList) {
            eventNode.invoke(t1, t2, t3);
        }
    }
}
