package com.cnoom.base;

public interface Event1Arg<T> extends BaseEvent {
    void invoke(T t);
}
