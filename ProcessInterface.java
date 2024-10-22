import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProcessInterface extends Remote {
    int getProcessId() throws RemoteException;
}
