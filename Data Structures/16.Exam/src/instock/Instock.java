
package instock;
import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> productsByInsertionOrder;
    private TreeMap<String, Product> nameAndProduct;
    private TreeMap<Double,List<Product>> priceAndProduct;
    private HashSet<Product> byChangingOrder;
    private List<Product> products;
    private TreeSet<Product> orderedByPrice;
    public Instock() {
        this.productsByInsertionOrder = new LinkedList<>();
        this.nameAndProduct = new TreeMap<>();
        this.priceAndProduct = new TreeMap<>();
        this.byChangingOrder = new HashSet<>();
        this.products = new ArrayList<>();
        this.orderedByPrice= new TreeSet<>();
    }

    @Override
    public int getCount() {
        return this.productsByInsertionOrder.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.contains(product);
    }

    @Override
    public void add(Product product) {
        this.productsByInsertionOrder.add(product);
        if (!this.nameAndProduct.containsKey(product.getLabel())) {
            this.nameAndProduct.put(product.getLabel(), product);
        }
        double price = product.getPrice();
        if(!this.priceAndProduct.containsKey(price)){
            this.priceAndProduct.put(price,new LinkedList<>());
        }
        this.priceAndProduct.get(price).add(product);
        this.byChangingOrder.add(product);
        this.products.add(product);
        this.orderedByPrice.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        if (!this.nameAndProduct.containsKey(product)) {
            throw new IllegalArgumentException();
        }
        this.nameAndProduct.get(product).setQuantity(quantity);
        Product prod = this.nameAndProduct.get(product);

        this.byChangingOrder.add(prod);
    }

    @Override
    public Product find(int index) {
        if (index < 0 || index >= this.productsByInsertionOrder.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        if (!this.nameAndProduct.containsKey(label)) {
            throw new IllegalArgumentException();
        }

        return this.nameAndProduct.get(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count < 0 || count > this.nameAndProduct.size()) {
            throw new IllegalArgumentException();
        }
        List<Product> result = new LinkedList<>();


        Iterator<Product> iterator = this.nameAndProduct.values().iterator();
        while (count > 0) {
            result.add(iterator.next());
            iterator.remove();
            count--;
        }
        return result;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        List<Product> result = new LinkedList<>();

        for (List<Product> products : this.priceAndProduct.subMap(lo+0.01, hi + 0.01).values()) {
            result.addAll(products);

        }
        result.sort((a,b)-> Double.compare(b.getPrice(),a.getPrice()));
        return  result;
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        if(!this.priceAndProduct.containsKey(price)){
            return new ArrayList<>();
        }
        return this.priceAndProduct.get(price);
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count < 0 || count > this.orderedByPrice.size()) {
            throw new IllegalArgumentException();
        }
        List<Product> result = new LinkedList<>(orderedByPrice);


        return result.subList(0,count);
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
          List<Product> collect = this.byChangingOrder.stream().filter(x -> x.getQuantity() == quantity).collect(Collectors.toList());
        if(collect.size() ==0){
            return new ArrayList<>();
        }
        return collect;
    }

    @Override
    public Iterator<Product> iterator() {
        return this.productsByInsertionOrder.iterator();
    }
}
