package model;

public class BascketProduct extends Product{
    protected double price;

    public BascketProduct(int volume, String name, double price) {
        this.volume = volume;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BascketProduct that = (BascketProduct) o;

        if (volume != that.volume) return false;
        if (Double.compare(that.price, price) != 0) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }
}
