import mediator.Server;
import model.Model;
import model.ModelManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Main {
  public static void main(String[] args)
      throws MalformedURLException, RemoteException {
    Model modelManager = new ModelManager();
    modelManager.startAuction(1, "heheh", "cool", 10, 20, 15, 23, "/C:/ns");

  }
}