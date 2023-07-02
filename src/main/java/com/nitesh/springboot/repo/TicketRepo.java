package com.nitesh.springboot.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.springboot.binding.TicketInfo;

public interface TicketRepo extends JpaRepository<TicketInfo, Serializable> {

}
