package org.example.Durak2;

import java.util.ArrayList;
import java.util.Scanner;

public class GameCreator {

    Scanner scanner;

    public GameCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<Player> createPlayers() {
        int playersCount = 0;
        do {
            System.out.println("Введите количество игроков:");
            try {
                playersCount = scanner.nextInt();
                scanner.nextLine();
                if (playersCount < 2 || playersCount > 6) {
                    System.out.println("Введите число больше одного и меньше 7");
                    playersCount = 0;
                }
            } catch (Exception e) {
                System.out.println("Некорректный ввод, введите число цифрами");
                scanner.next();
            }
        } while (playersCount <= 1);
        ArrayList<Player> players = new ArrayList<>();
        for (int i=0; i <playersCount; i++){
            System.out.println("Введите имя " + (i+1) + " игрока");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName, scanner));
        }
        return players;
    }

    public CardDeck createCardDeck() {
        CardDeck cardDeck = new CardDeck();
        for (int i = 6; i <= 14; i++) {
            for (int j = 0; j < 4; j++) {
                cardDeck.addCard(new Card(Suit.values()[j], i));
            }
        }
        cardDeck.shuffle();
        return cardDeck;
    }
    public Suit chooseTrump(){
        return Suit.values()[(int) Math.floor(Math.random() * 4)];
    }



    public void giveCardsToPlayer(CardDeck cardDeck, ArrayList<Player> players){
        for (int i=0; i<6; i++){
            for (int j=0; j< players.size(); j++){
                players.get(j).addCard(cardDeck.pop());
            }
        }
    }
    public int findFirstPlayer(ArrayList <Player> players, Suit trump) {
        int firstPlayerIndex = 0;
        Card minTrumpCard = null;

        for (int i=0; i<players.size(); i++){
            for (Card card: players.get(i).getCards()){
                if (trump.equals(card.getSuit())){
                    if (minTrumpCard==null){
                        minTrumpCard=card;
                        firstPlayerIndex = i;
                    } else {
                        if (minTrumpCard.compareTo(card)>0) {
                            minTrumpCard = card;
                            firstPlayerIndex = i;
                        }
                    }
                }
            }
        }
        return firstPlayerIndex;
    }
}
