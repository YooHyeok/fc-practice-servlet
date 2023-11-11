package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * GenericServlet 추상클래스 상속 <br/>
 * service메소드만 추상메소드이다 (필수)
 * init, getServletInfo, getServletConfig 등은 필요할때만 오버라이딩한다.
 */
@WebServlet("/generic/calculate") //URL Path와 Servlet을 매핑하기위한 어노테이션
public class CalculatorGenericServlet extends GenericServlet {

    private static final Logger log = LoggerFactory.getLogger(CalculatorGenericServlet.class);

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        log.info("calculate result : {}", result);
        response.getWriter().println("calculate result : "+  result);
    }


}
