package org.example.hello.mybatis.pattern.builder;

/**
 * @author jack.wen
 * @since 2023/4/9 00:42
 */
public class Main {

    public static void main(String[] args) {
        ComputerBuilder computerBuilder = new ComputerBuilder();
        Computer computer = computerBuilder.installDisplayer("显示器")
                .installMainUnit("主机")
                .installKeyboard("键盘")
                .installMouse("鼠标")
                .build();
        System.out.println(computer);
    }

}
