import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Instant starts = Instant.now();
        ShoppingCenter shoppingCenter = new ShoppingCenter();
        int n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {

            String line = bf.readLine();
            String command = line.substring(0, line.indexOf(" "));
            String[] product = line.substring(line.indexOf(" ") + 1).split(";");
            switch (command) {
                case "AddProduct":
                    shoppingCenter.addProduct(product[0], Double.parseDouble(product[1]), product[2]);
                    break;
                case "DeleteProducts":
                    if (product.length == 1)
                        shoppingCenter.deleteProducts(product[0]);
                    else
                        shoppingCenter.deleteProducts(product[0],product[1]);
                    break;
                case "FindProductsByName":
                        shoppingCenter.findProductsByName(product[0]);
                    break;
                case "FindProductsByProducer":
                        shoppingCenter.findProductsByProducer(product[0]);
                    break;
                case "FindProductsByPriceRange":
                        shoppingCenter.findProductsByPriceRange(Double.parseDouble(product[0]),Double.parseDouble(product[1]));
                    break;
            }

        }
        Instant ends = Instant.now();
        System.out.println(Duration.between(starts, ends));
    }
}
