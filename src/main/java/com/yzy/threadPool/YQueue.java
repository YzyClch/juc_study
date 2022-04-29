package com.yzy.threadPool;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;


/**
 * 双向链表队列实现
 * @param <T>
 */
public class YQueue<T> implements Queue<T> {

    private Node<T> first;
    private final int limit;
    private int elementSize;

    YQueue(int limit){
        this.limit=limit;
    }


    @Override
    public int size() {
        return elementSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size()>=limit){
            throw new IllegalArgumentException("超出长度限制！");
        }
        if (elementSize++==0){
            first=new Node<>(null,t);
        }else {
            Node<T> f = this.first;
            while (f.left!=null)f=f.left;
            f.left=new Node<>(null,t);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        Node<T> f = this.first;
        if (f!=null){
            if (f.left!=null){
                first=f.left;
            }
        }
        return f !=null ? f.element : null;
    }

    public T last(){
        Node<T> f = this.first;
        if (f!=null){
            Node<T>p = null;
            if (f.left!=null){
                for (;;) {
                    if (f.left!=null){
                        p=f;
                        f=f.left;
                    }else {
                        p.left=null;
                        break;
                    }
                }
            }else {
                first=null;
            }
        }
        return f != null ? f.element : null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    public static void main(String[] args) {
        YQueue<Object> queue = new YQueue<>(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.last());
        System.out.println(queue.last());
        queue.add(4);
        System.out.println(queue.last());
        queue.add(5);
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.poll());
        }


    }


    static private class Node<T>{
        Node<T> left;
        T element;
        private Node(Node<T> left, T element){
            this.left=left;
            this.element=element;
        }
    }
}
