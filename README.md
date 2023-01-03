# Java-Deserialize-MemShell

Java反序列化注入内存马payload生成工具

## 声明：

此项目只供学习和测试，禁止用于一切违法行为！！！

## usage：

### gui版本

首先，需要下载：

https://gluonhq.com/products/javafx/

下载里面的SDK，个人测试版本为Java11，JavaFX 11，其他版本暂时不知道能不能运行。

```
mvn package
java -jar --module-path path/to/javafx-sdk-11/lib --add-modules javafx.controls,javafx.fxml JavaFxApplication-1.0-SNAPSHOT.jar
```

当然也可以直接使用release里的jar包

```
java -jar --module-path path/to/javafx-sdk-11/lib --add-modules javafx.controls,javafx.fxml JavaFxApplication.jar
```

### 终端版

为了方便，直接去掉gui界面，回归终端jar包运行。终端版源码在github文件夹里面。

```
java -jar javaDeserializeMemShell.jar -g gadgetName -m memShellType -e encodeType
```

## DIY

如何自己加入其他的反序列化链？

sample.ViewController.java：

```
void initialize() {
        final String[] gadgetItems = {"cc4", "cc7", "cc2", "cc5", "cc6", "rome1.0", "rome1.7", "commons-beanutils", "自定义链子名"};
        gadgetBox.getItems().addAll(FXCollections.observableArrayList(gadgetItems));
    ...
}
```

sample.exploit.PayloadCreate.java:

```
public class PayloadCreate {
    public static String expCreate(String gadgetName, String encodeType, String memShellType) {
        byte[] expBytes;
        try {
            if (gadgetName.equals("cc4")) {
                CC4Exp cc4_Exp = new CC4Exp(memShellType);
                 expBytes = cc4_Exp.exploit();
                return EncodeUtils.encodeExp(encodeType, expBytes);
            } //省略
            else if(gadgetName.equals("自定义链子名")){//添加一个else if
                YourClass yourClass = new YourClass(memShellType);
                 expBytes = yourClass.youMethod();
                return EncodeUtils.encodeExp(encodeType, expBytes);
            }
```

sample.exploit.payload文件夹下面创建自己的类，可以加入以下代码：

```
public String memShell;
    public Class<?> memShellRunner;

    public YourClass(String memShell){
        this.memShell = memShell;
        this.memShellRunner = ExpUtils.getMemShellRunner(memShell);
    }

 public byte[] yourMethod(){
     //反序列化链
 }
```

然后即可。

### 注意

- 由于本项目都是用java1.8的TemplatesImpl来实现内存马注入，服务器运行java版本过高可能会不成功。
- 其实直接把反序列化代码加入yourMethod即可，只要return byte[]就行。

## 最后

由于用的JavaFX，最旧版本是java11环境，而反序列化都是java8的链子，所以打包不出可执行文件，如果有人教教就好了XD。

作为JavaFX新手，代码写的比较屎（忘记注释了），不要见怪。。。（轻喷）orz

手撸的一些小坑和过程记在了博客上：[https://maxzed.top/2022/11/03/%e6%88%91%e8%87%aa%e5%b7%b1%e5%86%99%e7%9a%84%e5%b0%8f%e5%b7%a5%e5%85%b7/](https://maxzed.top/2022/11/03/我自己写的小工具/)
