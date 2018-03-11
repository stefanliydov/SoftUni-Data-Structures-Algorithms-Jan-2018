import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getName().compareTo(o2.getName()) == 0) {
            if (o1.getProducer().compareTo(o2.getProducer()) == 0) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
            return o1.getProducer().compareTo(o2.getProducer());
        }
        return o1.getName().compareTo(o2.getName());
    }
}