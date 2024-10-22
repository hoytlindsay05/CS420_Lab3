import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client {
    private ProcessImplementation process;

    public Client(int processId, MutexManagerInterface mutexManager) throws RemoteException {
        process = new ProcessImplementation(processId, mutexManager);
    }

    public void requestCriticalSection() {
        process.requestCriticalSection();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Client <processId>");
            return;
        }

        int processId = Integer.parseInt(args[0]);
        try {
            MutexManagerInterface mutexManager = (MutexManagerInterface) Naming.lookup("//localhost:1100/MutexManager");
            System.out.println("Process " + processId + " connected to MutexManager.");

            Client client = new Client(processId, mutexManager);
            client.requestCriticalSection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
