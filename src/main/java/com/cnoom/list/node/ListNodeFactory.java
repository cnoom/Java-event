package com.cnoom.list.node;

import com.cnoom.base.Event;
import com.cnoom.base.Event1Arg;
import com.cnoom.base.Event2Arg;
import com.cnoom.base.Event3Arg;

public class ListNodeFactory {
    public static ListNode listNode(Event event){
        return new ListNode() {
            @Override
            protected void event() {
                event.invoke();
                eventEnd();
            }
        };
    }

    public static <T> ListNode1Arg<T> listNode1Arg(Event1Arg<T> event){
        return new ListNode1Arg<>() {
            @Override
            protected void event(T t) {
                event.invoke(t);
                eventEnd();
            }
        };
    }

    public static <T1,T2> ListNode2Arg<T1,T2> listNode2Arg(Event2Arg<T1,T2> event){
        return new ListNode2Arg<>() {
            @Override
            protected void event(T1 t1, T2 t2) {
                event.invoke(t1, t2);
                eventEnd();
            }
        };
    }

    public static <T1,T2,T3> ListNode3Arg <T1,T2,T3>listNode1Arg(Event3Arg<T1,T2,T3> event){
        return new ListNode3Arg<>() {
            @Override
            protected void event(T1 t1, T2 t2, T3 t3) {
                event.invoke(t1, t2, t3);
                eventEnd();
            }
        };
    }
}
