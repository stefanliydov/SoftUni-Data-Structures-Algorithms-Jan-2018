package limited_memory.main;


import java.util.*;

public class LimitedMemoryCollection<K, V> implements ILimitedMemoryCollection<K, V> {
    private int capacity;
    private List<Pair<K, V>> queue;
    private HashMap<K, Pair<K, V>> pairs;

    public LimitedMemoryCollection(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.pairs = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if (!this.pairs.containsKey(key)) {
            throw new IllegalArgumentException("Key does not exist");
        }
        Pair<K, V> currentPair = getPair(key);
        queue.remove(currentPair);
        queue.add(currentPair);

        return this.pairs.get(key).getValue();
    }

    @Override
    public void set(K key, V value) {
        Pair<K, V> newPair = new Pair<>(key, value);

        pairs.put(key, newPair);

           deleteIfExists(key);

        queue.add(newPair);

        if (this.getCapacity()+1 == this.getCount()) {
            K keyForRemove = queue.remove(0).getKey();
           pairs.remove(keyForRemove);
        }
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCount() {
        return this.queue.size();
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new collectionIterator(queue);
    }
    private class collectionIterator implements Iterator<Pair<K, V>>{


        private List<Pair<K, V>> queue;
        private int count = 0;

        public collectionIterator(List<Pair<K, V>> queue) {
            this.queue = queue;
            this.count = queue.size()-1;
        }

        @Override
        public boolean hasNext() {
            return count >= 0;
        }

        @Override
        public Pair<K, V> next() {
            return this.queue.get(count--);
        }
    }

    private Pair<K, V> getPair(K key) {
        for (Pair<K, V> kvPair : this.queue) {
            if (kvPair.getKey().equals(key)) {
                return kvPair;
            }
        }
        return null;
    }
    private void deleteIfExists(K key){
        for (int i = 0; i < queue.size(); i++) {
                  if(queue.get(i).getKey().equals(key)){
                      queue.remove(i);
                      return;
                  }
        }
    }
}
