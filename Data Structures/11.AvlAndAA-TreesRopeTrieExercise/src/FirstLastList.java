import java.util.*;
import java.util.stream.Collectors;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private List<T> elements = new LinkedList<>();

    public FirstLastList() {
    }

    @Override
    public void add(T element) {
        elements.add(element);

    }

    @Override
    public int getCount(){
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if(count>this.getCount()){
            throw new IllegalArgumentException();
        }
        return this.elements.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<T> last(int count) {
       if(count>this.getCount()){
           throw new IllegalArgumentException();
       }
       List<T> reversedList = new ArrayList<>();

        for (int i = this.elements.size() -1; i >this.elements.size() -1 - count ; i--) {
            reversedList.add(this.elements.get(i));
        }
        return reversedList;
    }

    @Override
    public Iterable<T> min(int count) {

        if(count> this.getCount()){
            throw new  IllegalArgumentException();
        }
      return this.elements.stream().sorted(Comparator.naturalOrder()).limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<T> max(int count) {
        if(count>this.getCount()){
            throw new IllegalArgumentException();
        }

        List<T> resultList = new ArrayList<>();
        List<T> sortedElemets = this.elements.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = sortedElemets.size()-1; i >sortedElemets.size()-1-count ; i--) {
            resultList.add(sortedElemets.get(i));
        }

        return resultList;
    }

    @Override
    public void clear() {
        this.elements.clear();

    }

    @Override
    public int removeAll(T element) {
        int prevSize = this.getCount();
        this.elements = this.elements.stream().filter(x -> x.compareTo(element)!=0).collect(Collectors.toList());

        return prevSize-this.getCount() ;
    }
}