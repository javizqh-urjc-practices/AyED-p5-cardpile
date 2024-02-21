public class Card {

    private static final int minValue = 1;
    private static final int maxValue = 10;
    private static final char minSuit = 'A';
    private static final char maxSuit = 'D';

    private int value;
    private char suit;
    private boolean valid;

    Card(int value, char suit){
        this.value=value;
        this.suit=suit;
        this.valid = validateCard();
    }

    private boolean validateCard(){
        return validateSuit() && validateValue(); 
    }

    private boolean validateSuit() {
        return ! (this.suit<minSuit || this.suit>maxSuit);
    }

    private boolean validateValue() {
        return ! (this.value<minValue || this.value>maxValue);
    }

    public boolean isValid(){
        return this.valid;
    }

    public String toString(){
        String stringCard = this.value +" "+this.suit;
        return stringCard;
    }

    public boolean equals(Object other){
        Card checkCard = (Card) other;
        String checkString = checkCard.toString();
        String actualString = toString();
        if (checkString.equals(actualString)){
            return true;
        }
        return false;
    }
}
