package buildings.sup;

public class SemaphorePlace {

    private boolean enable = true;

    synchronized public boolean isEnable() {
        return enable;
    }

    synchronized public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
