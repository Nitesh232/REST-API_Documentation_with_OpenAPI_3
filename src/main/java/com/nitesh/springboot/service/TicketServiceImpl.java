package com.nitesh.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nitesh.springboot.binding.TicketInfo;
import com.nitesh.springboot.repo.TicketRepo;

@Service
public class TicketServiceImpl implements TicketService {
	
	private TicketRepo ticketRepo;	

	public TicketServiceImpl(TicketRepo ticketRepo) {
		
		this.ticketRepo = ticketRepo;
		
	}

	@Override
	public List<TicketInfo> getAllTickets() {
		
		return ticketRepo.findAll();
	}

	@Override
	public String upsertTicket(TicketInfo tinf) {
		
		Integer id = tinf.getPassangerId();
		
		ticketRepo.save(tinf);
		String msg = "";
		if(id == null) {
			msg = "Record Inserted....";
			return msg;
		}else {
			msg = "Record Updated....";
			return msg;
		}		
		
	}

	@Override
	public String deleteTicket(Integer id) {
		
		ticketRepo.deleteById(id);
		
		
		return "Deleted reocrd successfully....";
	}

}
