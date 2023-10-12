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
    public void testForAutograder(){
        ArrayDeque<Integer> test=new ArrayDeque<>();
        test.addFirst(3);
        test.addFirst(5);
        int result=test.get(1);
        assertEquals(result,3);

    }
//
//    @Test
    public void testisEmpty() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        assertTrue(arrayDeque.isEmpty());
    }
}