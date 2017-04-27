package com.thread.notifyandwait.oneToMulti;

import java.util.*;

/**
 * Created by i325622 on 3/22/17.
 */
public class Game implements Runnable{
    private Set<Athlete> players = new HashSet<Athlete>();
    private boolean start = false;

    public void addPlayer(Athlete one) {
        players.add(one);
    }

    public void removePlayer(Athlete one) {
        players.remove(one);
    }

    public Collection<Athlete> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public synchronized void go() {
        notifyAll();
    }

    public void ready() throws InterruptedException {
        Iterator<Athlete> iter = getPlayers().iterator();
        List<Thread> threadList = new ArrayList<>();
        while (iter.hasNext()) {
            Thread thread = new Thread(iter.next());
            threadList.add(thread);
        }

        for(int i=0;i<threadList.size();i++) {
            threadList.get(i).start();
        }

        for(int i=0;i<threadList.size();i++) {
            threadList.get(i).join(10);
        }
    }

    public void prepare(Athlete athlete) throws InterruptedException {
        System.out.println(athlete + " ready!");
        synchronized (this) {
            while(!start) {
                wait();
            }
            if(start)
                System.out.println(athlete + " go!");
        }
    }

    @Override
    public void run() {
        start = false;
        System.out.println("Ready......");
        System.out.println("Ready......");
        System.out.println("Ready......");
        try {
            ready();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            Thread.currentThread().sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        start = true;
        System.out.println("Go!");
        go();
    }

    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 0; i < 10; i++)
            game.addPlayer(new Athlete(i, game));
        new Thread(game).start();
    }


    static class Athlete implements Runnable {
        private final int id;
        private Game game;

        public Athlete(int id, Game game) {
            this.id = id;
            this.game = game;
        }

        public boolean equals(Object o) {
            if( !(o instanceof Athlete))
                return false;
            return ((Athlete) o).getId() == id;
        }

        public String toString() {
            return "Athlete<" + id  + ">";
        }

        public int hashCode() {
            return new Integer(id).hashCode();
        }

        @Override
        public void run() {
            try {
                game.prepare(this);
            } catch (InterruptedException e) {
                System.out.println(this + "quit the game!");
            }
        }

        public int getId() {
            return id;
        }
    }
}
