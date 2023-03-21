package com.cnoom.list.node;

import com.cnoom.base.Event;

public abstract class ListNode extends BaseListNode implements Event {

    public ListNode() {

    }

    @Override
    public void invoke() {
        event();
    }

    protected abstract void event();
}
