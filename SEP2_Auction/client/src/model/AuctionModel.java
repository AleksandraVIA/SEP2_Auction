package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface AuctionModel extends NamedPropertyChangeSubject
{
  Auction startAuction(int ID, String title, String description, int reservePrice, int buyoutPrice, int minimumIncrement, int auctionTime, String imagePath);
  Auction getAuction(int ID);
}
