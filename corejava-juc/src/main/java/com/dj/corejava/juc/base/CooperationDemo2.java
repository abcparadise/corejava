 package com.dj.corejava.juc.base;

 public class CooperationDemo2 {
     
     private int number = 10;
     private final Object numberLock = new Object();

     private class EvenTask implements Runnable {
         @Override
         public void run() {
             synchronized (numberLock) {
                 while (number >= 0 && (number & 1) == 0) {
                     System.out.println("偶数: " + (number--));
                     numberLock.notify();
                     try {
                         numberLock.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }
     }

     private class OddTask implements Runnable {
         @Override
         public void run() {
             synchronized (numberLock) {
                 while (number >= 0 && (number & 1) == 1) {
                     System.out.println("奇数: " + (number--));
                     numberLock.notify();
                     try {
                         numberLock.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }
     }
     
    public static void main(String[] args) {
        CooperationDemo2 demo = new CooperationDemo2();
        new Thread(demo.new EvenTask()).start();
        new Thread(demo.new OddTask()).start();

    }

}