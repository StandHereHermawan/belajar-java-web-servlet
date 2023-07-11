package ariefbelajarjava.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@WebServlet(urlPatterns = "/form")
@MultipartConfig
public class FormUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Windows Version
        try (InputStream inputStream = FormUploadServlet.class.getResourceAsStream("/html/form-upload.html.html");
             Scanner scanner = new Scanner(inputStream)) {

            while (scanner.hasNextLine()) {
                String html = scanner.nextLine();
                resp.getWriter().println(html);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
