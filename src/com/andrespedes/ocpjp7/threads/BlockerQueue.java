package com.andrespedes.ocpjp7.threads;
import java.util.concurrent.locks.*;

class BlockerQueue {
    // remember the max size of the queue
    private int size = 0;

    // array to store the elements in the queue
    private Object elements[];

    // pointer that points to the current element in the queue
    private int currPointer = 0;

    // internal lock used for synchronized access to the BlockerQueue
    private Lock internalLock = new ReentrantLock();

    // condition to wait for when queue is empty that makes use of the common lock
    private Condition empty = internalLock.newCondition();

    // condition to wait for when queue is full that makes use of the common lock
    private Condition full =  internalLock.newCondition();

    public BlockerQueue(int size) {
        this.size = size;
        elements = new Object[size];
    }

    // remove an element if available; or if there are no elements in the queue,
    // await insertion of an element. Once an element is inserted, notify to any threads
    // waiting for insertion in a full queue
    public Object remove() {
        Object element = null;
        internalLock.lock();

        try {
            if(currPointer == 0) {
                System.out.println("In remove(): no element to remove, so waiting for insertion");
                // cannot remove - no elements in the queue;
                // so block until an element is inserted
                empty.await();
                // if control reaches here, that means some thread completed
                // calling insert(), so proceed to remove that element
                System.out.println("In remove(): got notification that an element has got inserted");
            }
            // decrement the currPointer and then get the element
            element = elements[--currPointer];
            System.out.println("In remove(): removed the element " + element);

            // an element is removed, so there is space for insertion
            // so notify any threads waiting to insert
            full.signalAll();
            System.out.println("In remove(): signalled that there is space for insertion");
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            internalLock.unlock();
        }
        return element;
    }

    // insert an element if there is space for insertion. if queue is full,
    // await for remove() to be called and get signal to proceed for insertion.
    // after insertion, signal any awaiting threads in case of an empty queue.
    public void insert(Object element) {
        internalLock.lock();
        try {
            if(currPointer == size) {
                System.out.println("In insert(): queue is full, so waiting for removal");
                // cannot insert - the queue is full;
                // so block until an element is removed
                full.await();
                // if control reaches here, that means some thread completed
                // calling remove(), so proceed to insert this element
                System.out.println("In insert(): got notification that remove got called, so proceeding to insert the element");
            }
            // get the element and after that decrement the currPointer
            elements[currPointer++] = element;
            System.out.println("In insert(): inserted the element " + element);
            // an element is inserted, so any other threads can remove it...
            // so notify any threads waiting to remove
            empty.signalAll();
            System.out.println("In insert(): notified that queue is not empty");
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            internalLock.unlock();
        }
    }
}

class BlockerQueueTest1 {
    public static void main(String []args) {
        final BlockerQueue blockerQueue = new BlockerQueue(2);
        new Thread() {
            public void run() {
                System.out.println("Thread1: attempting to remove an item from the queue ");
                Object o = blockerQueue.remove();
            }
        }.start();

        new Thread() {
            public void run() {
                System.out.println("Thread2: attempting to insert an item to the queue");
                blockerQueue.insert("one");
            }
        }.start();
    }
}
