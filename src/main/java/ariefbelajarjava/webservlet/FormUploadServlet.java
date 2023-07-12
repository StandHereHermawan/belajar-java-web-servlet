package ariefbelajarjava.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.UUID;

@WebServlet(urlPatterns = "/form-upload")
@MultipartConfig
public class FormUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Windows Version
        try (InputStream inputStream = FormUploadServlet.class.getResourceAsStream("/html/form-upload.html");
             Scanner scanner = new Scanner(inputStream)) {

            while (scanner.hasNextLine()) {
                String html = scanner.nextLine();
                resp.getWriter().println(html);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Part profile = req.getPart("profile");

        Path uploadLocation = Path.of("upload/" + UUID.randomUUID().toString() + profile.getSubmittedFileName());
        Files.copy(profile.getInputStream(), uploadLocation);

        String html = """
                <html>
                <body>
                Name : $name
                <br>
                Profile : <img width="400px" height="400px" src="/download?file=$profile" />
                </body>
                </html>
                """
                .replace("$name", name)
                .replace("$profile", uploadLocation.getFileName().toString());

        resp.getWriter().println(html);
    }
}
