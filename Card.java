package org.example.Durak2;

public class Card implements Comparable<Card>{


    @Override
    public int compareTo(Card card)  {
        return this.rank.value - card.rank.value;
    }

    public class Rank  {
        private int value;

        public Rank (int value){
            this.value=value;
        }


        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this==obj)
                return true;

            if (obj==null || obj.getClass() != getClass())
                return false;

            Rank otherRank = (Rank) obj;
            return otherRank.value == this.value;
        }

         @Override
         public String toString() {
            if (value == 11){
                return "J";
            }
            if (value == 12){
                return "Q";
            }
            if (value == 13){
                return "K";
            }
            if (value == 14){
                return "A";
            }
             return Integer.toString(value);
         }
     }


    private Suit suit;
    private Rank rank;

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank(){
        return rank;
    }
    public Card(Suit suit, int value){
        this.suit = suit;
        this.rank = new Rank(value);
    }

    @Override
    public String toString() {
        return suit.toString() + rank.toString();
    }

}
