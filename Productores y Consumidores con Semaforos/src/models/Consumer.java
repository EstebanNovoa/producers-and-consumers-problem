
import java.util.concurrent.Semaphore;

class Consumer implements Runnable{
 
    Semaphore semaphoreConsumer;
    Semaphore semaphoreProducer;
    int[] buffer;
    int use;
    
    public Consumer(Semaphore semaphoreConsumer,Semaphore semaphoreProducer,int[] buffer) {
           this.semaphoreConsumer=semaphoreConsumer;
           this.semaphoreProducer=semaphoreProducer;
           this.buffer = buffer;
           this.use = 0;
           
    }
 
    private int get(){
        int tmp = buffer[use];
        use = (use + 1) % buffer.length;
        return tmp;
    }
    public void run() {
           
           for(int i=1;i<=5;i++){
                  try {
                      //Revisa disponibilidad de consumo
                      semaphoreConsumer.acquire();
                      System.out.println("Item consumido: "+ get());
                      //Notifica al productor
                      semaphoreProducer.release();
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
           }
    }
    
}