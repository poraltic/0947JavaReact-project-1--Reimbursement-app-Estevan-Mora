package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//RequestHelper.process(req,res);
		if(req.getRequestURI().contains(".rs")) {
			req.getRequestDispatcher(RequestHelper.process(req, res)).forward(req, res);
		}else {
			JSONRequestHelper.process(req, res);
		}
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		if(req.getRequestURI().contains(".rs")) {
			req.getRequestDispatcher(RequestHelper.process(req, res)).forward(req, res);
		}else {
			JSONRequestHelper.process(req, res);
		}
		//req.getRequestDispatcher(RequestHelper.process(req, res)).forward(req, res);
		//JSONRequestHelper.process(req, res);
	}
}
