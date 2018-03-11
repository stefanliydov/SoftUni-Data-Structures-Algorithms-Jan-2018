package chainblock;

import java.util.*;
import java.util.stream.Collectors;

public class Chainblock implements IChainblock {
    private HashMap<Integer, Transaction> idTransactionts;
    private Map<TransactionStatus, List<Transaction>> byTransactionStatus;
    private Map<String, List<Transaction>> bySender;
    private Map<String, List<Transaction>> byReciever;
    private TreeMap<Double, List<Transaction>> byAmount;
    private List<Transaction> byInsertionOrder;

    public Chainblock() {
        this.idTransactionts = new HashMap<>();
        this.byTransactionStatus = new HashMap<>();
        this.bySender = new HashMap<>();
        this.byReciever = new HashMap<>();
        this.byAmount = new TreeMap<>();
        this.byInsertionOrder = new LinkedList<>();
        this.byTransactionStatus.put(TransactionStatus.Aborted,new ArrayList<>());
        this.byTransactionStatus.put(TransactionStatus.Unauthorized,new ArrayList<>());
        this.byTransactionStatus.put(TransactionStatus.Successfull,new ArrayList<>());
        this.byTransactionStatus.put(TransactionStatus.Failed,new ArrayList<>());
    }

    @Override
    public int getCount() {
        return this.idTransactionts.size();
    }

    @Override
    public void add(Transaction tx) {
        int id = tx.getId();
        if (this.idTransactionts.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        this.idTransactionts.put(id, tx);
        TransactionStatus transactionStatus = tx.getStatus();

        this.byTransactionStatus.get(transactionStatus).add(tx);

        String sender = tx.getSender();
        String reciever = tx.getReceiver();
        if (!this.bySender.containsKey(sender)) {
            this.bySender.put(sender, new ArrayList<>());
        }
        this.bySender.get(sender).add(tx);

        if (!this.byReciever.containsKey(reciever)) {
            this.byReciever.put(reciever, new ArrayList<>());
        }
        this.byReciever.get(reciever).add(tx);
        double amount = tx.getAmount();
        if (!this.byAmount.containsKey(amount)) {
            this.byAmount.put(amount, new ArrayList<>());
        }
        this.byAmount.get(amount).add(tx);
        this.byInsertionOrder.add(tx);
    }

    @Override
    public boolean contains(Transaction tx) {
        int id = tx.getId();
        return this.idTransactionts.containsKey(id);
    }

    @Override
    public boolean contains(int id) {
        return this.idTransactionts.containsKey(id);
    }

    @Override
    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.idTransactionts.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        Transaction transaction = this.idTransactionts.get(id);
        TransactionStatus old = transaction.getStatus();
        this.byTransactionStatus.get(old).remove(transaction);
        transaction.setStatus(newStatus);

        this.byTransactionStatus.get(transaction.getStatus()).add(transaction);
    }

    @Override
    public void removeTransactionById(int id) {
        Transaction transaction = this.idTransactionts.remove(id);
        this.byTransactionStatus.get(transaction.getStatus()).remove(transaction);
        String sender = transaction.getSender();
        String reciever = transaction.getReceiver();
        Double amount = transaction.getAmount();
        this.bySender.get(sender).remove(transaction);
        this.byReciever.get(reciever).remove(transaction);
        this.byAmount.get(amount).remove(transaction);
        this.byInsertionOrder.remove(transaction);
        if(this.byAmount.get(amount).isEmpty()){
            this.byAmount.remove(amount);
        }
    }

    @Override
    public Transaction getById(int id) {
        if (!this.idTransactionts.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.idTransactionts.get(id);
    }

    @Override
    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        if (!this.byTransactionStatus.containsKey(status) ||this.byTransactionStatus.get(status).isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result = new ArrayList<>(this.byTransactionStatus.get(status));

        result.sort((a, b) -> Double.compare(b.getAmount(), a.getAmount()));
        return result;
    }

    @Override
    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        if (!this.byTransactionStatus.containsKey(status)) {
            throw new IllegalArgumentException();
        }
        List<String> collect = this.byTransactionStatus.get(status)
                .stream()
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .map(Transaction::getSender).collect(Collectors.toList());
        if (collect.size() == 0) {
            throw new IllegalArgumentException();
        }
        return collect;
    }

    @Override
    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        if (!this.byTransactionStatus.containsKey(status)) {
            throw new IllegalArgumentException();
        }
        List<String> collect = this.byTransactionStatus.get(status)
                .stream()
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .map(Transaction::getReceiver).collect(Collectors.toList());
        if (collect.size() == 0) {
            throw new IllegalArgumentException();
        }
        return collect;
    }

    @Override
    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.byInsertionOrder.stream().sorted((a, b) -> {
            int firstCrit = Double.compare(b.getAmount(), a.getAmount());
            if (firstCrit == 0) {
                return a.getId() - b.getId();
            }
            return firstCrit;
        }).collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        if (!this.bySender.containsKey(sender)) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result = this.bySender.get(sender);
        result.sort((a, b) -> Double.compare(b.getAmount(), a.getAmount()));
        if (result.size() == 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        if (!this.byReciever.containsKey(receiver)) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result = this.byReciever.get(receiver);
        result.sort((a, b) -> {
            int firstCrit = Double.compare(b.getAmount(), a.getAmount());
            if (firstCrit == 0) {
                return a.getId() - b.getId();
            }
            return firstCrit;
        });
        if (result.size() == 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        if (!this.byTransactionStatus.containsKey(status)) {
            return new ArrayList<>();
        }

        return this.byTransactionStatus.get(status)
                .stream()
                .filter(x -> x.getAmount() <= amount)
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount())).collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        if (!this.bySender.containsKey(sender)) {
            throw new IllegalArgumentException();
        }

        return this.bySender.get(sender)
                .stream()
                .filter(x -> x.getAmount() > amount)
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount())).collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        if (!this.byReciever.containsKey(receiver)) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result =new ArrayList<>();
        for (List<Transaction> transactionList : this.byAmount.subMap(lo, hi).values()) {
            result.addAll(transactionList);
        }
        result =result.stream().filter(x->x.getReceiver().equals(receiver)).sorted((a, b) -> {
            int firstCrit = Double.compare(b.getAmount(), a.getAmount());
            if (firstCrit == 0) {
                return a.getId() - b.getId();
            }
            return firstCrit;
        }).collect(Collectors.toList());
        if(result.isEmpty()){
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Override
    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return this.byInsertionOrder.stream().filter(x-> x.getAmount()>=lo && x.getAmount()<=hi).collect(Collectors.toList());
    }

    @Override
    public Iterator<Transaction> iterator() {
        return byInsertionOrder.iterator();
    }
}
