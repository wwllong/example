package org.example.hello.mybatis.pattern.builder;

/**
 * @author jack.wen
 * @since 2023/4/9 00:35
 */
public class ComputerBuilder {

    private final Computer target = new Computer();

    public ComputerBuilder installDisplayer(String displayer) {
        target.setDisplayer(displayer);
        return this;
    }
    public ComputerBuilder installMainUnit(String mainUnit) {
        target.setMainUnit(mainUnit);
        return this;
    }
    public ComputerBuilder installMouse(String mouse) {
        target.setMouse(mouse);
        return this;
    }
    public ComputerBuilder installKeyboard(String keyboard) {
        target.setKeyboard(keyboard);
        return this;
    }
    public Computer build() {
        return target;
    }

}
