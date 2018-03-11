package bunny_wars.main;

import java.util.*;
import java.util.stream.Collectors;

public class BunnyWarsStructure implements IBunnyWarsStructure {

    private static final int TeamCount = 5;
    private TreeMap<Integer, List<Bunny>> roomIdsAndBunnies;
    private HashMap<Integer, TreeSet<Bunny>> teamAndBunnies;
    private HashMap<String, Bunny> bunnies;

    public BunnyWarsStructure() {
        this.roomIdsAndBunnies = new TreeMap<>();
        this.teamAndBunnies = new LinkedHashMap<>();
        this.bunnies = new HashMap<>();
        generateTeams();


    }

    private void generateTeams() {
        teamAndBunnies.put(0, new TreeSet<>());
        teamAndBunnies.put(1, new TreeSet<>());
        teamAndBunnies.put(2, new TreeSet<>());
        teamAndBunnies.put(3, new TreeSet<>());
        teamAndBunnies.put(4, new TreeSet<>());
    }

    @Override
    public int getBunnyCount() {
        return this.bunnies.size();
    }

    @Override
    public int getRoomCount() {
        return this.roomIdsAndBunnies.size();
    }

    @Override
    public void addRoom(int roomId) {
        if (this.roomIdsAndBunnies.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }
        this.roomIdsAndBunnies.put(roomId, new ArrayList<>());
    }

    @Override
    public void addBunny(String name, int team, int roomId) {
        if (!this.teamAndBunnies.containsKey(team) || !this.roomIdsAndBunnies.containsKey(roomId) || this.bunnies.containsKey(name)) {
            throw new IndexOutOfBoundsException();
        }

        Bunny bunny = new Bunny(name, team, roomId);
        this.bunnies.put(name, bunny);
        this.teamAndBunnies.get(team).add(bunny);
        this.roomIdsAndBunnies.get(roomId).add(bunny);

    }

    @Override
    public void remove(int roomId) {
        if (!this.roomIdsAndBunnies.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }
        List<Bunny> bunniesForRemove = this.roomIdsAndBunnies.get(roomId);
        this.roomIdsAndBunnies.remove(roomId);

        for (Bunny bunny : bunniesForRemove) {
            this.bunnies.remove(bunny.getName());
            for (TreeSet<Bunny> bunnyList : this.teamAndBunnies.values()) {
                bunnyList.remove(bunny);
            }
        }

    }

    @Override
    public void next(String bunnyName) {
        if (!this.bunnies.containsKey(bunnyName)) {
            throw new IllegalArgumentException();
        }
        Bunny bunny = this.bunnies.get(bunnyName);
        int roomId = bunny.getRoomId();
        Integer targetRoom = this.roomIdsAndBunnies.higherKey(roomId);
        if(targetRoom != null){
        this.roomIdsAndBunnies.get(roomId).remove(bunny);
        this.roomIdsAndBunnies.get(targetRoom).add(bunny);
        bunny.setRoomId(targetRoom);
        }
    }

    @Override
    public void previous(String bunnyName) {
        if (!this.bunnies.containsKey(bunnyName)) {
            throw new IllegalArgumentException();
        }
        Bunny bunny = this.bunnies.get(bunnyName);
        int roomId = bunny.getRoomId();
        Integer targetRoom = this.roomIdsAndBunnies.lowerKey(roomId);
        if(targetRoom != null){
            this.roomIdsAndBunnies.get(roomId).remove(bunny);
            this.roomIdsAndBunnies.get(targetRoom).add(bunny);
            bunny.setRoomId(targetRoom);
        }
    }

    @Override
    public void detonate(String bunnyName) {
        if(!this.bunnies.containsKey(bunnyName)){
            throw new IllegalArgumentException();
        }
        Bunny detonatedBunny = this.bunnies.get(bunnyName);
        int roomId = detonatedBunny.getRoomId();
        int chosenTeam = detonatedBunny.getTeam();
        List<Bunny> targetBunnies = this.roomIdsAndBunnies.get(roomId);
        List<Bunny> deadBunnies = new ArrayList<>();
        for (Bunny targetBunny : targetBunnies) {
            if(targetBunny.getTeam()!=chosenTeam){
                targetBunny.takeDamage(30);
                if(targetBunny.getHealth()<=0){
                   deadBunnies.add(targetBunny);
                    detonatedBunny.addScore();
                }
            }
        }
        for (Bunny deadBunny : deadBunnies) {
            this.bunnies.remove(deadBunny.getName());
            this.roomIdsAndBunnies.get(roomId).remove(deadBunny);
            this.teamAndBunnies.get(deadBunny.getTeam()).remove(deadBunny);
        }
    }

    @Override
    public Iterable<Bunny> listBunniesByTeam(int team) {
        if(!this.teamAndBunnies.containsKey(team)){
            throw new IndexOutOfBoundsException();
        }
        return this.teamAndBunnies.get(team);
    }

    @Override
    public Iterable<Bunny> listBunniesBySuffix(String suffix) {
        List<Bunny> result = new ArrayList<>();
        if(suffix.isEmpty()){
            return this.bunnies.values().stream().sorted((x,y)->{
                int n =new StringBuilder(x.getName()).reverse().toString().compareTo(new StringBuilder(y.getName()).reverse().toString());
                if(n==0){
                    return x.getName().length()- y.getName().length();
                }
                return n;
            }).collect(Collectors.toList());
        }
        for (Map.Entry<String, Bunny> stringBunnyEntry : this.bunnies.entrySet()) {

            if(stringBunnyEntry.getKey().endsWith(suffix)){
                result.add(stringBunnyEntry.getValue());
            }
        }
        return result.stream().sorted((x,y)->{
           int n =new StringBuilder(x.getName()).reverse().toString().compareTo(new StringBuilder(y.getName()).reverse().toString());
                if(n==0){
                    return x.getName().length()- y.getName().length();
                }
                return n;
         }).collect(Collectors.toList());
    }
}
