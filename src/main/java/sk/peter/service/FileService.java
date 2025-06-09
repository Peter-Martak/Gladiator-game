package sk.peter.service;

import sk.peter.ability.Ability;
import sk.peter.domain.Hero;
import sk.peter.domain.LoadedGame;
import sk.peter.utility.InputUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileService {

    public void savedGame(Hero hero, int level) {
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

    public LoadedGame loadGame() {
        while (true) {
            final File[] savedFiles = new File("saved-games").listFiles();
            if (savedFiles == null || savedFiles.length == 0){
                System.out.println("No saved games found");
                return null;
            }

            System.out.println("Enter name of save you want to load");
            for (int i = 0; i < savedFiles.length; i++){
                System.out.println(i + ". " + savedFiles[i].getName());
            }

            final int choice = InputUtils.readInt();
            if (choice < 0 || choice >= savedFiles.length){
                System.out.println("Invalid input, try again");
                continue;
            }

            final String loadGame = savedFiles[choice].getName();
            final String filePath = "saved-games/" + loadGame;

            try{
                final String heroData = Files.readString(Path.of(filePath));
                System.out.println("Game loaded");
                return this.stringToHeroData(heroData);
            } catch (IOException e) {
                System.out.println("Error while loading game!");
            }catch (InvalidPathException e){
                System.out.println("Invalid characters in file name");
            }
        }
    }

    private LoadedGame stringToHeroData(String heroData) {
        final String[] lines = heroData.split("\n");
        final int currentLevel = Integer.parseInt(lines[0]);
        final String heroName = lines[1];
        final int availablePoints = Integer.parseInt(lines[2]);

        Map<Ability, Integer> abilities = new HashMap<>();
        for(int i = 3; i < 3 + Ability.values().length; i++){
            final String[] abilityData = lines[i].split(":");
            final Ability ability = Ability.valueOf(abilityData[0]);
            final int value = Integer.parseInt(abilityData[1]);
            abilities.put(ability, value);
        }
        return new LoadedGame(
                new Hero(heroName, abilities, availablePoints), currentLevel
        );
    }
}


//public int loadGame(Hero hero) {
//    File[] files = printSavedGames();
//    int currentLevel;
//    while (true) {
//        int choice = InputUtils.readInt();
//        if (choice < files.length) {
//            String path = files[choice].toString();
//            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
//                currentLevel = Integer.parseInt(reader.readLine());
//                hero.setName(reader.readLine());
//                hero.setAvailablePoints(Integer.parseInt(reader.readLine()));
//                Map<Ability, Integer> mapAbilities = new HashMap<>();
//                for (Ability ability : Ability.values()) {
//                    String[] riadok = reader.readLine().split(":");
//                    mapAbilities.put(ability, Integer.parseInt(riadok[1]));
//                }
//                hero.setAbilities(mapAbilities);
//                break;
//            } catch (IOException e) {
//                System.out.println("Invalid input");;
//            }
//        }else {
//            System.out.println("Invalid input");
//        }
//    }
//    return currentLevel;
//}

//private File[] printSavedGames() {
//    String path = "C:\\Users\\admin\\Desktop\\JAVA\\Street of Code\\gladiator-java-game\\saved-games";
//    File directory = new File(path);
//    File[] files = directory.listFiles();
//
//    System.out.println("Enter name of save you want to load:");
//    if (files != null) {
//        for (int i = 0; i < files.length; i++) {
//            System.out.println(i + ". " + files[i].getName());
//        }
//    }
//    return files;
//}