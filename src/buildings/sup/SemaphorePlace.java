package buildings.sup;

import buildings.Floor;

public class SemaphorePlace {
    private int thrMax;
    private int thrCount;
    private Floor block;

    public SemaphorePlace(int curMax,Floor floor) {
        this.thrMax = curMax;
        block = floor;
    }

    public void acquire() {
        synchronized (block) {
            thrCount++;
            if (thrCount > thrMax) {
                try {
                    block.wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }

    }

    public void release() {
        synchronized (block) {
            thrCount--;
            block.notify();
        }
    }
}
