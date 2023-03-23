package com.cnoom.list;

import com.cnoom.base.Event1Arg;
import com.cnoom.list.node.ListNode1Arg;

import java.util.ArrayList;

public class EventLinkedList1Arg<T> extends BaseEventLinkedList<ListNode1Arg<T>> implements Event1Arg<T> {
    T t;

    @Override
    public void invoke(T t) {
        this.t = t;
        start();
    }

    @Override
    void invokeList(ArrayList<ListNode1Arg<T>> invokeList) {
        for (ListNode1Arg<T> eventNode : invokeList) {
            eventNode.invoke(t);
        }
    }
}
