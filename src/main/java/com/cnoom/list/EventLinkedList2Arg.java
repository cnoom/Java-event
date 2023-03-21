package com.cnoom.list;

import com.cnoom.base.Event2Arg;
import com.cnoom.list.node.ListNode2Arg;

import java.util.ArrayList;

public class EventLinkedList2Arg<T1, T2> extends BaseEventLinkedList<ListNode2Arg<T1, T2>> implements Event2Arg<T1, T2> {

    T1 t1;
    T2 t2;

    @Override
    public void invoke(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    void invokeList(ArrayList<ListNode2Arg<T1, T2>> invokeList) {
        for (ListNode2Arg<T1, T2> eventNode : invokeList) {
            eventNode.invoke(t1, t2);
        }
    }
}
