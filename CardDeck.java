package org.example.Durak2;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    ArrayList<Card> cards = new ArrayList<>();
    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public Card pop(){
        return cards.remove(cards.size()-1);
    }
}
