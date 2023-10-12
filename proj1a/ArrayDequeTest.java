import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ArrayDequeTest {
    @Test
    public void testaddsizeempty() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        assertTrue(dq.isEmpty());

        dq.addFirst("first");
        assertEquals(1, dq.size());

        dq.addLast("middle");
        assertEquals(2, dq.size());

        dq.addLast("last");
        assertEquals(3, dq.size());

        dq.printDeque();

        String first = dq.removeFirst();
        assertEquals("first", first);

        String last = dq.removeLast();
        assertEquals("last", last);

        assertEquals(1, dq.size());

        dq.removeFirst();
        assertTrue(dq.isEmpty());
    }

    @Test
    public void testgrowshrink() throws NoSuchFieldException, IllegalAccessException {
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        // 获取类的Class对象
        Class<?> cls = dq.getClass();

        // 获取私有字段
        Field privateField = cls.getDeclaredField("length");

        // 设置字段为可访问
        privateField.setAccessible(true);

        // 获取字段的值
         int value = (int) privateField.get(dq);

        for (int i = 0; i < 7; i++) {
            dq.addLast(i);
        }
        assertEquals(8, (int) privateField.get(dq));
        dq.addLast(7);
        assertEquals(16, (int) privateField.get(dq));
        for (int i = 0; i < 3; i++) {
            dq.addLast(i);
        }
        assertEquals(16, (int) privateField.get(dq));
        for (int i = 0; i < 15; i++) {
            dq.addLast(i);
        }
        assertEquals(32, (int) privateField.get(dq));

        for (int i = 0; i < 17; i++) {
            dq.removeFirst();
        }
        assertEquals(32, (int) privateField.get(dq));
        dq.removeFirst();
        System.out.println("size:" + dq.size() + ",length:" + (int) privateField.get(dq));
        assertEquals(32, (int) privateField.get(dq));

        for (int i = 0; i < 64; i++) {
            dq.addLast(i);
        }
        assertEquals(128, (int) privateField.get(dq));

        for (int i = 0; i < 40; i++) {
            dq.removeLast();
        }
        System.out.println("size:" + dq.size() + ",length:" + (int) privateField.get(dq) +
                ",length / size:" + (int) privateField.get(dq) / dq.size());
        dq.removeLast();
        System.out.println("size:" + dq.size() + ",length:" + (int) privateField.get(dq) +
                ",length / size:" + (int) privateField.get(dq) / dq.size());
        assertEquals(64, (int) privateField.get(dq));

    }
//
//    @Test
//    public void testisEmpty() {
//        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
//        assertTrue(arrayDeque.isEmpty());
//    }
}