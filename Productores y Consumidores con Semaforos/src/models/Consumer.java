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
public class Consumer extends Thread{
    
    
    private int use;
    private int[] buffer;
    private int max;
    private Semaphore semaphoreConsumer;
    private Semaphore semaphoreProducer;
    

    public Consumer(int max,int[] buffer,Semaphore semaphoreProducer, Semaphore semaphoreConsumer) {
        super();
        this.buffer = buffer;
        this.use = 0;
        this.semaphoreConsumer = semaphoreConsumer;
        this.semaphoreProducer = semaphoreProducer;
        start();
    }
       
    
    public int get(){
        int tmp = buffer[use];
        use = (use + 1) % max;
        return tmp;
    }
    
    
    
    private void getItem(){
        try {
            //Nos aseguramos que antes de que consuma haya items en el buffer
            semaphoreConsumer.acquire();
        }
        catch (InterruptedException e) {
            System.out.println("Error");
        }
        
        //El consumidor saca el item del buffer
        System.out.println("Item utilizado por el consumidor: " + get());
  
        // Luego de sacar el item notifica al productor
        semaphoreProducer.release();
    }
    
    
    @Override
    public void run() {
        for (int i = 0; i < max; i++){
            getItem();
        }
    }

    
    
}
