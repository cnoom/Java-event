package com.cnoom.list.node;

import com.cnoom.base.Event2Arg;

public abstract class ListNode2Arg<T1, T2> extends BaseListNode implements Event2Arg<T1, T2> {


    public ListNode2Arg() {

    }

    @Override
    public void invoke(T1 t1, T2 t2) {
        event(t1, t2);
    }

    protected abstract void event(T1 t1, T2 t2);
}
