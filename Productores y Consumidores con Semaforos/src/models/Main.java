
import java.util.concurrent.Semaphore;

/**
 * Copyright (c), AnkitMittal JavaMadeSoEasy.com
 */
public class Main {

    public static void main(String[] args) {

        Semaphore semaphoreProducer = new Semaphore(1);
        Semaphore semaphoreConsumer = new Semaphore(0);
        Producer producer = new Producer(semaphoreProducer, semaphoreConsumer);
        Consumer consumer = new Consumer(semaphoreConsumer, semaphoreProducer);
        Thread producerThread = new Thread(producer, "ProducerThread");
        Thread consumerThread = new Thread(consumer, "ConsumerThread");
        producerThread.start();
        consumerThread.start();

    }
}
