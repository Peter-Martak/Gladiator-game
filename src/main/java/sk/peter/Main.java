package sk.peter;

import sk.peter.service.GameManager;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
