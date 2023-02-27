package com.cnoom.collection;

import com.cnoom.node.BaseNode;
import lombok.Getter;
import lombok.NonNull;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeSet;

public class BaseNodeCollection<T extends BaseNode> {
    private final ArrayList<Integer> orderArr = new ArrayList<>();
    protected NodeTreeSet alwaysCollection = new NodeTreeSet();
    protected NodeTreeSet onceCollection = new NodeTreeSet();
    @Getter
    private int order = 0;

    public boolean addNode(String tag, boolean isOnce, @NonNull T t) {
        return addNode(tag, order++, isOnce, t);
    }

    /**
     * 按指定顺序添加事件
     * NullPointerException – 禁止序号相同
     */
    public boolean addNode(String tag, int order, boolean isOnce, @NonNull T t) {
        if(hasOrder(order)){
            throw new NullPointerException();
        }
        t.setTag(tag);
        t.setOrder(order);
        t.setOnce(isOnce);
        this.order = this.order < order ? order : this.order;
        boolean effective;
        if (t.isOnce()) {
            effective = onceCollection.add(t);
        } else {
            effective = alwaysCollection.add(t);
        }
        if (effective) {
            orderArr.add(order);
        }
        return effective;
    }

    public boolean hasOrder(int order) {
        return orderArr.contains(order);
    }

    public boolean removeNode(T t) {
        boolean effective = alwaysCollection.remove(t);
        if (effective) {
            orderArr.remove(t.getOrder());
        }
        return effective;
    }

    /**
     * 获取唤醒时最后的新排序集合(once集合自动清空)
     */
    protected NodeTreeSet getInvokeCollection() {
        NodeTreeSet nodeTreeSet = new NodeTreeSet();
        nodeTreeSet.addAll(onceCollection);
        nodeTreeSet.addAll(alwaysCollection);
        onceCollection.clear();
        return nodeTreeSet;
    }

    protected class NodeTreeSet extends TreeSet<T> {

    }
}
