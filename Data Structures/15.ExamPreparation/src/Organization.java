import java.util.*;

public class Organization implements IOrganization {

    private List<Person> byInput;
    private HashMap<String, List<Person>> nameAndPerson;

    public Organization(){
        this.byInput = new LinkedList<>();
        this.nameAndPerson = new LinkedHashMap<>();
    }
    @Override
    public int getCount() {
        return byInput.size();
    }

    @Override
    public boolean contains(Person person) {
        return byInput.contains(person);

    }

    @Override
    public boolean containsByName(String name) {
        return this.nameAndPerson.containsKey(name);
    }

    @Override
    public void add(Person person) {
        this.byInput.add(person);
        String name = person.getName();
        if(!this.nameAndPerson.containsKey(name)){
            this.nameAndPerson.put(name, new LinkedList<>());
        }
        this.nameAndPerson.get(name).add(person);
    }

    @Override
    public Person getAtIndex(int index) {
        if(index >= this.byInput.size() || index<0){
            throw new IllegalArgumentException();
        }
        return this.byInput.get(index);
    }

    @Override
    public Iterable<Person> getByName(String name) {
        if(!this.nameAndPerson.containsKey(name)){
            return new ArrayList<>();
        }
        return this.nameAndPerson.get(name);
    }

    @Override
    public Iterable<Person> firstByInsertOrder() {
        return this.byInput.subList(0,1);
    }

    @Override
    public Iterable<Person> firstByInsertOrder(int count) {
        if(count>this.byInput.size()){
            return this.byInput;
        }
        return this.byInput.subList(0,count);
    }

    @Override
    public Iterable<Person> searchWithNameSize(int minLength, int maxLength) {
        List<Person> result= new LinkedList<>();
        this.nameAndPerson
                .entrySet()
                .stream()
                .filter(x-> x.getKey().length()>=minLength && x.getKey().length()<=maxLength)
                .forEach(x-> result.addAll(x.getValue()));
        if(result.isEmpty()){
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Person> getWithNameSize(int length) {
        List<Person> result= new LinkedList<>();
        this.nameAndPerson
                .entrySet()
                .stream()
                .filter(x-> x.getKey().length()==length)
                .forEach(x-> result.addAll(x.getValue()));
        if(result.isEmpty()){
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Person> peopleByInsertOrder() {
        return this.byInput;
    }

    @Override
    public Iterator<Person> iterator() {
        return this.byInput.iterator();
    }
}
