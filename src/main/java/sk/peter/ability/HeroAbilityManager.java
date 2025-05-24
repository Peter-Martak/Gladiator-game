package sk.peter.ability;

import sk.peter.domain.Hero;
import sk.peter.utility.InputUtils;
import sk.peter.utility.PrintUtils;

import java.util.Map;

public class HeroAbilityManager {

    private final Hero hero;

    public HeroAbilityManager(Hero hero) {
        this.hero = hero;
    }

    public void spendAvailablePoints() {
        int availablePoints = hero.getAvailablePoints();

        if (availablePoints == 0) {
            System.out.println("You have no points to spend");
            return;
        }

        while (availablePoints > 0) {
            System.out.println("You have " + availablePoints + " points to spend. Choose wisely.");
            System.out.println("Choose ability to upgrade:");
            System.out.println("0. Explain Abilities");
            System.out.println("1. Attack");
            System.out.println("2. Defence");
            System.out.println("3. Dexterity");
            System.out.println("4. Skill");
            System.out.println("5. Luck");
            System.out.println("6. Health");

            final int abilityIndex = InputUtils.readInt();
            Ability ability = null;

            switch (abilityIndex) {
                case 0 -> {
                    for (Ability a : Ability.values()) {
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
            availablePoints--;
            System.out.println("You have upgraded " + ability);

            hero.updateAvailablePoints(-1);
            if (availablePoints > 1) {
                PrintUtils.printAbilities(hero);
            }
        }

        System.out.println("You have spent all your available points. Your abilities are: ");
        PrintUtils.printAbilities(hero);
    }
}
