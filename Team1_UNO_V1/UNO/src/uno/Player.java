/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;

public class Player {

    private String id;
    private String name;
    private ArrayList<Card> cardInHand = new ArrayList<Card>();

    public Player() {

    }

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hand
     */
    public Card getCardFromHand(int i) {
        return this.cardInHand.remove(i);
    }

    public void setCardToHand(Card card) {
        this.cardInHand.add(card);
    }

    @Override
    public String toString() {
        return "Player:" + "id=" + id + ", name=" + name + "\n\t\tCards in hand:\n\t\t\t" + getCardInHand() + "\n\t\tTotal Value=" + claculateHandValue() + "\n\t";
    }

    /**
     * @return the hand
     */
    public ArrayList<Card> getCardInHand() {
        return cardInHand;
    }

    public void setCardInHand(ArrayList<Card> hand) {
        this.cardInHand = hand;
    }

    public int claculateHandValue() {

        int totalValue = 0;

        for (Card card : cardInHand) {
            totalValue += card.getValue();
        }

        return totalValue;
    }

}
