package sk.peter.service;

import sk.peter.ability.HeroAbilityManager;
import sk.peter.constatn.Constant;
import sk.peter.domain.Enemy;
import sk.peter.domain.Hero;
import sk.peter.domain.LoadedGame;
import sk.peter.utility.EnemyGenerator;
import sk.peter.utility.InputUtils;
import sk.peter.utility.PrintUtils;

import java.io.IOException;
import java.util.Map;

public class GameManager {
    private Hero hero;
    private final HeroAbilityManager heroAbilityManager;
    private final FileService fileService;
    private int currentLevel;

    private final Map<Integer, Enemy> enemiesByLevel;

    public GameManager() {
        this.hero = new Hero(" ");
        this.heroAbilityManager = new HeroAbilityManager(this.hero);
        this.fileService = new FileService();
        this.currentLevel = Constant.INITIAL_LEVEL;
        this.enemiesByLevel = EnemyGenerator.createEnemies();
    }

    public void startGame() throws IOException {
        gameInit();

        while (this.currentLevel <= this.enemiesByLevel.size()) {
            final Enemy enemy = enemiesByLevel.get(this.currentLevel);
            System.out.println("0. Fight Level " +enemy.getName() + " (level "+ this.currentLevel);
            System.out.println("1. Upgrade abilities (" + hero.getAvailablePoints() + " points left)");
            System.out.println("2. Save Game");
            System.out.println("3. Exit Game");

            final int choice = InputUtils.readInt();

            switch (choice) {
                case 0 -> {
                    //TODO FIGHT
                    this.currentLevel++;
                }
                case 1 -> {
                    upgradeAbilites();
                }
                case 2 -> {
                    fileService.savedGame(this.hero, this.currentLevel);
                }
                case 3 -> {
                    System.out.println("Are you sure?");
                    System.out.println("0. No");
                    System.out.println("1. Yes");
                    final int exitChoice = InputUtils.readInt();
                    if (exitChoice == 1) {
                        System.out.println("Bye!");
                        return;
                    }
                    System.out.println("Continuing... ");
                    PrintUtils.printDivider();
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void upgradeAbilites() {
        System.out.println("Your abilities");
        PrintUtils.printAbilities(this.hero);
        System.out.println();
        System.out.println("0. Go back");
        System.out.println("1. Spend points " + this.hero.getAvailablePoints() + "points to spend)");
        System.out.println("2. Remove points");
        final int choice = InputUtils.readInt();

        switch (choice) {
            case 0 -> {
            }
            case 1 -> this.heroAbilityManager.spendAvailablePoints();
            case 2 -> this.heroAbilityManager.removePoints();
            case 3 -> System.out.println("Invalid choice");
        }
    }


    private void gameInit() {
        System.out.println("Welcome to the Gladiator game!");

        System.out.println("0. Start new game");
        System.out.println("1. Load game");
        int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> System.out.println("Let's go then");
            case 1 -> {
                final LoadedGame loadedGame = fileService.loadGame();
                if (loadedGame != null){
                    this.hero = loadedGame.getHero();
                    this.currentLevel = loadedGame.getLevel();
                    return;
                }
            }
            default -> System.out.println("Invalid choice");
        }


        System.out.println("Please enter your name");
        final String name = InputUtils.readString();
        this.hero.setName(name);
        System.out.println("Hello: " + hero.getName() + " Let's start the game!");
        PrintUtils.printDivider();
        System.out.println("Your abilites:");
        PrintUtils.printAbilities(hero);
        System.out.println();
        PrintUtils.printDivider();
        this.heroAbilityManager.spendAvailablePoints();
        System.out.println();
    }
}