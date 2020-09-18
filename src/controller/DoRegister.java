package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

/**
 * Servlet implementation class DoRegister
 */
@WebServlet("/doRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		//System.out.println(gender);

		CustomerService service = (CustomerService) CustomerService.getInstance();
		
		String page;
		
	
	if((service.findCustomer(id) !=null)||(id==null || password==null || name==null ||gender==null ||email==null)) {//같은 아이디가 있을 경우 and 모든 정보를 입력하지 않았을 경우
		page ="/view/registerFail.jsp";
		request.setAttribute("id", id);
	}else {
		
	Customer customer = new Customer(id, password, name, gender, email); 
		service.addCustomer(customer);
		page ="/view/registerSuccess.jsp";
		request.setAttribute("customer", customer);
	}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
