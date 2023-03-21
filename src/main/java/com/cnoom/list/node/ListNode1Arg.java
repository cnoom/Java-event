package com.cnoom.list.node;

import com.cnoom.base.Event1Arg;

public abstract class ListNode1Arg<T> extends BaseListNode implements Event1Arg<T> {


    public ListNode1Arg() {

    }

    @Override
    public void invoke(T t) {
        event(t);
    }

    protected abstract void event(T t);
}
