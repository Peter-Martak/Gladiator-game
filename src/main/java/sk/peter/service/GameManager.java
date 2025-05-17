package sk.peter.service;

import sk.peter.ability.Ability;
import sk.peter.ability.HeroAbilityManager;
import sk.peter.domain.Hero;
import sk.peter.utility.InputUtils;
import sk.peter.utility.PrintUtils;

import java.util.Map;

public class GameManager {
    private final Hero hero;
    private final HeroAbilityManager heroAbilityManager;

    public GameManager() {
        this.hero = new Hero(" ");
        this.heroAbilityManager = new HeroAbilityManager(this.hero);
    }

    public void startGame() {
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
    }
}