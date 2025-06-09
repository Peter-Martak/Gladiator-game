package sk.peter;

import sk.peter.service.GameManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
