/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<Card>();// = new ArrayList<Card>();
    //private Card card;
    //private int numCard;

    public Deck() {
        DeckCreate();
    }

    public void setCardToDeck(Card c) {
        this.cards.add(c);
    }

    public Card getCardFromDeck() {
        return this.cards.remove(0);
    }

    /**
     * @return the deck
     */
    public ArrayList<Card> getDeck() {
        return cards;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(ArrayList<Card> deck) {
        this.cards = deck;
    }

    /**
     * @return the numCard
     */
    public int getNumCard() {
        return cards.size();
    }

    public void DeckCreate() {

        String color[] = {"Red", "Yellow", "Green", "Blue", "RYGB"};
        String type[] = {"Normal", "Skip", "Reverse", "Draw2", "Wild", "WildDrawFour"};

        Card card;

        for (int c = 0; c < color.length; c++) {

            int typeIndex = 0;

            for (int n = 0; n <= 12; n++) {

                int value = n;

                if (n > 9) {
                    typeIndex++;
                    value = 20;
                }

                if (c != 4) {
                    card = new Card(color[c], type[typeIndex], value, color[c] + type[typeIndex] + value);
                    this.setCardToDeck(card);

                    if (n != 0) {
                        this.setCardToDeck(card);
                    }
                }

            }

            if (c == 4) {
                int counter = 1;
                while (counter <= 4) {

                    for (int t = 4; t < type.length; t++) {
                        card = new Card(color[c], type[t], 50, color[c] + type[t] + 50);
                        this.setCardToDeck(card);
                    }

                    counter++;
                }
            }
        }
    }

    public void ShuffleDeck() {
        Collections.shuffle(getDeck());
    }

    @Override
    public String toString() {
        return "Cards on deck: " + getNumCard() + "\n";//+cards;
    }
}
