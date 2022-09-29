package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.util.TicketUtil;

import static uk.gov.dwp.uc.pairtest.constant.TicketConstants.*;


public class TicketServiceImpl implements TicketService {

    /**
     * Should only have private methods other than the one below.
     */
    @Override
    public Boolean purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {


        int noOfTickets = 0;
        Boolean isAdultExist = false;
        int noOfSeats = 0;
        int price = 0;

        for (TicketTypeRequest request : ticketTypeRequests) {
            noOfTickets += request.getNoOfTickets();
            if (request.getTicketType().name().equalsIgnoreCase(STR_ADULT)) {
                isAdultExist = true;
            }

            if (!request.getTicketType().name().equalsIgnoreCase(STR_INFANT)) {
                noOfSeats = noOfSeats + 1;
            }

            price = price + TicketUtil.calculatePrice(request.getTicketType(),request.getNoOfTickets());

            if(price > Integer.MAX_VALUE || 0 > price)
                throw new InvalidPurchaseException("Unable to proceed the request for this request");

        }

        if (noOfTickets > 20 )
            throw new InvalidPurchaseException("Only a maximum of 20 tickets that can be purchased at a time");

        if (!isAdultExist) {
            throw new InvalidPurchaseException(
                    "Child and Infant tickets cannot be purchased without purchasing an Adult ticket");
        }

        new TicketPaymentServiceImpl().makePayment(accountId, price);
        new SeatReservationServiceImpl().reserveSeat(accountId, noOfSeats);
        return Boolean.TRUE;
    }

}

