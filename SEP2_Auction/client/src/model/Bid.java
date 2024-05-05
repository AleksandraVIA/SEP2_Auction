package model;

public class Bid {

  private double reservePrice;
  private double highestBid;
  private double oldHighestBid;

  public Bid(double reservePrice, double highestBid, double oldHighestBid) {
    this.reservePrice = reservePrice;
    this.highestBid = highestBid;
    this.oldHighestBid = oldHighestBid;
  }

  public double getReservePrice() {return reservePrice;}
  public double getHighestBid() {return highestBid;}
  public double getOldHighestBid() {return oldHighestBid;}


  public void setHighestBid(double bidAmount) {this.highestBid = bidAmount;}
  public void setReservePrice() {this.reservePrice = reservePrice;}
  public void setOldHighestBid(){this.oldHighestBid = oldHighestBid;}

  public boolean isValidBid(double bidAmount) {
    if (bidAmount <= 0) {
      throw new IllegalArgumentException(
          "Bid amount must be greater than zero.");
    }

    if (bidAmount < reservePrice) {
      throw new IllegalArgumentException(
          "Bid amount must be at least the reserve price.");
    }

    if (bidAmount <= highestBid) {
      throw new IllegalArgumentException(
          "Bid amount must be higher than the highest bid.");
    }
    return true;
  }




  }


