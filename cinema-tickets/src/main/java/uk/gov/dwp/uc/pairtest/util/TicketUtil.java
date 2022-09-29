package uk.gov.dwp.uc.pairtest.util;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static uk.gov.dwp.uc.pairtest.constant.TicketConstants.TicketPrice.*;

public class TicketUtil {

    public static int calculatePrice(TicketTypeRequest.Type type, int noOfTickets) throws InvalidPurchaseException {

        int price = 0;

        switch (type) {
            case ADULT:
                price = noOfTickets * ADULT_PRICE.price;
                break;
            case INFANT:
                price = noOfTickets * INFANT_PRICE.price;
                break;
            case CHILD:
                price = noOfTickets * CHILD_PRICE.price;
                break;
            default:
                price = 0;
        }

        if( price > Integer.MAX_VALUE || price < 0 )
            throw new InvalidPurchaseException("Unable to proceed the request for this request");
        return price;

    }
}
