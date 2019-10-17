package ru.job4j.clientlanguage.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/cities")
public class City extends HttpServlet {
    private Map<String, String[]> countries = Map.of("Russia", new String[]{"Moscow", "Saint-Petersburg", "Kazan"}, "Europe", new String[]{"Paris", "Helsinki"});

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = new ObjectMapper().readTree(this.getRequestData(req)).get("country").asText();
        resp.getWriter().println(new ObjectMapper().writeValueAsString(this.countries.get(country)));
    }

    /**
     * Returns data from request reader.
     *
     * @param req
     * @return data
     * @throws IOException
     */
    private String getRequestData(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
