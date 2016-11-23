package com.fob.tasks.emre.services;

/**
 * A basic int stacking service 
 * @author Emre
 */
public interface IStackService {

    /**
     * Push int item to the top of the stack
     * @param item
     */
    void push(int item);

    /**
     * Pop int item from the top of the stack
     * @return
     */
    int pop();

    /**
     * View stack elements in the order from bottom to top
     * @return
     */
    int[] view();
}
