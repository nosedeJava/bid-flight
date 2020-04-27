package project.app.realtime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import project.app.model.Bid;



@Controller
public class BidRealtimeHandler {
	
	@Autowired
	SimpMessagingTemplate msgt;
    
	@MessageMapping("/auctions/{idAuction}")    
	public void handleBidEvent(Bid bid, @DestinationVariable int idAuction) throws Exception {
		System.out.println("Nuevo bid recibido en el servidor!: "+idAuction+" "+bid.toString());
		msgt.convertAndSend("/topic/auctions/"+idAuction, bid);
	}
}