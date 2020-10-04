package com.company;

public class DinnerTableMonitor {

    public static int numberOfPhilosophers = 2;
    public static PhilosopherMonitor[] philosophers = new PhilosopherMonitor[numberOfPhilosophers];

    public static void takeForks(int id) throws InterruptedException {
        philosophers[id].state = "HUNGRY";

        if(!philosophers[left(id)].state.equals("EATING") && !philosophers[right(id)].state.equals("EATING")) {
            philosophers[id].state = "EATING";
        } else {
            synchronized (philosophers[id]) {
                philosophers[id].wait();
            }
        }
    }

    public static void putForks(int id) throws InterruptedException {
        philosophers[id].state = "THINKING";

        if (philosophers[right(id)].state.equals("HUNGRY") && !philosophers[right(right(id))].state.equals("EATING")) {
            philosophers[right(id)].state = "EATING";
            synchronized (philosophers[right(id)]) {
                philosophers[right(id)].notify();
            }
        }

        if (philosophers[left(id)].state.equals("HUNGRY") && !philosophers[left(left(id))].state.equals("EATING")) {
            philosophers[left(id)].state = "EATING";
            synchronized (philosophers[left(id)]) {
                philosophers[left(id)].notify();
            }
        }
    }

    public static int left(int i) {
        return (i + 1) % numberOfPhilosophers;
    }

    public static int right(int i) {
        return (i + numberOfPhilosophers - 1) % numberOfPhilosophers;
    }

    public static void main(String[] args) {
        for (int i = 0; i < numberOfPhilosophers; i++) {
            philosophers[i] = new PhilosopherMonitor(i);
        }
    }
}
