package org.example.hello.mybatis.pattern.factory;

/**
 * @author jack.wen
 * @since 2023/4/9 14:50
 */
public class ComputerFactory {

    public static Computer create(String type) {
        Computer computer = null;
        switch (type) {
            case "lenovo":
                computer = new LenovoComputer();
                break;
            case "hp":
                computer = new HpComputer();
                break;
        }
        return computer;
    }

}
