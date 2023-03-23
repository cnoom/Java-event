package com.cnoom.list;

import com.cnoom.base.BaseNode;
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
    @Getter
    private final int order = 0;
    protected boolean isHas;
    ArrayList<T> alwaysList = new ArrayList<>();
    ArrayList<T> onceList = new ArrayList<>();
    ArrayList<ArrayList<T>> invokeList = new ArrayList<>();

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
        if (isOnce) {
            onceList.add(t);
        } else {
            alwaysList.add(t);
        }
        return t;
    }

    public void initPlayList() {
        ArrayList<T> list = new ArrayList<>();
        list.addAll(alwaysList);
        list.addAll(onceList);
        onceList.clear();
        TreeMap<Integer, ArrayList<T>> orderListMap = new TreeMap<>();
        //order sort
        for (T t : list) {
            if (!orderListMap.containsKey(t.getOrder())) {
                orderListMap.put(t.getOrder(), new ArrayList<>());
            }
            orderListMap.get(t.getOrder()).add(t);
        }
        //priority sort and set invokeList
        for (ArrayList<T> value : orderListMap.values()) {
            value.sort(BaseNode::compareTo);
            invokeList.add(value);
        }
    }    private final Counter counter = new Counter(this::nextOrder);

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
        counter.initValue(invokeList.get(counter.index).size());
        invokeList(invokeList.get(counter.index));
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

        private void initValue(int size){
            value = size;
        }
    }
}
