package sk.peter.utility;

import sk.peter.ability.Ability;
import sk.peter.domain.GameCharacter;
import sk.peter.domain.Hero;

import java.util.Map;

public class PrintUtils {

    public static void printAbilities(GameCharacter character){
        for (Map.Entry<Ability, Integer> entry : character.getAbilities().entrySet()){
            System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
        }
    }

    public static void printDivider(){
        System.out.println();
        System.out.println("---------------------------------");
    }
}
