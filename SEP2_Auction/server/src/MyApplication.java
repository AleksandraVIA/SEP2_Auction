import javafx.application.Application;
import javafx.stage.Stage;
import mediator.AuctionServer;
import model.AuctionModel;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MyApplication extends Application
{
  private AuctionServer server;

  @Override public void start(Stage primaryStage)
  {
    /*
    AuctionModel model = new AuctionModelManager();
    try
    {
      server=new AuctionServer(model);
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

     */
  }
}
