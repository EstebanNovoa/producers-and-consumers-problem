/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.concurrent.Semaphore;

/**
 *
 * @author novoa
 */
public class Main{
    
    private Semaphore semaphoreConsumer;
    private Semaphore semaphoreProducer;
    private int[] buffer;
    private Consumer consumer;
    private Producers producer;
    
    public Main() {
        this.semaphoreConsumer = new Semaphore(0);
        this.semaphoreProducer = new Semaphore(1);
        this.consumer = new Consumer(5, buffer,semaphoreProducer,semaphoreConsumer);
        this.producer = new Producers(5, buffer,semaphoreProducer,semaphoreConsumer);
    }
    
 
    public static void main(String[] args) {
        new Main();
    }
    
    
    
}
