package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.AuctionDatabase;
import persistence.AuctionPersistence;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StartAuctionTests {
    private AuctionModel model;
    private AuctionPersistence auctionDatabase;
    private byte[] byteArray;
    @BeforeEach void setUp(){
        try {
            model = new AuctionModelManager();
            auctionDatabase = new AuctionDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedImage image = null;
        try {
            //image = ImageIO.read(new File("images_for_test\\12.jpg"));
            image = ImageIO.read(new File("..\\server\\images\\12.png"));
            ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outStreamObj);

            byteArray = outStreamObj.toByteArray();
        } catch (IOException e) {
            //  ...
        }


    }

    /*
    Z - zero
    O - one
    M - many
    B - boundary
    E - exceptions

     */

    /*
    1. Start Auction
    Z - check for empty titles, null parameters
    O - not relevant
    M - not relevant
    B - not relevant
    E - check for any exceptions
     */


    void test_test_test(){
        try {
            model = new AuctionModelManager();
            auctionDatabase = new AuctionDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedImage image = null;
        try {
            //image = ImageIO.read(new File("images_for_test\\12.jpg"));
            image = ImageIO.read(new File("..\\server\\images\\12.png"));
            ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outStreamObj);

            byteArray = outStreamObj.toByteArray();
            System.out.println(Arrays.toString(byteArray));
            System.out.println("!!!!!!!!!!");
            assertEquals(null,byteArray);
        } catch (IOException e) {
            //  ...
        }
    }

    @Test
    void startAuction_with_correct_input(){
        assertDoesNotThrow(() -> model.startAuction("titletitle","description_description_description_description", 69,420,2,12,null));
    }

    @Test
    void start_Auction_with_empty_title(){
        assertThrows(Exception.class,() -> model.startAuction("","description_description", 69,420,2,12,byteArray));
    }
    @Test
    void start_Auction_with_null_title(){
        assertThrows(Exception.class,() -> model.startAuction(null,"description_description", 69,420,2,12,byteArray));
    }
    @Test
    void start_Auction_with_long_title(){
        final String title = "TITLETILEISADDSASDASDSADSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

        assertThrows(Exception.class,() -> model.startAuction(title,"description_description", 69,420,2,12,byteArray));
    }

    @Test
    void start_Auction_with_empty_description_description(){
        assertThrows(Exception.class,() -> model.startAuction("title","", 69,420,2,12,byteArray));
    }
    @Test
    void start_Auction_with_null_description_description(){
        assertThrows(Exception.class,() -> model.startAuction("title",null, 69,420,2,12,byteArray));
    }
    @Test
    void start_Auction_with_long_description_description(){
        final String description_description = " This exquisite piece of artistry and craftsmanship is a testament to the f" +
                "inest traditions of the Renaissance era, meticulously handcrafted over several years by master " +
                "artisans in Florence, Italy. Its intricate details and breathtaking beauty have captivated art " +
                "enthusiasts and collectors alike for centuries. Featuring a mesmerizing array of colors, " +
                "textures, and patterns, this masterpiece transcends time and space, offering a glimpse i" +
                "nto a bygone era of opulence and refinement. From its meticulously carved figurines to " +
                "its ornate embellishments, every aspect of this artwork tells a story of passion, " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +
                "dedication, and creativity. Displaying unparalleled skill and artistry, this " +
                "extraordinary piece is sure to be the crowning jewel of any collection and a " +

                "cherished heirloom for generations to come\n";

        assertThrows(Exception.class,() -> model.startAuction("title",description_description, 69,420,2,12,byteArray));
    }
    @Test
    void start_Auction_with_zero_reservedPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 0,420,2,12,byteArray));
    }
    @Test
    void start_Auction_with_negative_reservedPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", -20,420,2,12,byteArray));
    }

    @Test
    void start_Auction_with_boundary_reservedPrice(){
        assertDoesNotThrow(() -> model.startAuction("title","description_description", 99999999,420,2,12,byteArray) );
    }
    @Test
    void start_Auction_with_zero_buyoutPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 69,0,2,12,byteArray));
    }
    @Test
    void start_Auction_with_negative_buyoutPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 69,-420,2,12,byteArray));
    }
    @Test
    void start_Auction_with_boundary_buyoutPrice(){
        assertDoesNotThrow(() -> model.startAuction("title","description_description", 69,999999999,2,12,byteArray) );
    }
    @Test
    void start_Auction_with_zero_minimumIncrement(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 69,420,0,12,byteArray));
    }
    @Test
    void start_Auction_with_negative_minimumIncrement(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 69,420,-2,12,byteArray));
    }
    @Test
    void start_Auction_with_boundary_minimumIncrement(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 69,420,999999999,12,byteArray));
    }

    @Test
    void start_Auction_with_zero_auctionTime(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 0,420,2,0,byteArray));
    }
    @Test
    void start_Auction_with_negative_auctionTime(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 0,420,2,-12,byteArray));
    }

    @Test
    void start_Auction_with_outOfBoundary_auctionTime(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 0,420,2,25,byteArray));
    }

    @Test
    void start_Auction_with_null_imagePath(){
        assertThrows(Exception.class,() -> model.startAuction("title","description_description", 69,420,2,12,null));
    }
//    @Test
//    void start_Auction_toString(){
//        Auction auction = null;
//        try {
//            auction = model.startAuction("title","description_description", 69,420,2,12,byteArray);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        String result = assertDoesNotThrow(() -> auction.toString());
//        //  how am I supposed to check the property in toString
//        assertEquals("ID=0, title='null', description_description='null', reservePrice=0, buyoutPrice=0, minimumIncrement=0, auctionTime=0, imagePath='null', timer=null, property=java.beans.PropertyChangeSupport@19dc67c2}",result);
//    }
}