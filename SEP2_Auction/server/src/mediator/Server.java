package mediator;

import model.Auction;
import model.Model;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeEvent;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteAuction {
  private Model model;
  private PropertyChangeHandler<String, String> remoteProperty;

  public Server(Model model) throws RemoteException, MalformedURLException {
    this.model = model;
    model.addListener("time", this);
    model.addListener("end", this);

    this.remoteProperty = new PropertyChangeHandler<>(this, true);
  }

  public void startServer()
      throws RemoteException, MalformedURLException, AlreadyBoundException {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.bind("Auction", this);
  }

  @Override public Auction startAuction(long ID, String title,
      String description, int reservePrice, int buyoutPrice,
      int minimumIncrement, int auctionTime, String imagePath)
      throws RemoteException {

    return model.startAuction(ID, title, description, reservePrice, buyoutPrice, minimumIncrement, auctionTime, imagePath);
  }

 //ArrayList and DB needed for this
  @Override public long generateID() throws RemoteException {
    return 0;
  }
  @Override public Auction getAuction(long ID) throws RemoteException {
    return null;
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException {
    return remoteProperty.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
      throws RemoteException {
    return remoteProperty.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    if(evt.getPropertyName().equals("time")){
      remoteProperty.firePropertyChange(evt.getPropertyName(), null, (String) evt.getNewValue());
      System.out.println((String) evt.getNewValue());
    }
    else if (evt.getPropertyName().equals("end")) {
      //sends
      remoteProperty.firePropertyChange(evt.getPropertyName(), "", " ");
    }
  }
}
