package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
                System.out.println("새로운 스레드 - "+ Thread.currentThread());
                throw new NullPointerException();
        });

        thread.setName("newThread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler((a,b)-> System.out.println("예외발생"));
        System.out.println("새로운 스레드 시작 전 - "+Thread.currentThread());
        thread.start(); // 운체가 비동기처리
        System.out.println("새로운 스레드 시작 후 - "+Thread.currentThread());

        Thread.sleep(1000);

        Thread myThread = new MyThread();
        myThread.start();
    }

    static class MyThread extends Thread {
        // 이렇게 thread를 확장해서 쓰면 정적메소드말고 다양한 메소드를 사용할 수 있다.
        @Override
        public void run() {
            System.out.println("my thread - "+this.getName());
        }
    }

}