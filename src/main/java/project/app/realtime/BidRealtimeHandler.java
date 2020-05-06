package project.app.realtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import project.app.exception.AuctionNotFound;
import project.app.exception.BidderNotFound;
import project.app.exception.InconsistentBid;
import project.app.model.Bid;
import project.app.services.AuctionServices;
import project.app.services.BidderServices;

@Controller
public class BidRealtimeHandler {

	@Autowired
	SimpMessagingTemplate msgt;

	@Autowired
	AuctionServices auctionServices;

	@Autowired
	BidderServices bidderServices;

	@MessageMapping("/auctions/{idAuction}")
	public void handleBidEvent(Bid bid, @DestinationVariable int idAuction) {
		System.out.println("Bid recibida en el servidor: " + bid.toString());
		try {
			if (!bidderServices.validateBid(bid)) {
				System.out.println("Aiiuda alo crack digo... polisia");
				msgt.convertAndSend("/auctions/bid/" + idAuction, "Bid inconsistente - Saldo insuficiente");
			} else {
				auctionServices.updateAuction(bid);
				msgt.convertAndSend("/auctions/bid/"+idAuction, bid);
			}
		} catch (BidderNotFound e1) {
			msgt.convertAndSend("/auctions/bid/" + idAuction, e1.getMessage());
			e1.printStackTrace();
		} catch (AuctionNotFound e) {
			msgt.convertAndSend("/auctions/bid/" + idAuction, e.getMessage());
			e.printStackTrace();
		} catch (InconsistentBid e) {
			msgt.convertAndSend("/auctions/bid/" + idAuction, e.getMessage());
			e.printStackTrace();
		}
	}
}