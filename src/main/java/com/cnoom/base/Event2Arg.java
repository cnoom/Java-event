package com.cnoom.base;

public interface Event2Arg<T1, T2> extends BaseEvent {
    void invoke(T1 t1, T2 t2);
}
