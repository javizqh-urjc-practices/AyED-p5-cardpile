import edu.princeton.cs.algs4.*;
import java.util.Random;

public class CardPile {

	private CardStack stack;

	CardPile(){
		this.stack = new CardStack();
	}

	public void push(Card card) {
		this.stack.push(card);
	}

	public Card pop(){
		return this.stack.pop();
	}

	public void cut(int n){
		if (n<=0){return;}

		int stackSize = this.stack.length();
		CardStack tmpStackCut = new CardStack();
		CardStack tmpStack = new CardStack();

		for (int cards=0; cards < stackSize ; cards++){
			Card card = this.pop();
			if (cards<n){
				tmpStackCut.push(card);
				continue;
			}
			
			tmpStack.push(card);
		}

		for (int cards=0; cards < stackSize ; cards++){
			Card card;
			if (cards<n){
				card = tmpStackCut.pop();
			} else {
				card = tmpStack.pop();
			}
			this.stack.push(card);
		}

	}

	public void invert() {
		int stackSize = this.stack.length();
		CardStack tmpStack = new CardStack();

		for (int cards=0; cards < stackSize ; cards++){
			Card card = this.stack.pop();
			tmpStack.push(card);
		}

		this.stack = tmpStack;
	}

	public void mix(int n, int seed) {

		Random randomNum = new Random();
		randomNum.setSeed(seed);
		int randomNumber;

		for (int repetitions = 0 ; repetitions < n ; repetitions++){
			if (this.stack.length()==0){return;}
			randomNumber = 1+randomNum.nextInt(this.stack.length()-1);
			cut(randomNumber);
			randomNumber = 1+randomNum.nextInt(this.stack.length()-1);
			mix(randomNumber);
		}
	}

	private void mix(int mixPos) {

		CardStack mixCard = new CardStack();
		CardStack tmpStackMix = new CardStack();

		for (int cards=0; cards <= mixPos ; cards++){
			Card card = this.pop();
			if (cards==mixPos-1){
				mixCard.push(card);
				continue;
			}
			tmpStackMix.push(card);
		}

		this.stack.push(mixCard.pop());

		for (int cards=0; cards < mixPos  ; cards++){
			Card card = tmpStackMix.pop();
			this.stack.push(card);
		}
	}

	public String toString() {
		return this.stack.toString();
	}

	private static void fileExist (String filename){
		try {
			In test = new In(filename);
		} catch (Exception e){
			System.err.println("Error: el fichero "+filename+" no existe");
			System.exit(1);
		}
	}
	
	private void execute(String instruction, int line) {
		String[] array = instruction.split(" ");
		switch (array[0]) {
			case "drop":
				if (array.length == 3){
					Card card = new Card(toInt(array[1]), toChar(array[2]));
					if ( ! card.isValid()){
						System.err.println("The card "+ card.toString() +" in line "+line+" is not valid.");
						System.exit(1);
					}
					this.push(card);
				} else {
					errorInstr(instruction,line);
				}
				break;
			case "take":
				if (array.length == 1){
					this.pop();
				} else {
					errorInstr(instruction,line);
				}
				break;
			case "mix":
				if (array.length == 3){
					mix(toInt(array[1]),toInt(array[2]));
				} else {
					errorInstr(instruction,line);
				}
				break;
			case "print":
				if (array.length == 1){
					StdOut.println(toString());
				} else {
					errorInstr(instruction,line); 
				}
				break;
		
			default:
				errorInstr(instruction,line);
				break;
		}
	}

	private void errorInstr(String instruction, int line){
		System.err.println("In line "+line+" the instruccion '"+instruction+"' is not supported.");
		System.exit(1);
	}

	private int toInt(String value) {
		int val = 0;
		try {
			val = Integer.parseInt(value);
		} catch (Exception e) {
			System.err.println("Error: cannot convert "+value+" to an integer.");
			System.exit(1);
		}
		return val;
	}

	private char toChar(String suit) {
		if (suit.length()!=1){
			System.err.println("Error: "+suit+" is not a valid character");
			System.exit(1);
		}
		return suit.charAt(0);
	}

	public static void main(String[] args) {
		if (args.length != 1){
			System.err.println("Usage: CardPile file.txt");
			System.exit(1);
		}
		String filename = args[0];
		fileExist(filename);
		In file = new In(filename);
		CardPile cardPile = new CardPile();
		int executeLine = 1;
		while (file.hasNextLine()){
			String instruction = file.readLine();
			cardPile.execute(instruction,executeLine);
			executeLine++;
		}
	}
}
