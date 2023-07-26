package org.example.Durak2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);
        game.start();
        scanner.close();

    }
//в конце игры дублируются карты
}
