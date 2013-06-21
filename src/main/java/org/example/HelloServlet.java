package org.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.ParameterStyle;
import net.oauth.client.OAuthResponseMessage;
import net.oauth.http.HttpMessage;
import net.oauth.http.HttpResponseMessage;


public class HelloServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OAuthConsumer consumer = null;
        try {
            consumer = CookieConsumer.getConsumer("twitter", getServletContext());
            OAuthAccessor accessor = CookieConsumer.getAccessor(request, response, consumer);
            OAuthResponseMessage result = CookieConsumer.CLIENT.access(
                accessor.newRequestMessage(
                    OAuthMessage.GET,
                    "http://twitter.com/statuses/friends_timeline.atom", null
                ),
                ParameterStyle.AUTHORIZATION_HEADER
            );
            int status = result.getHttpResponse().getStatusCode();
            if (status != HttpResponseMessage.STATUS_OK) {
                OAuthProblemException problem = result.toOAuthProblemException();
                if (problem.getProblem() != null) {
                    throw problem;
                }
                Map<String, Object> dump = problem.getParameters();
                response.setContentType("text/plain");

                response.getWriter().println(dump.get(HttpMessage.REQUEST));
                response.getWriter().println("----------------------");
                response.getWriter().println(dump.get(HttpMessage.RESPONSE));
            } else {
                // Simply pass the data through to the browser:
                CookieConsumer.copyResponse(result, response);
            }
        } catch (Exception e) {
            CookieConsumer.handleException(e, request, response, consumer);
        }
    }

//    response.setContentType("text/html");
//    response.setStatus(HttpServletResponse.SC_OK);
//    response.getWriter().println("<h1>Hello Servlet</h1>");
//    response.getWriter().println("session=" + request.getSession(true).getId());


}

