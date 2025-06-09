package sk.peter.domain;

import sk.peter.ability.Ability;

import java.util.Map;

public class Enemy extends GameCharacter {

    public Enemy(String name, Map<Ability, Integer> abilities) {
        super(name, abilities);
    }

    public void setAbility(Ability ability, int value) {
        this.abilities.put(ability, value);
    }
}
