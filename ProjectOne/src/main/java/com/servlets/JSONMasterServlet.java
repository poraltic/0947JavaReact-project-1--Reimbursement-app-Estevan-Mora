package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSONMasterServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//RequestHelper.process(req,res);
		//req.getRequestDispatcher(RequestHelper.process(req, res)).forward(req, res);
		JSONRequestHelper.process(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//req.getRequestDispatcher(RequestHelper.process(req, res)).forward(req, res);
		JSONRequestHelper.process(req, res);
	}
}
