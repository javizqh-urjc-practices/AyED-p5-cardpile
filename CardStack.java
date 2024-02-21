public class CardStack {
    
    private Node first;
    private int size;

    private class Node{
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

    public Card pop(){
        if (size==0){
            return null;
        }
        Card card = first.card;
        first = first.next;
        this.size--;
        return card;
    }

    public int length(){
        return this.size;
    }

    public String toString(){
        String output = "]";
        Node oldFirst = this.first;
        for (int cardNumber = 0; cardNumber < this.size; cardNumber++){
            Card card = first.card;
            first = first.next;
            output = card.toString() + output; 
            if (cardNumber<this.size-1){ 
                output= ", " + output;
            }
        }
        output = "[" + output;
        this.first= oldFirst;
        return output;
    }


}
