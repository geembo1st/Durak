package org.example.Durak2;

public enum Suit {
    SPADES, HEARTS, CLUMBS, DIAMONDS;
    @Override
    public String toString() {
        String suitString = "";
        switch (this.ordinal()){
            case 2 -> suitString = "♣";
            case 1 -> suitString = "♥";
            case 3 -> suitString = "♦";
            case 0 -> suitString = "♠";
        }
        return suitString;
    }
}
