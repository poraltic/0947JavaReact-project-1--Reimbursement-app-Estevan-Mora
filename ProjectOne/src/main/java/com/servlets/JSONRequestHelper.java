package com.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controllers.DisplayTicketController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JSONRequestHelper {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		switch(req.getRequestURI()) {
		case "/ProjectOne/displaytickets.json":
			System.out.println("got this far");
			DisplayTicketController.displayTickets(req, res);
			break;
		default:
			System.out.println("oof");
		}
	}

}
