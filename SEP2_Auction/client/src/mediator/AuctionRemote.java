package mediator;

import model.Auction;
import model.AuctionList;
import utility.observer.listener.GeneralListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface AuctionRemote extends Remote
{
  Auction startAuction(String title, String description,
      int reservePrice, int buyoutPrice, int minimumIncrement, int auctionTime,
      byte[] imageData) throws RemoteException, SQLException, ClassNotFoundException;
  Auction getAuction(int ID) throws RemoteException, SQLException;
  AuctionList getOngoingAuctions() throws RemoteException, SQLException;
  boolean addListener(GeneralListener<String, Object> listener,
      String... propertyNames) throws RemoteException;
  boolean removeListener(GeneralListener<String, Object> listener,
      String... propertyNames) throws RemoteException;
}
