import exceptions.WrongFormatException;
import model.Receipt;
import model.ShoppingBascket;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

public class SystemTest {

    @Test
    public void program() throws IOException, WrongFormatException {
        String s1 = "2 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        String s2 = "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50";

        String s3 = "1 imported bottle of perfume at 27.99\n" +
                "1 bottle of perfume at 18.99\n" +
                "1 packet of headache pills at 9.75\n" +
                "3 box of imported chocolates at 11.25\n";

        StringReader input1 = new StringReader(s1);
        StringReader input2 = new StringReader(s2);
        StringReader input3 = new StringReader(s3);

        System.out.println("INPUT:");
        System.out.println("\nInput 1:");
        System.out.println(s1);
        System.out.println("\nInput 2:");
        System.out.println(s2);
        System.out.println("\nInput 3:");
        System.out.println(s3);

        //CALCULATION OF RECEIPTS
        BascketParser parser = new BascketParser();
        CashDesk cashDesk = new CashDesk(10, 5);

        ShoppingBascket b1 = parser.parse(input1);
        ShoppingBascket b2 = parser.parse(input2);
        ShoppingBascket b3 = parser.parse(input3);

        Receipt r1 = cashDesk.generate(b1);
        Receipt r2 = cashDesk.generate(b2);
        Receipt r3 = cashDesk.generate(b3);

        System.out.println("OUTPUT:");
        System.out.println("\nOutput 1:");
        System.out.println(r1);
        System.out.println("\nOutput 2:");
        System.out.println(r2);
        System.out.println("\nOutput 3:");
        System.out.println(r3);
    }
}
