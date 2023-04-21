package com.cnoom.list;

import com.cnoom.base.Event;
import com.cnoom.list.node.BaseListNode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 一个链式事件表，上一个顺序节点事件全部结束后才会进入到下个顺序节点的事件，同顺序节点优先级高事件先触发可作为链式动画使用
 * <br/>
 * <br/>
 * 事件节点完成时需要调用Counter.eventDone()
 */
public abstract class BaseEventLinkedList<T extends BaseListNode> {
    private final Counter counter = new Counter(this::nextOrder);
    NodeArray alwaysList = new NodeArray();
    NodeArray onceList = new NodeArray();
    ArrayList<ArrayList<T>> invokeList = new ArrayList<>();
    /**
     * 当前排在最后的事件顺序
     */
    @Getter
    private int order = 0;

    public T addNode(int order, int priority, T t) {
        return addNode(order, priority, false, t);
    }

    /**
     * @param order    顺序
     * @param priority 通顺序下优先级
     * @param isOnce   是否仅执行一次
     */
    public T addNode(int order, int priority, boolean isOnce, T t) {
        t.setOrder(order);
        t.setPriority(priority);
        t.setOnce(isOnce);
        t.setCounter(counter);
        this.order = Math.max(order, this.order);
        if (isOnce) {
            onceList.add(t);
        } else {
            alwaysList.add(t);
        }
        return t;
    }

    public void initPlayList() {
        NodeArray list = new NodeArray();
        list.addAll(alwaysList);
        list.addAll(onceList);
        onceList.clear();
        TreeMap<Integer, NodeArray> orderListMap = new TreeMap<>();
        //order sort
        for (T t : list) {
            if (!orderListMap.containsKey(t.getOrder())) {
                orderListMap.put(t.getOrder(), new NodeArray());
            }
            orderListMap.get(t.getOrder()).add(t);
        }
        //priority sort and set invokeList// 9582 5982 5892 5829
        for (NodeArray value : orderListMap.values()) {
            for (int i = 0; i < value.size(); i++) {
                for (int j = i + 1; j < value.size(); j++) {
                    if (value.get(i).getPriority() > value.get(j).getPriority()) {
                        value.swap(i,j);
                    }
                }
            }
            invokeList.add(value);
        }
    }

    public boolean removeNode(T t) {
        boolean ok = alwaysList.remove(t);
        if (!ok) {
            ok = onceList.remove(t);
        }
        return ok;
    }

    public void clear() {
        alwaysList.clear();
        onceList.clear();
        invokeList.clear();
    }

    protected void start() {
        initPlayList();
        if (invokeList.size() == 0) {
            return;
        }
        counter.reset();
        counter.initValue(invokeList.get(counter.index).size());
        invokeList(invokeList.get(counter.index));
    }

    public int size() {
        return alwaysList.size() + onceList.size();
    }

    abstract void invokeList(ArrayList<T> invokeList);

    private void nextOrder() {
        if (counter.index >= invokeList.size()) {
            counter.reset();
            invokeList.clear();
            return;
        }
        counter.initValue(invokeList.get(counter.index).size());
        invokeList(invokeList.get(counter.index));
    }

    public static class Counter {
        int index;
        int value;
        Event orderEndEvent;

        Counter(Event event) {
            orderEndEvent = event;
        }

        public void eventDone() {
            value--;
            if (value < 1) {
                index++;
                orderEndEvent.invoke();
            }
        }


        private void reset() {
            value = 0;
            index = 0;
        }

        private void initValue(int size) {
            value = size;
        }
    }

    protected class NodeArray extends ArrayList<T> {
        protected void swap(int i, int j) {
            T t = get(i);
            set(i, get(j));
            set(j, t);
        }
    }
}
