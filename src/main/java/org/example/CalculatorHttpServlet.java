package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 기본적으로 톰캣이 service메소드를 호출하면 HttpServlet의 service메소드를 호출한다. <br/>
 * service메소드 내부에서 doGet, doPost등 메소드를 확인하여 분기한다. <br/>
 * 여기서 service메소드가 호출한 doGet은 요청에 해당하는 urlPath를 지정한 서블릿 클래스에서 구현하지 않으면 기본적으로 호출된다. <br/>
 * HttpServlet은 GenericServlet을 상속받고 있기 때문에 GenericServlet의 service메소드가 구현되어 있다. <br/>
 * 때문에 service메소드가 자동으로 호출되는것이다.
 *
 */
@WebServlet("/http/calculate") //URL Path와 Servlet을 매핑하기위한 어노테이션
public class CalculatorHttpServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(CalculatorHttpServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        log.info("calculate result : {}", result);
        response.getWriter().println("calculate result : "+  result);
    }


}
