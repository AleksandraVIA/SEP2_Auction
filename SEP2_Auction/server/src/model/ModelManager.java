package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ModelManager implements Model {
  private Auction auction;
  private PropertyChangeSupport property;

  public ModelManager(Auction auction) {
    property = new PropertyChangeSupport(this);
    this.auction = auction;
  }

  public ModelManager() {
    property = new PropertyChangeSupport(this);
  }


  @Override public Auction startAuction(long ID, String title,
      String description, int reservePrice, int buyoutPrice,
      int minimumIncrement, int auctionTime, String imagePath) {

    auction = new Auction(ID, title, description, reservePrice, buyoutPrice, minimumIncrement, auctionTime, imagePath);
    auction.addListener("time", this);
    auction.addListener("end", this);
    return auction;
  }

  @Override public Auction startAuction(String title, String description,
      int reservePrice, int buyoutPrice, int minimumIncrement, int auctionTime,
      String imagePath) {

    auction = new Auction(title, description, reservePrice, buyoutPrice, minimumIncrement, auctionTime, imagePath);
    auction.addListener("time", this);
    auction.addListener("end", this);
    return auction;
  }

  @Override public long generateID() {

    return Auction.generateID();
  }

  @Override public Auction getAuction(long ID) {
    //TODO:change whe ArrayList is used
    return this.auction;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener) {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener) {
    property.removePropertyChangeListener(propertyName, listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    //model manager property fires auction events further
    property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    //System.out.println((String) evt.getNewValue());
  }
}
