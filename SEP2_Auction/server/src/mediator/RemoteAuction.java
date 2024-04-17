package mediator;

import model.Auction;
import utility.observer.subject.RemoteSubject;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteAuction extends RemoteSubject<String, String>,
    PropertyChangeListener {
  Auction startAuction(long ID, String title, String description,
      int reservePrice, int buyoutPrice, int minimumIncrement, int auctionTime,
      String imagePath) throws RemoteException;

  long generateID() throws RemoteException;

  Auction getAuction(long ID) throws RemoteException;

}
