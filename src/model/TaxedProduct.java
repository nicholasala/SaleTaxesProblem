package model;

import java.util.Arrays;
import java.util.List;

public class TaxedProduct extends Product{
    private double taxAmount;
    private double total;

    public TaxedProduct(int volume, String name, double taxAmount, double total) {
        this.taxAmount = taxAmount;
        this.total = total;
        this.volume = volume;
        this.name = name;
    }

    public TaxedProduct(BascketProduct p, double taxAmount, double total) {
        this.taxAmount = taxAmount;
        this.total = total;
        this.volume = p.getVolume();
        this.name = p.getName();
    }

    public double getTotal() {
        return total;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    @Override
    public String toString() {
        List<String> name = Arrays.asList(this.name.split(" "));
        StringBuilder sb = new StringBuilder();

        if(name.contains("imported") && name.indexOf("imported") > 0){
            String prev = name.get(0);
            name.set(0, "imported");

            for(int i=1; i<name.size(); i++){
                String actual = name.get(i);
                name.set(i, prev);
                prev = actual;

                if(actual.equals("imported")) break;
            }
        }

        sb.append(volume);

        for(String s : name) {
            sb.append(" ");
            sb.append(s);
        }

        sb.append(": ");
        sb.append(total);
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxedProduct product = (TaxedProduct) o;

        if (Double.compare(product.taxAmount, taxAmount) != 0) return false;
        if (Double.compare(product.total, total) != 0) return false;
        if (volume != product.volume) return false;
        return name.equals(product.name);

    }
}