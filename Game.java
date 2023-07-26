package org.example.Durak2;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Scanner scanner;
    ArrayList <Player> players = new ArrayList<>();
    CardDeck cardDeck;
    Suit trump; 
    int activePlayerIndex;


    public Game(Scanner scanner) {
        this.scanner = scanner;
        GameCreator gameCreator = new GameCreator(scanner);
        players=gameCreator.createPlayers();
        cardDeck=gameCreator.createCardDeck();
        trump = gameCreator.chooseTrump();
        gameCreator.giveCardsToPlayer(cardDeck, players);
        for (Player player: players) {
            System.out.println(player.toString());
        }
        System.out.println("Trump is:  " + trump.toString());
        activePlayerIndex = gameCreator.findFirstPlayer(players,trump);
    }

    public void start() {
        while(!cardDeck.isEmpty() || players.size()>1) {
            Player activePlayer = players.get(activePlayerIndex);
            Player nextPlayer = players.get((activePlayerIndex+1) % players.size());
            System.out.println("Ходит игрок " + activePlayer.getName() + " на игрока " + nextPlayer.getName());
            ArrayList<Card> inTurnCards = new ArrayList<>();
            while(activePlayer.hasCards() && nextPlayer.hasCards()) {
                Card attackCard;
                if (inTurnCards.isEmpty()){
                    attackCard = activePlayer.chooseCardToAttack();
                } else {
                    attackCard = activePlayer.chooseCardToAttack(inTurnCards);
                }
                if (attackCard == null) {
                    break;
                }
                inTurnCards.add(attackCard);
                Card defensedCard = nextPlayer.chooseCardToDefense(attackCard, trump);
                if (defensedCard == null) {
                    nextPlayer.addCards(inTurnCards);
                    this.activePlayerIndex = (this.activePlayerIndex + 1) % this.players.size();
                    break;
                }
                inTurnCards.add(defensedCard);
            }
            this.activePlayerIndex = (this.activePlayerIndex + 1) % this.players.size();
            while (activePlayer.getCards().size() < 6) {
                activePlayer.addCard(cardDeck.pop());
            }
            while (nextPlayer.getCards().size() < 6) {
                activePlayer.addCard(cardDeck.pop());
            }
        }
    }
}
