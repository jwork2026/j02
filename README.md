# 七个葫芦娃排队

这是一个用纯 Java 编写的面向对象建模示例，不使用 Maven 或 Gradle。

当前只完成第一步：建模葫芦娃。`Huluwa` 保存每个葫芦娃固有的排行、称呼和颜色；队伍中的位置不属于葫芦娃自身，将由后续的队伍模型负责。

## 编译和运行

直接编译并运行：

```shell
make
```

也可以分别执行：

```shell
make compile
make run
make clean
```

源码统一编译为兼容 Java 17 的字节码。上述目标执行的底层 Java 命令是：

```shell
javac --release 17 -encoding UTF-8 -d out src/*.java
java -cp out Main
```
