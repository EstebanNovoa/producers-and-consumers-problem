
import java.util.concurrent.Semaphore;

/**
 * Copyright (c), AnkitMittal JavaMadeSoEasy.com
 */
public class Main {

    public static void main(String[] args) {
        int[] buffer = new int[5] ;
        Semaphore semaphoreProducer = new Semaphore(1);
        Semaphore semaphoreConsumer = new Semaphore(0);
        Producer producer = new Producer(semaphoreProducer, semaphoreConsumer,buffer);
        Consumer consumer = new Consumer(semaphoreConsumer, semaphoreProducer,buffer);
        Thread producerThread = new Thread(producer, "ProducerThread");
        Thread consumerThread = new Thread(consumer, "ConsumerThread");
        producerThread.start();
        consumerThread.start();

    }
}
