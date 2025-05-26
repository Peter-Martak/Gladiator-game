package sk.peter.service;

import sk.peter.ability.Ability;
import sk.peter.domain.Hero;
import sk.peter.utility.InputUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileService {

    public void savedGame(Hero hero, int level) throws IOException {
        while (true) {
            System.out.println("How do you want to name your save?");
            final String fileName = InputUtils.readString();

            final String path = "saved-games/" + fileName + ".txt";

            if (new File(path).exists()) {
                System.out.println("Game with this name is already saved");
                continue;
            }

            try {
                Files.writeString(Path.of(path), heroDataString(hero, level));
                System.out.println("Game saved");
            } catch (IOException e) {
                System.out.println("Error while saving game");
                continue;
            } catch (InvalidPathException e) {
                System.out.println("Invalid characters in file name");
                continue;
            }
            break;
        }
    }

    private String heroDataString(Hero hero, int currentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(currentLevel).append("\n")
                .append(hero.getName()).append("\n")
                .append(hero.getAvailablePoints()).append("\n");

        for (Ability ability : Ability.values()) {
            sb.append(ability).append(":").append(hero.getAbilities().get(ability)).append("\n");
        }
        return sb.toString();
    }
}
