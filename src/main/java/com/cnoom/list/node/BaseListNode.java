package com.cnoom.list.node;

import com.cnoom.base.BaseNode;
import com.cnoom.list.BaseEventLinkedList;
import lombok.Getter;
import lombok.Setter;

@Setter
public abstract class BaseListNode extends BaseNode {

    @Getter
    protected int order;
    BaseEventLinkedList.Counter counter;

    protected void eventEnd() {
        if(counter == null){
            return;
        }
        counter.eventDone();
    }
}
