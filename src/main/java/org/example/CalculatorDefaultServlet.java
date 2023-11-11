package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * 서블릿 객체를 싱글톤으로 관리 <br/>
 * (인스턴스를 하나만 생성하여 공유하는방식) <br/>
 * CalculatorServlet 클래스의 init, service등의 메소드를 호출하기 위해서는 인스턴스를 만들어야 한다.<br/>
 * 해당 인스턴스는 딱 하나만 생성하여 공유한다.
 */
@WebServlet("/default/calculate") //URL Path와 Servlet을 매핑하기위한 어노테이션
public class CalculatorDefaultServlet  implements Servlet {

    private static final Logger log = LoggerFactory.getLogger(CalculatorDefaultServlet.class);
    private ServletConfig servletConfig;

    /**
     * Servlet컨테이너가 Servlet생성 후 초기화 작업을 수행하기 위해 호출하는 메소드 <br/>
     * (자원 초기화 작업 등)
     * @param config
     *            a <code>ServletConfig</code> object containing the servlet's
     *            configuration and initialization parameters
     *
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init");
        this.servletConfig = config;
    }

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

    /**
     * 자원 해제 등의 작업
     */
    @Override
    public void destroy() {
        //resource release
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }


    @Override
    public String getServletInfo() {
        return null;
    }

}
