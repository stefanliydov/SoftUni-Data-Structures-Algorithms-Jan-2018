import java.util.*;
import java.util.stream.Collectors;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> byEmail = new HashMap<>();
    private Map<String, TreeSet<Person>> byDomain = new HashMap<>();
    private Map<String, TreeSet<Person>> byNameAndTown = new HashMap<>();
    private TreeMap<Integer,TreeSet<Person>> byAge = new TreeMap<>();
    private Map<String,TreeMap<Integer,TreeSet<Person>>> byTownAndAge = new HashMap<>();
    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.byEmail.containsKey(email)) {
            return false;
        }
        Person newPerson = new Person(email, name, age, town);
        // Save By Email
        this.byEmail.put(email, newPerson);
        // Save By Domain
        addByDomain(email, newPerson);
        // Save By Name And Town
        addByNameAndTown(name, town, newPerson);
        // Save By Age
        addByAge(age, newPerson);
        // Save By Town And Age
        addByTownAndAge(age, town, newPerson);
        return true;
    }



    @Override
    public int getCount() {
        return this.byEmail.size();
    }

    @Override
    public Person findPerson(String email) {
        if (!this.byEmail.containsKey(email)) {
            return null;
        }

        return byEmail.get(email);
    }

    @Override
    public boolean deletePerson(String email) {
        if (!this.byEmail.containsKey(email)) {
            return false;
        }
        Person personForRemove = this.byEmail.get(email);
        this.byEmail.remove(email);
        this.byDomain.get(getDomain(email)).remove(personForRemove);

        String nameTown = personForRemove.getName() + personForRemove.getTown();
        this.byNameAndTown.get(nameTown).remove(personForRemove);

        int age = personForRemove.getAge();
        this.byAge.get(age).remove(personForRemove);
        String town = personForRemove.getTown();
        this.byTownAndAge.get(town).get(age).remove(personForRemove);
        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {

        if (!this.byDomain.containsKey(emailDomain)) {
            return Collections.emptyList();
        }
        return this.byDomain.get(emailDomain);
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        String nameTown = name+town;
        if (!this.byNameAndTown.containsKey(nameTown)) {
            return Collections.emptyList();
        }
        TreeSet<Person> people = this.byNameAndTown.get(nameTown);
        return people;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        List<TreeSet<Person>> collect = new ArrayList<>(byAge.subMap(startAge, endAge+1).values());
        List<Person> result = new LinkedList<>();
        for (TreeSet<Person> people : collect) {
            result.addAll(people);
        }
        return result;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        if(!this.byTownAndAge.containsKey(town)){
            return Collections.emptyList();
        }
        List<TreeSet<Person>> collect = new ArrayList<>(this.byTownAndAge.get(town).subMap(startAge, endAge).values());
        List<Person> result = new LinkedList<>();
        for (TreeSet<Person> people : collect) {
            result.addAll(people);
        }
        return result;
    }

    private String getDomain(String email) {
        int index = email.indexOf("@");
        return email.substring(index + 1);
    }

    private void addByDomain(String email, Person newPerson) {
        String domain = getDomain(email);
        if (!this.byDomain.containsKey(domain)) {
            this.byDomain.put(domain, new TreeSet<>());
        }
        this.byDomain.get(domain).add(newPerson);
    }

    private void addByNameAndTown(String name, String town, Person newPerson) {
        String nameTown = name + town;
        if (!this.byNameAndTown.containsKey(nameTown)) {
            this.byNameAndTown.put(nameTown, new TreeSet<>());
        }
        this.byNameAndTown.get(nameTown).add(newPerson);
    }

    private void addByAge(int age, Person newPerson) {
        if(!this.byAge.containsKey(age)){
            this.byAge.put(age,new TreeSet<>());
        }
        this.byAge.get(age).add(newPerson);
    }

    private void addByTownAndAge(int age, String town, Person newPerson) {
        if(!this.byTownAndAge.containsKey(town)){
            this.byTownAndAge.put(town, new TreeMap<>());
        }
        if(!this.byTownAndAge.get(town).containsKey(age)){
            this.byTownAndAge.get(town).put(age,new TreeSet<>());
        }
        this.byTownAndAge.get(town).get(age).add(newPerson);
    }
}
