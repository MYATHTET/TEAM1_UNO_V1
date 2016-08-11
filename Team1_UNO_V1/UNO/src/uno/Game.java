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

public class Game {

    private String id;
    private String gname;
    private ArrayList<Player> playerList;
    private String status;
    private Deck deck;
    private Card discardCard;

    public Game(String id, String gname, String status) {
        this.id = id;
        this.gname = gname;
        this.status = status;
        deck = new Deck();
        playerList = new ArrayList<Player>();
    }

    /**
     * @return the id
     */
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
     * @return the playList
     */
    public ArrayList<Player> getPlayList() {
        return playerList;
    }

    /**
     * @param playList the playList to set
     */
    public void setPlayerList(ArrayList<Player> playList) {
        this.playerList = playList;
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    public void removePlayer(Player player) {
        this.playerList.add(player);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * @return the discardPile
     */
    public Card getDiscardPile() {
        return discardCard;
    }

    /**
     * @param discardPile the discardPile to set
     */
    public void setDiscardPile(Card discardPile) {
        this.discardCard = discardPile;
    }

    public void startPlay() {

        deck.ShuffleDeck();

        //clear
        for (Player player : playerList) {
            player.setCardInHand(new ArrayList<Card>());

        }

        int counter = 1;
        while (counter <= 7) {

            for (Player player : playerList) {
                Card handCard = deck.getCardFromDeck();
                player.setCardToHand(handCard);

            }
            counter++;
        }

        discardCard = deck.getCardFromDeck();
    }

    public int claculateHandsValues() {

        int totalValue = 0;

        for (Player player : playerList) {
            totalValue += player.claculateHandValue();
        }

        return totalValue;
    }

    @Override
    public String toString() {
        return "Game id=" + id + "\nDiscard Card=" + discardCard + "\nStatus=" + status + "\n" + deck + "\n\t" + playerList + "\ntotal=" + claculateHandsValues();
    }

    /**
     * @return the gname
     */
    public String getGname() {
        return gname;
    }

    /**
     * @param gname the gname to set
     */
    public void setGname(String gname) {
        this.gname = gname;
    }

}
