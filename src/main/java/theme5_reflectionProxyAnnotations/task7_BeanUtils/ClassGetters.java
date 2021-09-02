package theme5_reflectionProxyAnnotations.task7_BeanUtils;

public class ClassGetters /*extends ClassSettersGetters*/ {
    private Integer integer;
    private String string;

    public ClassGetters(Integer integer, String string) {
        this.integer = integer;
        this.string = string;
    }

    public ClassGetters() {
    }


    public Integer getInteger() {
        return integer;
    }

    public String getString() {
        return string;
    }

}
