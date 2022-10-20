package com.likelion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


class Stack01Test {
    @BeforeEach
    void setUp() {
        // db를 지우는코드
        System.out.println("before each");
    }

    @Test
    @DisplayName("push가 잘되는지")
    void pushTest() {
        Stack01 st = new Stack01();
        st.push(10);
        st.push(20);

        int[] arr = st.getArr();
        //10,20
        Assertions.assertEquals(10, arr[0]);
        Assertions.assertEquals(20, arr[1]);
    }

    @Test
    void pushAndPop() {
        Stack01 st = new Stack01();
        st.push(10);
        st.push(20);

        Assertions.assertEquals(20, st.pop());
        Assertions.assertEquals(10, st.pop());

        st.push(30);
        Assertions.assertEquals(30, st.pop());
       st.pop();

       //Exception예외의 검증
       assertThrows(EmptyStackException.class, () -> {
           st.pop();
       });
    }
    @Test
    void realStack() {
        Stack<Integer> st = new Stack<>();
        assertThrows(EmptyStackException.class, ()-> {
            st.pop();
        });
        st.pop();
    }
    @Test
    void isEmpty() {
        Stack01 st = new Stack01();
        assertTrue(st.isEmpty());
        st.push(10);
        assertFalse(st.isEmpty());
        st.pop();
        assertTrue(st.isEmpty());
    }
}