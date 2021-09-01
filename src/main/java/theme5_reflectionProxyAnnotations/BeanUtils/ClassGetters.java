package theme5_reflectionProxyAnnotations.BeanUtils;

import java.util.GregorianCalendar;

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
