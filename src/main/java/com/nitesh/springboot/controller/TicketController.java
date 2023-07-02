package com.nitesh.springboot.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springboot.binding.PassangerInfo;
import com.nitesh.springboot.binding.TicketInfo;
import com.nitesh.springboot.constants.ConstantVariables;
import com.nitesh.springboot.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class TicketController {
	
	
	@Autowired
	private TicketService ticket;
	
	@Operation(summary = "Endpoint to get all tickets booked", description = "This endpoint provides details of all tickets booed till now.")
	@ApiResponses({
	      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TicketInfo.class), mediaType = "application/json") }),
	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(value = "/getTickets")
	public ResponseEntity<List<TicketInfo>> getAllTicket(){
		
		List<TicketInfo> allTickets = ticket.getAllTickets();
		
		return new ResponseEntity<List<TicketInfo>>(allTickets, HttpStatus.OK);
	}
	
	@Operation(summary = "Endpoint to book ticket.", description = "This endpoint is used to book your tickets.", 
			   requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
			   (content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PassangerInfo.class))))
	@ApiResponses({
	      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TicketInfo.class), mediaType = "application/json") }),
	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping(value = "/bookTicket")			
	public ResponseEntity<TicketInfo> bookTicket(@RequestBody PassangerInfo pinf){
		
		TicketInfo tinf = new TicketInfo();
		
		BeanUtils.copyProperties(pinf, tinf);
		
		tinf.setTicketBookingDate(new Date());
		tinf.setTicketPNR(UUID.randomUUID().toString());
		tinf.setTicketPrice(pinf.getTicketCount()*1024.52);		
		
		ConstantVariables cnstVar = new ConstantVariables();
		tinf.setTicketStatus("Confirmed");
		boolean initialPresent = cnstVar.isInitialPresent(pinf.getFirstName().toLowerCase().charAt(0));
		System.out.println("Value of initialPresent :: "+ initialPresent);
		if(!initialPresent){
			tinf.setTicketStatus("Not-Confirmed, Under Waiting");
		}
		
		ticket.upsertTicket(tinf);		
		
		return new ResponseEntity<TicketInfo>(tinf, HttpStatus.CREATED);
	}
	
	
	
	
	@Operation(summary = "Ticket updating endpoint.", 
			description = "This endpoint is used to update yout tickets if need any update. Note that passangerId is must field to be filled.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Operação realizada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Unauthorized - Acesso não autorizado"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Acesso negado"),
			@ApiResponse(responseCode = "404", description = "Not Found - Recurso não encontrado")
	})
	@DeleteMapping("/book/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable("id") Integer id){
		
		String deleteTicket = ticket.deleteTicket(id);	
		
		return new ResponseEntity<String>(deleteTicket, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Endpoint to delete ticket.", description = "This endpoint is used to delete your booked ticket.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Operação realizada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Unauthorized - Acesso não autorizado"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Acesso negado"),
			@ApiResponse(responseCode = "404", description = "Not Found - Recurso não encontrado")})
	@PutMapping(value = "/bookTicket")			
	public ResponseEntity<String> updateTicket(@RequestBody TicketInfo tinf){
		
		String upsertTicket = ticket.upsertTicket(tinf);		
		
		return new ResponseEntity<String>(upsertTicket, HttpStatus.CREATED);
	}
	
}
