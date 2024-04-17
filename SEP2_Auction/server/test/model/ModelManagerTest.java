package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest {
    private Model model;
    @BeforeEach void setUp(){model = new ModelManager();}

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

    @Test
    void startAuction_with_correct_input(){
        assertDoesNotThrow(() -> model.startAuction("title","description", 69,420,2,12,"/path/to/image"));
    }

    @Test
    void start_Auction_with_empty_title(){
        assertThrows(Exception.class,() -> model.startAuction("","description", 69,420,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_null_title(){
        assertThrows(Exception.class,() -> model.startAuction(null,"description", 69,420,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_long_title(){
        final String title = "TITLETILEISADDSASDASDSADSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

        assertThrows(Exception.class,() -> model.startAuction(title,"description", 69,420,2,12,"/path/to/image"));
    }

    @Test
    void start_Auction_with_empty_description(){
        assertThrows(Exception.class,() -> model.startAuction("title","", 69,420,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_null_description(){
        assertThrows(Exception.class,() -> model.startAuction("title",null, 69,420,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_long_description(){
        final String description = " This exquisite piece of artistry and craftsmanship is a testament to the f" +
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

        assertThrows(Exception.class,() -> model.startAuction("title",description, 69,420,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_zero_reservedPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 0,420,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_negative_reservedPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", -20,420,2,12,"/path/to/image"));
    }

    @Test
    void start_Auction_with_boundary_reservedPrice(){
        assertDoesNotThrow(() -> model.startAuction("title","description", 99999999,420,2,12,"/path/to/image") );
    }
    @Test
    void start_Auction_with_zero_buyoutPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 69,0,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_negative_buyoutPrice(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 69,-420,2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_boundary_buyoutPrice(){
        assertDoesNotThrow(() -> model.startAuction("title","description", 69,999999999,2,12,"/path/to/image") );
    }
    @Test
    void start_Auction_with_zero_minimumIncrement(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 69,420,0,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_negative_minimumIncrement(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 69,420,-2,12,"/path/to/image"));
    }
    @Test
    void start_Auction_with_boundary_minimumIncrement(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 69,420,999999999,12,"/path/to/image"));
    }

    @Test
    void start_Auction_with_zero_auctionTime(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 0,420,2,0,"/path/to/image"));
    }
    @Test
    void start_Auction_with_negative_auctionTime(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 0,420,2,-12,"/path/to/image"));
    }

    @Test
    void start_Auction_with_outOfBoundary_auctionTime(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 0,420,2,25,"/path/to/image"));
    }


    @Test
    void start_Auction_with_empty_imagePath(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 69,420,2,12,""));
    }
    @Test
    void start_Auction_with_null_imagePath(){
        assertThrows(Exception.class,() -> model.startAuction("title","description", 69,420,2,12,null));
    }
    @Test
    void start_Auction_toString(){
        Auction auction = model.startAuction("title","description", 69,420,2,12,"/path/to/image");
        String result = assertDoesNotThrow(() -> auction.toString());
        //  how am I supposed to check the property in toString
        assertEquals("ID=0, title='null', description='null', reservePrice=0, buyoutPrice=0, minimumIncrement=0, auctionTime=0, imagePath='null', timer=null, property=java.beans.PropertyChangeSupport@19dc67c2}",result);
    }
}