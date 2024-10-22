import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) {
        try {
            // Start the RMI registry programmatically on port 1100
            try {
                LocateRegistry.createRegistry(1100);  // Start the registry
                System.out.println("RMI registry started on port 1100");
            } catch (RemoteException e) {
                System.out.println("RMI registry may already be running: " + e.getMessage());
            }

            // Create and bind the MutexManager instance
            MutexManagerInterface mutexManager = new MutexManagerImplementation();
            Naming.rebind("//localhost:1100/MutexManager", mutexManager);  // Bind MutexManager to registry
            System.out.println("MutexManager bound in registry");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
