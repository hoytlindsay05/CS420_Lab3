import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;

public class MutexManagerImplementation extends UnicastRemoteObject implements MutexManagerInterface {
    private Queue<ProcessInterface> requestQueue;

    public MutexManagerImplementation() throws RemoteException {
        requestQueue = new LinkedList<>();
    }

    @Override
    public synchronized void requestEntry(ProcessInterface process, VectorClock clock) throws RemoteException {
        System.out.println("Received request from process " + process.getProcessId());
        requestQueue.add(process);
        System.out.println("Current request queue: " + requestQueue);
    }

    @Override
    public synchronized void releaseEntry(ProcessInterface process) throws RemoteException {
        System.out.println("Process " + process.getProcessId() + " released entry.");
        requestQueue.remove(process);
    }
}
