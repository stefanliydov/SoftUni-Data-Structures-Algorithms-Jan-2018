import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCenter {

    private static final String NO_PRODUCTS_FOUND = "No products found";
    private static final String PRODUCT_ADDED ="Product added";
    private static final String PRODUCTS_DELETED = " products deleted";

    private Map<String, List<Product>> byProducer = new HashMap<>();
    private Map<String, List<Product>> byNameAndProducer = new HashMap<>();
    private Map<String, List<Product>> byName = new HashMap<>();
    private TreeMap<Double, List<Product>> byPrice = new TreeMap<>();

    public void addProduct(String name, Double price, String producer) {

        Product newProduct = new Product(name, price, producer);
        // Save By Producer
        addByProducer(producer, newProduct);
        // Save By Name And Producer
        addByNameAndProducer(name, producer, newProduct);
        // Save By Name
        addByName(name, newProduct);
        //Save By Price
        addByPrice(price, newProduct);
        ConsoleWriter.writeLine(PRODUCT_ADDED);
    }


    public void deleteProducts(String producer) {
        if (!this.byProducer.containsKey(producer)) {
            System.out.println(NO_PRODUCTS_FOUND);
            return;
        }
        int producerProductSize = this.byProducer.get(producer).size();
        List<Product> forDelete = this.byProducer.get(producer);
        this.byProducer.remove(producer);
        for (Product product : forDelete) {
            byNameAndProducer.get(product.getName()+product.getProducer()).remove(product);
            byName.get(product.getName()).remove(product);
            byPrice.get(product.getPrice()).remove(product);
        }
        System.out.println((producerProductSize + PRODUCTS_DELETED));
    }

    public void deleteProducts(String name, String producer) {
        String nameProducer = name + producer;
        if (!this.byNameAndProducer.containsKey(nameProducer)) {
            System.out.println((NO_PRODUCTS_FOUND));
            return;
        }
        int size = this.byNameAndProducer.get(nameProducer).size();
        List<Product> forDelete = this.byNameAndProducer.get(nameProducer);
        this.byNameAndProducer.remove(nameProducer);
        for (Product product : forDelete) {
            byProducer.get(product.getProducer()).remove(product);
            byName.get(product.getName()).remove(product);
            byPrice.get(product.getPrice()).remove(product);
        }
        System.out.println((size + PRODUCTS_DELETED));
    }

    public void findProductsByName(String name) {
        if(!this.byName.containsKey(name) ) {
            System.out.println((NO_PRODUCTS_FOUND));
            return;
        }
        if(this.byName.get(name).size()==0){
            System.out.println((NO_PRODUCTS_FOUND));
            return;
        }
        StringBuilder sb= new StringBuilder();
        this.byName.get(name).sort(new ProductComparator());
        this.byName.get(name)
                .forEach(x ->sb.append(x.toString()).append(System.lineSeparator()));
        System.out.print(sb.toString());

    }

    public void findProductsByProducer(String producer) {
        if(!this.byProducer.containsKey(producer)){
            System.out.println((NO_PRODUCTS_FOUND));
            return;
        }
        if(this.byProducer.get(producer).size()==0){
            System.out.println((NO_PRODUCTS_FOUND));
            return;
        }
        StringBuilder sb=  new StringBuilder();
        this.byProducer.get(producer).sort(new ProductComparator());
        this.byProducer.get(producer)
                .forEach(x ->sb.append(x.toString()).append(System.lineSeparator()));
        System.out.print(sb.toString());
    }

    public void findProductsByPriceRange(double startPrice, double endPrice) {
        Collection<List<Product>> values = byPrice.subMap(startPrice, endPrice+0.01).values();
        List<Product> result = new LinkedList<>();
        for (List<Product> value : values) {
            result.addAll(value);
        }
        if(result.size()==0){
            System.out.println((NO_PRODUCTS_FOUND));
            return;
        }
        StringBuilder sb= new StringBuilder();
        result
                .stream()
                .sorted()
                .forEach(x ->sb.append(x.toString()).append(System.lineSeparator()));
        System.out.print(sb.toString());
    }

    private void addByProducer(String producer, Product newProduct) {
        if (!this.byProducer.containsKey(producer)) {
            this.byProducer.put(producer, new ArrayList<>());
        }
        this.byProducer.get(producer).add(newProduct);
    }

    private void addByNameAndProducer(String name, String producer, Product newProduct) {
        String nameProducer = name + producer;
        if (!this.byNameAndProducer.containsKey(nameProducer)) {
            this.byNameAndProducer.put(nameProducer, new ArrayList<>());
        }
        this.byNameAndProducer.get(nameProducer).add(newProduct);
    }

    private void addByName(String name, Product newProduct) {
        if (!this.byName.containsKey(name)) {
            this.byName.put(name, new ArrayList<>());
        }
        this.byName.get(name).add(newProduct);

    }

    private void addByPrice(Double price, Product newProduct) {
        if (!this.byPrice.containsKey(price)) {
            this.byPrice.put(price, new ArrayList<>());
        }
        this.byPrice.get(price).add(newProduct);
    }
}
