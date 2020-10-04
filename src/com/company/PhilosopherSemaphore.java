package com.company;

import java.util.concurrent.Semaphore;

public class PhilosopherSemaphore implements Runnable {

    public int id;
    public String state;
    public Semaphore lock;

    public PhilosopherSemaphore(int id) {
        this.id = id;
        this.state = "THINKING";
        this.lock = new Semaphore(0);

        new Thread((Runnable) this, "Philosopher " + id).start();
    }

    public void think() {
        System.out.println("Philosopher " + id + " is thinking right now!");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Philosopher " + id + " is not thinking anymore, he is hungry!");
    }

    public void eat() {
        System.out.println("Philosopher " + id + " is eating right now!");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Philosopher " + id + " is not eating anymore!");
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.think();
                DinnerTableSemaphore.takeForks(this.id);
                this.eat();
                DinnerTableSemaphore.putForks(this.id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}