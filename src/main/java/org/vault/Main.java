package org.vault;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int Max = 9999;
    public static void main(String[] args) {

        Random random = new Random();
        Vault vault = new Vault(random.nextInt(Max));
        List<Runnable> threadList = new ArrayList<>();
        threadList.add(new AscendingHackerThread(vault));
        threadList.add(new DesHackerThread(vault));
        threadList.add(new PoliceThread());

        for(Runnable thread : threadList) {
            thread.run();
        }
    }

    static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {

            }
            return password == guess;
        }
    }

    static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("starting thread");
            super.start();
        }
    }

    static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
           for(int guess=0; guess<Max; guess++) {
               if(vault.isCorrectPassword(guess)) {
                   System.out.println(this.getName() + " guess password" + guess);
                   System.exit(0);
               }
           }
        }
    }

    static class DesHackerThread extends HackerThread {

        public DesHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = Max; guess>0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guess password" + guess);
                    System.exit(0);
                }
            }
        }
    }

    static class PoliceThread extends Thread {

        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {

                }
                System.out.println(i);
            }

            System.out.println("catch hacker");
            System.exit(0);
        }
    }
}
