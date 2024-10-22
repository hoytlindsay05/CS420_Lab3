import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MutexManagerInterface extends Remote {
    void requestEntry(ProcessInterface process, VectorClock clock) throws RemoteException;
    void releaseEntry(ProcessInterface process) throws RemoteException;
}
