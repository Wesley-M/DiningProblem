package com.company;

import java.util.concurrent.Semaphore;

public class DinnerTableSemaphore {

    public static int numberOfPhilosophers = 2;
    public static Semaphore mutex = new Semaphore(1);
    public static PhilosopherSemaphore[] philosophers = new PhilosopherSemaphore[numberOfPhilosophers];

    public static void takeForks(int id) throws InterruptedException {
        mutex.acquire();
        philosophers[id].state = "HUNGRY";

        if(!philosophers[left(id)].state.equals("EATING") && !philosophers[right(id)].state.equals("EATING")) {
            philosophers[id].lock.release();
            philosophers[id].state = "EATING";
        }

        mutex.release();
        philosophers[id].lock.acquire();
    }

    public static void putForks(int id) throws InterruptedException {
        mutex.acquire();
        philosophers[id].state = "THINKING";

        if (philosophers[right(id)].state.equals("HUNGRY") && !philosophers[right(right(id))].state.equals("EATING")) {
            philosophers[right(id)].state = "EATING";
            philosophers[right(id)].lock.release();
        }

        if (philosophers[left(id)].state.equals("HUNGRY") && !philosophers[left(left(id))].state.equals("EATING")) {
            philosophers[left(id)].state = "EATING";
            philosophers[left(id)].lock.release();
        }

        mutex.release();
    }

    public static int left(int i) {
        return (i + 1) % numberOfPhilosophers;
    }

    public static int right(int i) {
        return (i + numberOfPhilosophers - 1) % numberOfPhilosophers;
    }

    public static void main(String[] args) {
        for (int i = 0; i < numberOfPhilosophers; i++) {
            philosophers[i] = new PhilosopherSemaphore(i);
        }
    }
}
