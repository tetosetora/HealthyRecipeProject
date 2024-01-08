<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.ServletContext"%>
<%@ page import="jakarta.servlet.RequestDispatcher"%>
<%@ page import="jakarta.servlet.http.HttpServlet"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>
<%@ page import="jakarta.servlet.http.HttpServletResponse"%>
<%
	ServletContext context = getServletContext();
	RequestDispatcher rd = context.getRequestDispatcher("/home");
	rd.forward(request, response);
%>