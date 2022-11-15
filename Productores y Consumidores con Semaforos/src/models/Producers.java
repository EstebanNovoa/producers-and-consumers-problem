/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author novoa
 */
public class Producers extends Thread{
    
    private int fill;
    private int use;
    private int[] buffer;
    private int max;
    private Semaphore semaphoreConsumer;
    private Semaphore semaphoreProducer;
    
    public Producers(int max,int[] buffer,Semaphore semaphoreProducer, Semaphore semaphoreConsumer) {
        super();
        this.buffer = buffer;
        this.fill = 0;
        this.use = 0;
        this.semaphoreConsumer = semaphoreConsumer;
        this.semaphoreProducer = semaphoreProducer;
        start();
    }
    
    private void put(int value){
        buffer[fill]  = value;
        fill = (fill + 1) % max;
    }
    
    
    private void putItem(int item){
        try {
            // Before producer can produce an item,
            // it must acquire a permit from semProd
            semaphoreProducer.acquire();
        }
        catch (InterruptedException e) {
            System.out.println("Error");
        }
        // producer producing an item
        put(item);
        System.out.println("Item producido: " + item);
        //Luego de producir el item notifica al consumidor que lo generó
        semaphoreConsumer.release();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < max; i++){
            putItem(i);
        }
    }

    
    
    
    
    
    
}
