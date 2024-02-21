public class LinkedLists {

    // Pila
    private Node first;
    private int size;

    private class Node {
        Card card;
        Node next;
    }

    CardStack(){
        this.first = null;
    }

    public void push(Card card) {
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.card = card;
        this.first.next = oldFirst;
        this.size++;
    }

    public Card pop() {
        if (size == 0) {
            return null;
        }
        Card card = first.card;
        first = first.next;
        this.size--;
        return card;
    }

    public int length() {
        return this.size;
    }

    public String toString() {
        String output = "]";
        Node oldFirst = this.first;
        for (int cardNumber = 0; cardNumber < this.size; cardNumber++) {
            Card card = first.card;
            first = first.next;
            output = card.toString() + output;
            if (cardNumber < this.size - 1) {
                output = ", " + output;
            }
        }
        output = "[" + output;
        this.first = oldFirst;
        return output;
    }

    // ------------------------------------------------------------------------------------------------------
    // Cola
    private Node first;
    private Node last;
    private int n = 0;

    private class Node {
        // clase anidada para definir nodos
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        // Añadir item al final de la lista
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    public Item dequeue() {
        // eliminar item del comienzo de la lista
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
    // ------------------------------------------------------------------------------------------------------
    // Bolsa

    private Node first; // primer nodo de la lista
    private int n;

    private class Node {
        // clase anidada privada para definir nodos
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        // Añadir item a la bolsa: igual que push() de la pila
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

}
