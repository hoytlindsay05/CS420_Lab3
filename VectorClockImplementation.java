import java.io.Serializable;

public class VectorClockImplementation implements VectorClock {
    private static final long serialVersionUID = 1L;
    private int[] clock; // Array to store clock values
    private int size; // Number of processes

    public VectorClockImplementation(int size) {
        this.size = size;
        this.clock = new int[size];
    }

    @Override
    public void increment(int processId) {
        clock[processId]++;
    }

    @Override
    public int[] getClockArray() {
        return clock;
    }

    @Override
    public void update(VectorClock other) {
        int[] otherClock = other.getClockArray();
        for (int i = 0; i < size; i++) {
            this.clock[i] = Math.max(this.clock[i], otherClock[i]);
        }
    }
}
