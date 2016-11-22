package com.fob.tasks.emre.services;

import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

/**
 * A basic int stacking service implementation.
 * @author Emre
 */
@Service
public class StackServiceBasicImpl implements IStackService {

    private final LinkedList<Integer> stack = new LinkedList<Integer>();

    @Override
    public void push(int item) {
        stack.addLast(item);
    }

    @Override
    public int pop() {
        return stack.removeLast();
    }

    @Override
    public int[] view() {
        return Arrays.stream(stack.toArray(new Integer[stack.size()])).mapToInt(i -> i).toArray();
    }
}
