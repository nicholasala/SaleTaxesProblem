import exceptions.WrongFormatException;
import model.BascketProduct;
import model.ShoppingBascket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class BascketParser {

    public ShoppingBascket parse(Reader input) throws IOException, WrongFormatException {
        BufferedReader bufReader = new BufferedReader(input);
        ShoppingBascket bascket = new ShoppingBascket();
        String line = bufReader.readLine();

        do{
            if(verifyEntry(line))
                bascket.addProduct(new BascketProduct(getVolume(line), getName(line), getPrice(line)));
            else
                throw new WrongFormatException("Bascket entry not correctly formatted: "+line+"\nThe correct format is: volume name at price");
        }while((line = bufReader.readLine()) != null);

        return bascket;
    }

    public int getVolume(String s) {
        return Integer.valueOf(s.split(" ")[0]);
    }

    public String getName(String s) {
        StringBuilder name = new StringBuilder();
        String[] values = s.split(" ");

        for(int i = 1; i<values.length - 3; i++){
            name.append(values[i]);
            name.append(" ");
        }

        name.append(values[values.length -3]);
        return name.toString();
    }

    public double getPrice(String s) {
        String[] values = s.split(" ");
        return Double.valueOf(values[values.length - 1]);
    }

    public boolean verifyEntry(String s) {
        String[] values = s.split(" ");

        if(values.length < 4) return false;

        if(!values[values.length - 2].equals("at")) return false;

        for(char c : values[0].toCharArray())
            if(!Character.isDigit(c))
                return false;

        for(char c : values[values.length - 1].toCharArray())
            if(!Character.isDigit(c) && c != '.')
                return false;

        return true;
    }
}
