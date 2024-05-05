package viewmodel;

import model.Bid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class BidViewModel {
  private Bid model;
  private List<PropertyChangeListener> listeners;

  public BidViewModel(Bid bid) {
    this.model = bid;
    this.listeners = new ArrayList<>();
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    listeners.add(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    listeners.remove(listener);
  }

  public void placeBid(double bidAmount) {
    try {
      if (model.isValidBid(bidAmount)) {
        if (bidAmount > model.getHighestBid()) {
          double oldHighestBid = model.getHighestBid();
          model.setHighestBid(bidAmount);
          firePropertyChange("highestBid", oldHighestBid, bidAmount);
        }
      }
    } catch (IllegalArgumentException e) {
    }
  }

  private void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    for (PropertyChangeListener listener : listeners) {
      listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
    }
  }
}
