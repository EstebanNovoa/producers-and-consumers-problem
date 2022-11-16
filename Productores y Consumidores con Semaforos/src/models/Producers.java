
import java.util.concurrent.Semaphore;

class Producer implements Runnable{
    
    Semaphore semaphoreProducer;
    Semaphore semaphoreConsumer;
    int[] buffer;
    int fill;
    
    
    public Producer(Semaphore semaphoreProducer,Semaphore semaphoreConsumer, int[] buffer) {
           this.semaphoreProducer=semaphoreProducer;
           this.semaphoreConsumer=semaphoreConsumer;
           this.buffer = buffer;
           this.fill = 0;
    }
    
    void put(int value){
        this.buffer[fill] = value;
        fill = (fill + 1) % buffer.length;
    }
    
    public void run() {
           for(int i=1;i<=5;i++){
                  try {
                      //Revisa la disposición
                      semaphoreProducer.acquire();
                      System.out.println("Item producido : "+i);
                      put(i);
                      //Notifica al consumidor
                      semaphoreConsumer.release();
                        
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
           }          
    }
}