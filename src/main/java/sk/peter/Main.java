package sk.peter;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Gladiator game!");
        System.out.println("Please enter your name");
        final String name = InputUtils.readString();
        final Hero hero = new Hero(name);
        System.out.println("Hello: " + hero.getName() + " Let's start the game!");
        System.out.println("Your abilites:");
        for (Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()){
            System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
        }
        System.out.println();
        spendAvailablePoints(hero);

        for (Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()){
            System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
        }
    }

    public static void spendAvailablePoints(Hero hero){
        int availablePoints = hero.getAvailablePoints();

        if (availablePoints == 0 ){
            System.out.println("You have no points to spend");
            return;
        }
        
            while (availablePoints > 0){
                System.out.println("You have " + availablePoints + " points to spend. Choose wisely.");
                System.out.println("Choose ability to upgrade:");
                System.out.println("0. Explain Abilities");
                System.out.println("1. Attack");
                System.out.println("2. Defence");
                System.out.println("3. Dexterity");
                System.out.println("4. Skill");
                System.out.println("5. Luck");
                System.out.println("6. Health");
                System.out.println();

                final int abilityIndex = InputUtils.readInt();
                Ability ability = null;

                    switch (abilityIndex){
                        case 0 -> {
                            for (Ability a : Ability.values()){
                                System.out.println(a + ": " + a.getDescription());
                                System.out.println();
                            }
                            continue;
                        }
                        case 1 -> ability = Ability.ATTACK;
                        case 2 -> ability = Ability.DEFENCE;
                        case 3 -> ability = Ability.DEXTERITY;
                        case 4 -> ability = Ability.SKILL;
                        case 5 -> ability = Ability.LUCK;
                        case 6 -> ability = Ability.HEALTH;
                        default -> System.out.println("you must enter number 0-6");
                }
                hero.updateAbility(ability, 1);
                System.out.println("You have upgraded " + ability);

                hero.updateAvailablePoints(-1);
                if (availablePoints > 1){
                    for (Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()){
                        System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
                    }
                    System.out.println();
                }
                availablePoints--;
            }

        System.out.println("You have spent all your available points. Your abilities are: ");
        for (Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()){
            System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
        }
        System.out.println( );
    }
}
