package sk.peter;

import java.util.HashMap;
import java.util.Map;

public class Hero {
    private String name;
    private Map<Ability, Integer> abilities;

    public Hero(String name){
        this.name = name;
        this.abilities = this.getInitialAbilities();
    }

    public String getName() {
        return name;
    }

    private Map<Ability, Integer> getInitialAbilities(){
        return new HashMap<Ability, Integer>(Map.of(
                Ability.ATTACK, 1,
                Ability.DEFENCE, 1,
                Ability.DEXTERITY, 1,
                Ability.SKILL, 1,
                Ability.LUCK, 1,
                Ability.HEALTH, 50
        ));
    }

    public Map<Ability, Integer> getAbilities() {
        return abilities;
    }
}
