package com.cnoom.node;

import com.cnoom.base.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseNode implements BaseEvent,Comparable<BaseNode> {
    protected boolean isOnce;
    protected int order;
    protected String tag ="";
    @Override
    public int compareTo(BaseNode o) {
        return Integer.compare(order, o.order);
    }
}
