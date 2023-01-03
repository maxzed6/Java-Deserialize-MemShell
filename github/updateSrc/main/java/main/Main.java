package main;

import main.exploit.PayloadCreate;
import main.utils.ShowUtils;

public class Main {
    public static void main(String[] args) {
        String[] inputArgs = new String[]{"", "", "base64"};
        if (args == null){
            System.out.println(ShowUtils.showHelp());
        } else {
          for (int i = 0; i < args.length; i++){
              switch (args[i]) {
                  case "-g":
                    inputArgs[0] = args[++i];
                    break;
                  case "-m":
                      inputArgs[1] = args[++i];
                      break;
                  case "-e":
                      inputArgs[2] = args[++i];
                      break;
                  default:
                      System.out.println(ShowUtils.showHelp());
                      return;
              }
          }
          System.out.println(PayloadCreate.expCreate(inputArgs[0], inputArgs[2], inputArgs[1]));
          System.out.println(ShowUtils.showMemShellUse(inputArgs[1]));
        }
    }
}
