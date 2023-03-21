package com.cnoom.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseNode implements BaseEvent, Comparable<BaseNode> {
    protected boolean isOnce;
    protected int priority;
    protected String tag = "";

    @Override
    public int compareTo(BaseNode o) {
        return Integer.compare(priority, o.priority);
    }
}
