package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Auction
    implements NamedPropertyChangeSubject, PropertyChangeListener {
  private long ID;
  private String title;
  String description;
  int reservePrice, buyoutPrice, minimumIncrement, auctionTime;
  String imagePath;

  private Timer timer;
  private PropertyChangeSupport property;

  public Auction(long ID, String title, String description, int reservePrice,
      int buyoutPrice, int minimumIncrement, int auctionTime,
      String imagePath) {
    property = new PropertyChangeSupport(this);

    setID(ID);
    setTitle(title);
    setDescription(description);
    setReservePrice(reservePrice);
    setMinimumIncrement(minimumIncrement);
    setBuyoutPrice(buyoutPrice);
    setAuctionTime(auctionTime);
    setImagePath(imagePath);

    this.timer = new Timer(this.auctionTime);
    this.timer.addListener("time", this);
    this.timer.addListener("end", this);
    Thread t = new Thread(timer);
    t.start();

  }

  public Auction(String title, String description, int reservePrice,
      int buyoutPrice, int minimumIncrement, int auctionTime,
      String imagePath) {

    property = new PropertyChangeSupport(this);
    new Auction(generateID(), title, description, reservePrice, buyoutPrice, minimumIncrement, auctionTime, imagePath);
  }

  public static long generateID() {
    //generate from 0 to 1000000
    long upperBound = 1000000L;
    return ThreadLocalRandom.current().nextLong(upperBound);
  }

  public long getID() {
    return ID;
  }

  public void setID(long ID) {
    this.ID = ID;
  }

  public synchronized String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    int maxTitleLength = 100;
    if (title.length() > maxTitleLength){
      throw new IllegalArgumentException("Title is too long!");
    }
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    int maxDescriptionLength = 1000;
    if (title.length() > maxDescriptionLength){
      throw new IllegalArgumentException("Description is too long!");
    }
    this.description = description;
  }

  public int getReservePrice() {
    return reservePrice;
  }

  public void setReservePrice(int reservePrice) {
    if (reservePrice <= 0){
      throw new IllegalArgumentException("Reserved price has too be more than 0");
    }
    this.reservePrice = reservePrice;
  }

  public int getBuyoutPrice() {
    return buyoutPrice;
  }

  public void setBuyoutPrice(int buyoutPrice) {
    if (buyoutPrice < minimumIncrement){
      throw new IllegalArgumentException("Buyout price has to be bigger than minimum increment");
    }
    this.buyoutPrice = buyoutPrice;
  }

  public int getMinimumIncrement() {
    return minimumIncrement;
  }

  public void setMinimumIncrement(int minimumIncrement) {
    if (minimumIncrement <= 0){
      throw new IllegalArgumentException("Minimum increment has too be more than 0");
    }
    this.minimumIncrement = minimumIncrement;
  }

  public void setAuctionTime(int auctionTime) {
    if (auctionTime <= 0 || auctionTime > 24){
      throw new IllegalArgumentException("Auction can only last from 1h to 24h");
    }
    int auctionTimeInHours = auctionTime * 3600;
    this.auctionTime = auctionTimeInHours;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override public String toString() {
    return "ID=" + ID + ", title='" + title + '\''
        + ", description='" + description + '\'' + ", reservePrice="
        + reservePrice + ", buyoutPrice=" + buyoutPrice + ", minimumIncrement="
        + minimumIncrement + ", auctionTime=" + auctionTime + ", imagePath='"
        + imagePath + '\'' + ", timer=" + timer + ", property=" + property
        + '}';
  }

  @Override public boolean equals(Object obj) {
    if (obj == null || this.getClass() != obj.getClass())
      return false;

    Auction auction = (Auction)obj;
    return ID == auction.ID && reservePrice == auction.reservePrice
        && buyoutPrice == auction.buyoutPrice
        && minimumIncrement == auction.minimumIncrement
        && auctionTime == auction.auctionTime && Objects.equals(title,
        auction.title) && Objects.equals(description, auction.description)
        && Objects.equals(imagePath, auction.imagePath);
  }

  @Override synchronized public void addListener(String propertyName,
      PropertyChangeListener listener) {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override public synchronized void removeListener(String propertyName,
      PropertyChangeListener listener) {

    property.removePropertyChangeListener(propertyName, listener);
  }

  @Override public synchronized void propertyChange(PropertyChangeEvent evt) {
    //auction property fires timer evens further
    property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    //System.out.println((String) evt.getNewValue());
  }

}
