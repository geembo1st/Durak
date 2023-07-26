package org.example.Durak2;

public class Helper {
    public static boolean canDefense(Card defendeCard, Card attackingCard, Suit trump) throws Exception {
        if (attackingCard.getSuit() != defendeCard.getSuit() && defendeCard.getSuit() != trump) {
            throw new Exception("Нельзя защититься картой другой масти (выбранная масть не козырь)");
        }
        if (attackingCard.getSuit() == defendeCard.getSuit() && attackingCard.compareTo(defendeCard) > 0) {
            throw new Exception("Нельзя защититься картой, слабее чем аттакующая");
        }
        return true;
    }
}
