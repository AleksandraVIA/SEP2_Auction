package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface Model extends NamedPropertyChangeSubject,
    PropertyChangeListener {
  Auction startAuction(long ID, String title, String description,
      int reservePrice, int buyoutPrice, int minimumIncrement, int auctionTime,
      String imagePath);

  Auction startAuction(String title, String description,
      int reservePrice, int buyoutPrice, int minimumIncrement, int auctionTime,
      String imagePath);


  //don't need that?
  long generateID() ;

  Auction getAuction(long ID) ;
}
