import java.lang.reflect.Field;

/**
 * Класс различных проб. К выполнению заданий не отностится.
 */
public class Testing {

    public static void add(Integer i, Integer k){
        try {
            Field v = i.getClass().getDeclaredField("value");
            v.setAccessible(true);
            v.setInt(i,v.getInt(i)+k);
            v.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Integer n;
        n = new Integer(0);
        System.out.println("n = " + n);
        add(n, 1);
        System.out.println("n = " + n);
        add(n,1);
        System.out.println("n = " + n);
        add(n,1);
        System.out.println("n = " + n);

    }
}
