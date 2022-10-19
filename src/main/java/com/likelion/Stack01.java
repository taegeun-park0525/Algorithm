package com.likelion;

public class Stack01 {
    private int[] arr = new int[10000];
    private int pointer = 0; //비어있는 상태
    public Stack01() {}

    public Stack01(int size) {  // 외부에서 개수를 받을 수있게 설정
        this.arr = new int[size];
    }

    public void push(int value) {
        this.arr[this.pointer] = value;
        this.pointer ++;
    }

    public int[] getArr() {
        return arr;
    }

    public int pop() {
        //push 10 push 20 point 2
        int value = this.arr[this.pointer-1];
        this.pointer--;
        return value;
    }
}
