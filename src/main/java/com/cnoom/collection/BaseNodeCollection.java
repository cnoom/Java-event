package com.cnoom.collection;

import com.cnoom.node.BaseNode;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseNodeCollection<T extends BaseNode> {
    protected NodeArray alwaysCollection = new NodeArray();
    protected NodeArray onceCollection = new NodeArray();
    protected HashMap<String, String> swapMap = new HashMap<>();
    @Getter
    private int priority = 0;

    public boolean addNode(T t) {
        return addNode("default", priority++, false, t);
    }

    public boolean addNode(boolean isOnce, T t) {
        return addNode("default", priority++, isOnce, t);
    }

    public boolean addNode(int priority, T t) {
        return addNode("default", priority, false, t);
    }

    public boolean addNode(String tag, boolean isOnce, T t) {
        return addNode(tag, priority++, isOnce, t);
    }

    public boolean addNode(int priority, boolean isOnce, T t) {
        return addNode("default", priority, isOnce, t);
    }

    /**
     * 按指定顺序添加事件
     * <br/>序号相同时自然顺序添加，同优先级的事件一次性事件执行始终在前
     */
    public boolean addNode(@NonNull String tag, int order, boolean isOnce, @NonNull T t) {
        t.setTag(tag);
        t.setPriority(order);
        t.setOnce(isOnce);
        this.priority = this.priority < order ? order : this.priority;
        boolean effective;
        if (t.isOnce()) {
            effective = onceCollection.add(t);
        } else {
            effective = alwaysCollection.add(t);
        }
        return effective;
    }

    public T getNodeByTag(String tag) {
        for (T t : onceCollection) {
            if (t.getTag().equals(tag)) {
                return t;
            }
        }
        for (T t : alwaysCollection) {
            if (t.getTag().equals(tag)) {
                return t;
            }
        }
        throw new NullPointerException("Tag not found in collection! tag[" + tag + "]");
    }

    public boolean containNode(T t) {
        if (!onceCollection.contains(t)) {
            return alwaysCollection.contains(t);
        }
        return true;
    }

    public boolean containTagNode(String tag) {
        for (T t : onceCollection) {
            if (t.getTag().equals(tag)) {
                return true;
            }
        }
        for (T t : alwaysCollection) {
            if (t.getTag().equals(tag)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeNode(T t) {
        boolean effective = alwaysCollection.remove(t);
        if (!effective) {
            effective = onceCollection.remove(t);
        }
        return effective;
    }

    public int size() {
        return alwaysCollection.size() + onceCollection.size();
    }

    /**
     * 根据Tag交互指定的node
     * <br> 每个node只可被交换一次
     */
    public boolean swapByTag(String tag1, String tag2) {
        if (swapMap.containsKey(tag1) || swapMap.containsKey(tag2)) {
            return false;
        }
        if (swapMap.containsValue(tag1) || swapMap.containsValue(tag2)) {
            return false;
        }
        swapMap.put(tag1, tag2);
        return true;
    }

    public boolean removeNodeByTag(String tag) {
        T node = null;
        for (T t : onceCollection) {
            if (t.getTag().equals(tag)) {
                node = t;
            }
        }
        if (node != null) {
            return onceCollection.remove(node);
        }

        for (T t : alwaysCollection) {
            if (t.getTag().equals(tag)) {
                node = t;
            }
        }
        return alwaysCollection.remove(node);
    }


    /**
     * 获取唤醒时最后的新排序集合(once集合自动清空)
     */
    protected NodeArray getInvokeCollection() {
        NodeArray nodeArray = new NodeArray();
        nodeArray.addAll(onceCollection);
        nodeArray.addAll(alwaysCollection);
        onceCollection.clear();
        arraySort(nodeArray);
        return nodeArray;
    }

    private void arraySort(NodeArray array) {
        //sort
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (array.get(j).compareTo(array.get(j - 1)) < 0) {
                    array.swap(j, j - 1);
                } else {
                    break;
                }
            }
        }
        //swap
        for (String s : swapMap.keySet()) {
            array.swap(s, swapMap.get(s));
        }
        swapMap.clear();
    }

    protected class NodeArray extends ArrayList<T> {
        protected void swap(int i, int j) {
            T t = get(i);
            set(i, get(j));
            set(j, t);
        }

        protected void swap(String tag1, String tag2) {
            T t1 = getNodeByTag(tag1);
            T t2 = getNodeByTag(tag2);
            swap(this.indexOf(t1), this.indexOf(t2));
        }
    }
}
