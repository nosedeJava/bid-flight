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
<<<<<<< HEAD
    
	@MessageMapping("/auctions/{idAuction}")    
	public void handleBidEvent(Bid bid, @DestinationVariable int idAuction) throws Exception {
		System.out.println("Nuevo bid recibido en el servidor!: "+idAuction+" "+bid.toString());
		msgt.convertAndSend("/app/auctions/"+idAuction, bid);
=======

	@Autowired
	AuctionServices auctionServices;

	@Autowired
	BidderServices bidderServices;

	@MessageMapping("/auctions/{idAuction}")
	public void handleBidEvent(Bid bid, @DestinationVariable int idAuction) {
		System.out.println("Bid recibida en el servidor: " + bid.toString());
		/*try {
			if (!bidderServices.validateBid(bid)) {
				msgt.convertAndSend("/app/auctions/" + idAuction, new InconsistentBid("Bid inconsistente - Saldo insuficiente"));
			} else {
				auctionServices.updateAuction(bid);
				msgt.convertAndSend("/app/auctions/"+idAuction, bid);
			}
		} catch (BidderNotFound e1) {
			msgt.convertAndSend("/app/auctions/" + idAuction, e1);
			e1.printStackTrace();
		} catch (AuctionNotFound e) {
			msgt.convertAndSend("/app/auctions/" + idAuction, e);
			e.printStackTrace();
		} catch (InconsistentBid e) {
			msgt.convertAndSend("/app/auctions/" + idAuction, e);
			e.printStackTrace();
		}*/
>>>>>>> 1e8db763f8e94e8d5f256a1e983897cc6aa4264a
	}
}