package ariefbelajarjava.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

@WebServlet(urlPatterns = "/form")
public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("src/main/resources/html/form.html");

        try (InputStream inputStream = Files.newInputStream(path);
             Scanner scanner = new Scanner(inputStream)) {

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                // System.out.println(line);
                resp.getWriter().println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Path path = Path.of("form.html");
        // String html = Files.readString(path);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String fullName = firstName + " " + lastName;
        String response = "Hello " + fullName;
        resp.getWriter().println(response);
    }
}
