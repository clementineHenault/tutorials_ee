package com.linkedin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet(urlPatterns= "/CatalogServlet", asyncSupported = true)
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(request.getParameter("name"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// Deal with async / threads: 
		AsyncContext asyncContext = request.startAsync();
		
		// Start a new thread to respond to the request
		asyncContext.start(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("Printing the response");
					System.out.println("Response returned by: " + Thread.currentThread().getName());
					// Set and send the response
					returnResponse(request, response);
					// Mark complete -> cause the response to be returned back to the browser
					asyncContext.complete();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		System.out.println("Inital request: " + Thread.currentThread().getName());
	}

	private void returnResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get the information sent in the form (from form.html)
		String name = request.getParameter("name");
		String manufacturer = request.getParameter("manufacturer");
		String sku = request.getParameter("sku");
		
		// Create a catalog item with the information and add it to the catalogue 
		Catalog.addItem(new CatalogItem(name, manufacturer, sku));
		
		// Generate the response header and cookie
		response.setHeader("someHeader", "someHeaderValue");
		response.addCookie(new Cookie("someCookie", "someCookieValue"));
		
		// Generate the actual reponse body
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<body>");
		out.println("<table>");
		
		// Create a table row for each of catalog item
		for (CatalogItem item : Catalog.getItems()) {
			out.println("<tr>");
			out.println("<td>");
			
			out.print(item.getName());
			
			out.println("</td>");
			out.println("</tr>");
			
		}
		
		out.println("</table>");
		out.println("</body>");
		out.println("</head>");
		out.println("</html>");
	}

}