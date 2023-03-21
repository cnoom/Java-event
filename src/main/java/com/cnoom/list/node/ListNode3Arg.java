package com.cnoom.list.node;

import com.cnoom.base.Event3Arg;

public abstract class ListNode3Arg<T1, T2, T3> extends BaseListNode implements Event3Arg<T1, T2, T3> {


    public ListNode3Arg() {

    }

    @Override
    public void invoke(T1 t1, T2 t2, T3 t3) {
        event(t1, t2, t3);
    }

    protected abstract void event(T1 t1, T2 t2, T3 t3);
}
