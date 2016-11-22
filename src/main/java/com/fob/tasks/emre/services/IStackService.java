package com.fob.tasks.emre.services;

public interface IStackService {
    void push(int item);
    int pop();
    int[] view();
}
