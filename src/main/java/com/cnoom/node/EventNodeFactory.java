package com.cnoom.node;


import com.cnoom.base.Event;

public class EventNodeFactory {
    public static EventNode createEventNode(Event event) {
        return new EventNode(event);
    }

    public static <T> EventNode1Arg<T> createEventNode1Arg(EventNode1Arg<T> event) {
        return new EventNode1Arg<T>(event);
    }

    public static <T1, T2> EventNode2Arg<T1, T2> createEventNode2Arg(EventNode2Arg<T1, T2> event) {
        return new EventNode2Arg<>(event);
    }

    public static <T1, T2,T3> EventNode3Arg<T1, T2,T3> createEventNode2Arg(EventNode3Arg<T1, T2,T3> event) {
        return new EventNode3Arg<>(event);
    }
}
