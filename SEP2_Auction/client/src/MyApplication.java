import javafx.application.Application;
import javafx.stage.Stage;
import mediator.AuctionClient;
import model.AuctionModel;
import model.Bid;
import model.CacheProxy;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.sql.SQLException;

public class MyApplication extends Application
{
  @Override
  public void start(Stage primaryStage) {
    try {
      AuctionModel auctionModel = new CacheProxy();
      ViewModelFactory viewModelFactory = new ViewModelFactory(auctionModel);
      ViewHandler view = new ViewHandler(viewModelFactory);
      AuctionClient client = new AuctionClient();
      view.start(primaryStage);
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }
}
