package theme6_Module_BuildTools_Testing.task4_Iterator;

import java.util.Iterator;

public class MyArray<T> implements Iterable<T> {

    private T[] array;
    private int currentSize;

    public MyArray(T[] newArray) {
        this.array = newArray;
        this.currentSize = array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && array[currentIndex] != null;
            }

            @Override
            public T next() {
                return array[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}