package sk.peter;

import sk.peter.service.GameManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
