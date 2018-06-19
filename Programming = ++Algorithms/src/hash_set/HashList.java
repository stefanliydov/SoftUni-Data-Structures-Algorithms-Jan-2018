package hash_set;

public class HashList<T extends Comparable<T>> {

    private Node arr[];

    @SuppressWarnings("unchecked")
    public HashList() {
        this.arr = new HashList.Node[16];
    }

    public void test(T first, T sec) {
        Node node1 = new Node(first);
        Node node2 = new Node(sec);
        System.out.println(node1.hashCode());
        System.out.println(node2.hashCode());
        System.out.println(node1.equals(node2));
    }

    public void add(T element) {
        Node node = new Node(element);
        int hashCode = node.hashCode();
        Node olderNode = this.arr[hashCode];
        if (olderNode != null) {
            if (node.equals(olderNode)) {
                return;
            }
            while (olderNode.next != null) {
                olderNode = olderNode.next;
                if (node.equals(olderNode)) {
                    return;
                }
            }
            olderNode.next = node;
        } else {
            this.arr[hashCode] = node;
        }
    }

    public boolean contains(T element) {
        Node node = new Node(element);
        int code = node.hashCode();
        Node olderNode = this.arr[code];
        if (olderNode != null) {
            if (olderNode.equals(node)) {
                return true;
            }
            while (olderNode.next != null) {
                olderNode = olderNode.next;
                if (olderNode.equals(node)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }

    public void remove(T element) {
        Node node = new Node(element);
        int code = node.hashCode();
        Node olderNode = this.arr[code];
        if (olderNode != null) {
            Node parent = olderNode;
            if (parent.equals(node)) {
                this.arr[code] = olderNode.next;
                return;
            }
            Node child = parent;
            while (olderNode.next != null) {
                olderNode = child;
                child = child.next;
                if (child.equals(node)) {
                    olderNode.next = child.next;
                    return;
                }
            }
        }
    }

    private class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            if (node.value == null) {
                return false;
            }
            return this.value.compareTo(node.value) == 0;
        }

        @Override
        public int hashCode() {
            if (this.value == null) {
                return 0;
            }
            String value = String.valueOf(this.value);
            int result = 0;
            for (int i = 0; i < value.length(); i++) {
                result += (int) value.charAt(i);
            }
            return result % 16;
        }
    }
}
