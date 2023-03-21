package com.cnoom.collection.node;


import com.cnoom.base.Event;
import com.cnoom.base.Event1Arg;
import com.cnoom.base.Event2Arg;
import com.cnoom.base.Event3Arg;

public class EventNodeFactory {
    public static EventNode createEventNode(Event event) {
        return new EventNode(event);
    }

    public static <T> EventNode1Arg<T> createEventNode1Arg(Event1Arg<T> event) {
        return new EventNode1Arg<T>(event);
    }

    public static <T1, T2> EventNode2Arg<T1, T2> createEventNode2Arg(Event2Arg<T1, T2> event) {
        return new EventNode2Arg<>(event);
    }

    public static <T1, T2, T3> EventNode3Arg<T1, T2, T3> createEventNode2Arg(Event3Arg<T1, T2, T3> event) {
        return new EventNode3Arg<>(event);
    }
}
