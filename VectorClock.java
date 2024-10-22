import java.io.Serializable;

public interface VectorClock extends Serializable {
    void increment(int processId);
    int[] getClockArray();
    void update(VectorClock other);
}
