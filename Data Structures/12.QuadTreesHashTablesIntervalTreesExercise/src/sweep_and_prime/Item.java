package sweep_and_prime;
public class Item {


    private String id;


    private int x;
    private int y;
    private int x2;
    private int y2;

    public Item(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.x2 = this.x + 10;
        this.y2 = this.y + 10;
    }

    public void setX(int x) {
        this.x = x;
        this.x2 = this.x+10;
    }

    public void setY(int y) {
        this.y = y;
        this.y2 = this.y+10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return this.y;
    }

    public int getX2() {
        return this.x2;
    }

    public int getY2() {
        return this.y2;
    }

    public boolean interesect(Item other) {
        return
                this.getX() <= other.getX2() &&
                        this.getX2()>= other.getX() &&
                        this.getY() <= other.getY2() &&
                        this.getY2() >= other.getY();
    }

    public String getId() {
        return id;
    }
}
