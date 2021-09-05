package theme6_Module_BuildTools_Testing.task4_Iterator;


import org.junit.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyArrayTest {

    @Test
    public void iterator() {
        Integer[] a = {1, 2, 3, null, 5, 6};
        MyArray<Integer> array = new MyArray<>(a);

        Iterator<Integer> iterator = array.iterator();
        int expect = 1;
        while (iterator.hasNext()) {
            assertEquals(expect++, iterator.next());
        }
    }
}