package hwr.oop;

public class Game {
  public void roll(int pinsHit) throws Exception {
    if (pinsHit < 0) {
      throw new Exception("Cannot knock down less than 0 pins");
    }
    if (10 < pinsHit) {
      throw new Exception("Cannot knock down more than 10 pins");
    }
  }
}
