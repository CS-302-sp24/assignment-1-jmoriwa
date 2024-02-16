import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;
  private int thinkCount = 0;
  private int cycles;
  private int maxThink;
  private int maxEat;
  private boolean rightHand;
  private String id;

  public Philosopher(Chopstick left, Chopstick right, int cycles, int maxThink, int maxEat, boolean rightHanded, int id) {
    this.left = left; this.right = right;
    this.cycles = cycles;
    this.maxThink = maxThink;
    this.maxEat = maxEat;
    this.rightHand = rightHanded;
    this.id = "Philosopher " + id;
    random = new Random();
  }

  public void run() {
    try {
      boolean forever = false;
      if (cycles == 0) {forever = true;}
      while(forever || cycles > 0) {
        thinkCount += 1;
        if (thinkCount % 10 == 0) {System.out.println(id + " has thought " + thinkCount + " times");}
        int think = random.nextInt(maxThink);
        Thread.sleep(think);     // Think for a while
        System.out.println(id +" has thought for " + think + " units");
        if (rightHand) {
          synchronized(right) {
            System.out.println(id + " wants right fork");
            synchronized(left) {
              System.out.println(id + " wants left fork");
              int eat = random.nextInt(maxEat);
              Thread.sleep(eat);
              System.out.println(id + " has eaten for " + eat + " units");
            }
          }
        }
        else {
          synchronized(left) {                    
            System.out.println(id + " wants left fork");
            synchronized(right) {     
              System.out.println(id + " wants right fork");    
              int eat = random.nextInt(maxEat);        
              Thread.sleep(eat); // Eat for a while
              System.out.println(id + " has eaten for " + eat + " units");
          }
        }
      }
      cycles -= 1;
      }
    } catch(InterruptedException e) {}
  }
}
