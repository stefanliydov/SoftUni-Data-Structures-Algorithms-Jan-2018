public class Product implements Comparable<Product> {

    private String name;
    private double price;
    private String producer;

    public Product(String name, double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getProducer() {
        return this.producer;
    }

    @Override
    public int compareTo(Product o) {
        if(this.name.compareTo(o.name) ==0){
            if(this.producer.compareTo(o.producer)==0){
                return (int)(this.price - o.price);
            }
            return this.producer.compareTo(o.producer);
        }
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return String.format("{%s;%s;%.2f}",this.getName(),this.getProducer(),this.getPrice());
    }
}
