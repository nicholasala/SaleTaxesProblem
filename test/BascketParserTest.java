import exceptions.WrongFormatException;
import model.BascketProduct;
import model.ShoppingBascket;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class BascketParserTest {

    BascketParser parser;

    @Before
    public void init(){ parser = new BascketParser(); }

    @Test
    public void parse(){
        ShoppingBascket bascket = new ShoppingBascket();
        bascket.addProduct(new BascketProduct(1, "music CD", 12.9));
        bascket.addProduct(new BascketProduct(3, "box of imported chocolates", 11.25));
        bascket.addProduct(new BascketProduct(34, "book", 34.5));

        StringReader input = new StringReader("1 music CD at 12.9\n" +
                "3 box of imported chocolates at 11.25\n" +
                "34 book at 34.5\n");

        try {
            assertEquals(bascket, parser.parse(input));
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = WrongFormatException.class)
    public void parseFail() throws IOException, WrongFormatException {
        StringReader input = new StringReader("1 music CD at 12.9\n" +
                "3 at 11.25\n" +
                "34 book at 34.5\n");

        parser.parse(input);
    }

    @Test
    public void getVolume(){
        assertEquals(3, parser.getVolume("3 box of imported chocolates at 11.25"));
        assertEquals(45, parser.getVolume("45 box of imported chocolates at 11.25"));
    }

    @Test
    public void getName(){
        assertEquals("box of imported chocolates", parser.getName("3 box of imported chocolates at 11.25"));
        assertEquals("CD", parser.getName("45 CD at 11.25"));
    }

    @Test
    public void getPrice(){
        assertEquals(11.25, parser.getPrice("3 box of imported chocolates at 11.25"), 0.0);
        assertEquals(0.67, parser.getPrice("45 box of imported chocolates at 0.67"), 0.0);
    }

    @Test
    public void verifyEntry(){
        assertTrue(parser.verifyEntry("3 box of imported chocolates at 11.25"));
        assertTrue(parser.verifyEntry("1 book at 0.25"));

        assertFalse(parser.verifyEntry(""));
        assertFalse(parser.verifyEntry("ok box of imported chocolates at 11.25"));
        assertFalse(parser.verifyEntry("3 box of imported chocolates at ok"));
        assertFalse(parser.verifyEntry("3 box of imported chocolates 11.25"));
        assertFalse(parser.verifyEntry("3 at 11.25"));
        assertFalse(parser.verifyEntry("3 book at 11.25b"));
        assertFalse(parser.verifyEntry("3c book at 11.25"));
    }
}
