package byog.lab6;
import org.junit.Test;
import static org.junit.Assert.*;

public class MemoryGameTest {

    @Test
    public void testRandomString()
    {
        MemoryGame test=new MemoryGame(10,10,1);
        int expect=5;
        int argument=5;
        assertEquals(expect,test.generateRandomString(5).length());
    }

    @Test
    public void testDrawFrame()
    {
        MemoryGame test=new MemoryGame(40,40,13);
        test.drawFrame(test.generateRandomString(5));
    }
}
