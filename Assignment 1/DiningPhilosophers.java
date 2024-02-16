public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {
    int num = Integer.parseInt(args[0]);
    int cycles = Integer.parseInt(args[1]);
    int maxThink = Integer.parseInt(args[2]);
    int maxEat = Integer.parseInt(args[3]);

    boolean difHand = Integer.parseInt(args[4]) == 1 ? true : false;

    Philosopher[] philosophers = new Philosopher[num];
    Chopstick[] chopsticks = new Chopstick[num];
    
    for (int i = 0; i < num; ++i)
      chopsticks[i] = new Chopstick(i);
    
    for (int i = 0; i < num; ++i) {
      boolean rightHand;
      if (difHand){
        rightHand = i % 2 == 0 ? true : false;
      }
      else{rightHand = true;}
      
      
      philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % num], cycles, maxThink, maxEat, rightHand, i);
      philosophers[i].start();
    }
    for (int i = 0; i < num; ++i)
      philosophers[i].join();
  }
}
