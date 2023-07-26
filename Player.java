package org.example.Durak2;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;

    Scanner scanner;
    ArrayList<Card> cards = new ArrayList<>();

    public Player(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    public void addCard (Card card){
        cards.add(card);
    }

    public void addCards (ArrayList <Card> cardsToAdd){
        cards.addAll(cardsToAdd);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        String cardsString = "";
        for (int i=0; i< cards.size(); i++){
            cardsString += (i + 1) + ") " + cards.get(i).toString()+"\n";
        }
        return "Имя: " + name.toString() + "\n" + cardsString;
    }

    public Card chooseCardToAttack() {
        System.out.println(this.toString());
        System.out.println("Выберите карту");
        int cardIndex = scanner.nextInt();
        scanner.nextLine();
        return this.cards.remove(--cardIndex);
    }
    public Card chooseCardToAttack(ArrayList<Card> inTurnCards) {
        ArrayList<Integer> availableCardsIndexes = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            for (Card inTurnCard : inTurnCards){
                if (inTurnCard.getRank().equals(card.getRank())){
                    availableCardsIndexes.add(i);
                }
            }
        }
        if (availableCardsIndexes.isEmpty()) {
            System.out.println("Нет карт для выбора");
            return null;
        }
        System.out.println("Выберите карту для атаки");
        for (int i = 0; i < availableCardsIndexes.size(); i++){
            System.out.println((i + 1) + ") "+ cards.get(availableCardsIndexes.get(i)));
        }
        int readedIndex = scanner.nextInt();
        scanner.nextLine();
        return cards.remove(availableCardsIndexes.get(readedIndex - 1).intValue());
    }
    public Card chooseCardToDefense(Card attackCard, Suit trump) {
        int avaiableCardsCount = 0;
        for(Card card:getCards()) {
            try {
                if(Helper.canDefense(card,attackCard,trump)) {
                    avaiableCardsCount ++;
                }
            } catch (Exception e){ }
        }
        if (avaiableCardsCount==0){
            System.out.println("Нет карт, чтобы отбиться");
            return null;
        }
        System.out.println(this.toString());
        Card choosedCard = null;
        int choosedCardIndex = -1;
        while (choosedCard == null) {
            System.out.println("Выберите карту для защиты \n" + attackCard.toString());
            System.out.println(this.toString());
            choosedCardIndex = scanner.nextInt();
            scanner.nextLine();
            choosedCard = this.cards.get(--choosedCardIndex);
            try {
                Helper.canDefense(choosedCard,attackCard,trump);
            } catch (Exception e){
                choosedCard = null;
                System.out.println(e.getMessage());
            }
        }
        return cards.remove(choosedCardIndex);
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }
}
