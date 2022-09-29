package uk.gov.dwp.uc.pairtest.constant;

public class TicketConstants {

    public static String STR_ADULT = "ADULT" ;
    public static String STR_CHILD = "CHILD" ;
    public static String STR_INFANT = "INFANT" ;

    public enum TicketPrice {
        ADULT_PRICE(20), CHILD_PRICE(10), INFANT_PRICE(0);
        public final Integer price;
        private TicketPrice(Integer price) {
            this.price = price;
        }
    }
}
