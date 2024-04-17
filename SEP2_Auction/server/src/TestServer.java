import mediator.Server;
import model.Model;
import model.ModelManager;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestServer {
  public static void main(String[] args)
      throws MalformedURLException, RemoteException, AlreadyBoundException {

    Model modelManager = new ModelManager();
    modelManager.startAuction(1, "heheh", "cool", 10, 20, 15, 23, "/C:/ns");

    startRegistry();
    Server server = new Server(modelManager);
    server.startServer();
    System.out.println("Server started...");
  }


  public static void startRegistry() throws RemoteException {
    try {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }catch (RemoteException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }
}
