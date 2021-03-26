package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Reimbursement;
import dao.ReimbDao;

public class DisplayTicketController {
	
	public static void displayTickets(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
	IOException{
		List<Reimbursement> rList;
		ReimbDao rDao = new ReimbDao();
		HttpSession session = req.getSession();
		System.out.println();
		rList = rDao.getAllReimbs();
		res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
	}
}
