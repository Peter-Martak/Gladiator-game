package sk.peter.domain;

import sk.peter.ability.Ability;
import sk.peter.constatn.Constants;

import java.util.HashMap;
import java.util.Map;

public class Hero extends GameCharacter{
    private int availablePoints;

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public Hero(String name){
        super(name, new HashMap<>());
        this.abilities = this.getInitialAbilities();
        this.availablePoints = Constants.INITIAL_ABILITY_POINTS;
    }

    public Hero(String name, Map<Ability, Integer> abilities, int availablePoints) {
        super(name,abilities);
        this.availablePoints = availablePoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbilities(Map<Ability, Integer> abilities) {
        this.abilities = abilities;
    }

    public void updateAbility(Ability ability , int delta) {
        if (ability.equals(Ability.HEALTH)) {
            this.abilities.put(ability, abilities.get(ability) + delta * Constants.HEALTH_OF_ONE_POINT);
        } else {
            this.abilities.put(ability, abilities.get(ability) + delta);
        }
    }

    public void updateAvailablePoints(int delta){
        this.availablePoints += delta;
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
    public int getAvailablePoints() {
        return availablePoints;
    }


    public void setAbility(Ability ability, int value) {
        this.abilities.put(ability, value);
    }
}
