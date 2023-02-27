package com.cnoom.base;

public interface Event3Arg<T1, T2, T3> extends BaseEvent {
    void invoke(T1 t1,T2 t2,T3 t3);
}
