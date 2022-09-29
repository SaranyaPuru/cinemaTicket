package uk.gov.dwp.uc.pairtest;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class TicketServiceImplTest {

    @Test
    public void testSuccess() throws InvalidPurchaseException {
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10);
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);
        assertTrue(new TicketServiceImpl().purchaseTickets(new Random().nextLong(), new TicketTypeRequest[]{adultRequest, childRequest, infantRequest}));
    }


    @Test(expected = InvalidPurchaseException.class)
    public void testNoOfTickets() throws InvalidPurchaseException {
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10);
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 9);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);
        new TicketServiceImpl().purchaseTickets(new Random().nextLong(), new TicketTypeRequest[]{adultRequest, childRequest, infantRequest});
    }

    @Test(expected = InvalidPurchaseException.class)
    public void testWithInvalidSeats() throws InvalidPurchaseException {
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, Integer.MAX_VALUE);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);
        new TicketServiceImpl().purchaseTickets(new Random().nextLong(), new TicketTypeRequest[]{childRequest, infantRequest});
    }



}
