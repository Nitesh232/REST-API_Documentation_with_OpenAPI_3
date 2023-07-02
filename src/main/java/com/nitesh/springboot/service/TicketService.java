package com.nitesh.springboot.service;

import java.util.List;

import com.nitesh.springboot.binding.TicketInfo;

public interface TicketService {
	
	public List<TicketInfo> getAllTickets();
	
	public String upsertTicket(TicketInfo tinf);
	
	public String deleteTicket(Integer id);

}
