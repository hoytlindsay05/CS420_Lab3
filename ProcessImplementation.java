import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProcessImplementation extends UnicastRemoteObject implements ProcessInterface {
    private int processId;
    private MutexManagerInterface mutexManager;
    private VectorClock clock;

    public ProcessImplementation(int processId, MutexManagerInterface mutexManager) throws RemoteException {
        this.processId = processId;
        this.mutexManager = mutexManager;
        this.clock = new VectorClockImplementation(3); // Assuming 3 processes
    }

    public void requestCriticalSection() {
        try {
            System.out.println("Process " + processId + " requesting critical section...");
            clock.increment(processId); // Increment the clock for this process
            mutexManager.requestEntry(this, clock); // Pass 'this' and clock
            System.out.println("Process " + processId + " has entered the critical section.");
            // Simulate some work in the critical section
            Thread.sleep(2000); // Simulating work
            releaseCriticalSection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseCriticalSection() {
        try {
            mutexManager.releaseEntry(this); // Pass 'this'
            System.out.println("Process " + processId + " has released the critical section.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getProcessId() {
        return processId;
    }
}
