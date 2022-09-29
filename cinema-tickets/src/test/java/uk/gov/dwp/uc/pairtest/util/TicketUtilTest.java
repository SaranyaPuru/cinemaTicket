package uk.gov.dwp.uc.pairtest.util;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TicketUtilTest  {

    @Test
    public void testTicketPrice() throws InvalidPurchaseException {
        assertEquals(160,TicketUtil.calculatePrice(TicketTypeRequest.Type.ADULT,8));
        assertEquals(40,TicketUtil.calculatePrice(TicketTypeRequest.Type.CHILD,4));
        assertEquals(0,TicketUtil.calculatePrice(TicketTypeRequest.Type.INFANT,2));
        assertNotEquals(20,TicketUtil.calculatePrice(TicketTypeRequest.Type.INFANT,2));
    }

    @Test(expected = InvalidPurchaseException.class)
    public void testIllegalRequest() throws InvalidPurchaseException {
        TicketUtil.calculatePrice(TicketTypeRequest.Type.ADULT, Integer.MAX_VALUE);
    }

}