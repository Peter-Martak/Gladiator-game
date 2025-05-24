package sk.peter.service;

import sk.peter.ability.Ability;
import sk.peter.ability.HeroAbilityManager;
import sk.peter.constatn.Constant;
import sk.peter.domain.Hero;
import sk.peter.utility.InputUtils;
import sk.peter.utility.PrintUtils;

public class GameManager {
    private final Hero hero;
    private final HeroAbilityManager heroAbilityManager;

    private int currentLevel;

    public GameManager() {
        this.hero = new Hero(" ");
        this.heroAbilityManager = new HeroAbilityManager(this.hero);
        this.currentLevel = Constant.INITIAL_LEVEL;
    }

    public void startGame() {
        gameInit();

        while(this.currentLevel < 5){
            System.out.println("0. Fight Level " + this.currentLevel);
            System.out.println("1. Upgrade abilities (" + hero.getAvailablePoints() + " points left)");
            System.out.println("2. Save Game");
            System.out.println("3. Exit Game");

            final int choice = InputUtils.readInt();

            switch (choice){
                case 0 -> {
                    //TODO FIGHT
                    this.currentLevel++;
                }
                case 1 -> {
                    upgradeAbilites();
                }
                case 2 -> {
                    //TODO SAVE
                }
                case 3 -> {
                    System.out.println("Are you sure?");
                    System.out.println("0. No");
                    System.out.println("1. Yes");
                    final int exitChoice = InputUtils.readInt();
                    if (exitChoice == 1){
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
            case 0 -> {}
            case 1 -> this.heroAbilityManager.spendAvailablePoints();
            case 2 -> this.heroAbilityManager.removePoints();
            case 3 -> System.out.println("Invalid choice");
        }
    }


    private void gameInit(){
        System.out.println("Welcome to the Gladiator game!");
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