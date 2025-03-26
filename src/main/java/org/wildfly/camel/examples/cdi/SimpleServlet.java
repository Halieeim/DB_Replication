package org.wildfly.camel.examples.cdi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.wildfly.camel.examples.cdi.entity.Vehicle;

@SuppressWarnings("serial")
@WebServlet(name = "HttpServiceServlet", urlPatterns = { "/*" }, loadOnStartup = 1)
public class SimpleServlet extends HttpServlet
{
	@Resource(name = "java:jboss/camel/context/spring-context")
	private CamelContext camelctx;

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null || name.isEmpty()) {
            // Default to Hello world output
            name = "world";
        }
        
        EntityManagerFactory postgresEntityManagerFactory = Persistence.createEntityManagerFactory("camel");
        EntityManager postgresEntityManager = postgresEntityManagerFactory.createEntityManager();

        List<Vehicle> vecList = postgresEntityManager.createQuery("SELECT v FROM Vehicle v").getResultList();
        
        postgresEntityManager.close();
        postgresEntityManagerFactory.close();
        
        EntityManagerFactory mssqlEntityManagerFactory = Persistence.createEntityManagerFactory("mssql");
        EntityManager mssqlEntityManager = mssqlEntityManagerFactory.createEntityManager();

        List<Vehicle> subscriberList = mssqlEntityManager.createQuery("SELECT s FROM Subscriber s").getResultList();
        
        postgresEntityManager.close();
        postgresEntityManagerFactory.close();
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/plain; charset=UTF-8");
    	ServletOutputStream out = res.getOutputStream();
    	
//        ProducerTemplate producer = camelctx.createProducerTemplate();
//        String result = producer.requestBody("direct:jpaTest", null, String.class);
        // Convert the string to bytes using UTF-8 encoding
        String result = vecList.get(0).toString() + "\nresult set size for vehicles = " + vecList.size()
        + "\n" + subscriberList.get(0) + "\nresult set size for subscribers = " + subscriberList.size();
    	out.write(result.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
