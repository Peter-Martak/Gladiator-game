package sk.peter.utility;

import sk.peter.ability.Ability;
import sk.peter.domain.Hero;

import java.util.Map;

public class PrintUtils {

    public static void printAbilities(Hero hero){
        for (Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()){
            System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
            System.out.println();
        }
    }

    public static void printDivider(){
        System.out.println("---------------------------------");
    }
}
