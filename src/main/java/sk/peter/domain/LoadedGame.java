package sk.peter.domain;

public class LoadedGame {
    private final Hero hero;
    private int level;

    public LoadedGame(Hero hero, int level) {
        this.hero = hero;
        this.level = level;
    }

    public Hero getHero() {
        return hero;
    }

    public int getLevel() {
        return level;
    }
}
