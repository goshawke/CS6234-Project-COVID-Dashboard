package edu.gwu.coviddashboard;

public class ObjectCardList {

    private String cardlist_firstline;
    private String cardlist_secondline;
    private int cardlist_image;

    // Constructor
    public ObjectCardList(String cardlist_firstline, String cardlist_secondline, int cardlist_image) {
        this.cardlist_firstline = cardlist_firstline;
        this.cardlist_secondline = cardlist_secondline;
        this.cardlist_image = cardlist_image;
    }

    // Getter and Setter
    public String getCardlist_firstline() {
        return cardlist_firstline;
    }

    public void setCardlist_firstline(String cardlist_firstline) {
        this.cardlist_firstline = cardlist_firstline;
    }

    public String getCardlist_secondline() {
        return cardlist_secondline;
    }

    public void setCardlist_secondline(String cardlist_secondline) {
        this.cardlist_secondline = cardlist_secondline;
    }

    public int getCardlist_image() {
        return cardlist_image;
    }

    public void setCardlist_image(int cardlist_image) {
        this.cardlist_image = cardlist_image;
    }
}

