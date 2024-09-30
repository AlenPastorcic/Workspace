package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class House {
    private Map<String, Game> games = new HashMap<>();

    /* Step 2 - Instantiating the object calls the constructor */
    public House() {
        /* Step 3a - We create five slot games and five table games and added to map */
//        for (int i = 0; i < 5; i++) {
//            TableGame tableGame = new TableGame();
//            SlotGame slotGame = new SlotGame();
//            games.put(tableGame.getId(), tableGame);
//            tableGame.setName("Blackjack");
//            games.put(slotGame.getId(), slotGame);
//            slotGame.setName("Super Slots");
//        }

        /* Step 3b - We read the CSV to get the games to put in the map */
        File f = new File("games.csv");
        if (f.exists() && f.isFile()) {
            /* Step 3c - Open file and read each line */
            try (Scanner reader = new Scanner(f)) {
                String lineOfText;
                if (reader.hasNextLine()) {
                    lineOfText = reader.nextLine();
                }
                while (reader.hasNextLine()) {
                    lineOfText = reader.nextLine();
                    String[] strings = lineOfText.split(",");
                    Game game = null;
                    if (strings[1].equalsIgnoreCase("SLOT")) {
                        game = new SlotGame(strings[0]);
                    }
                    else if (strings[1].equalsIgnoreCase("TABLE")) {
                        game = new TableGame(strings[0]);

                    }

                    if (game != null) {
                        game.setName(strings[2]);
                        games.put(game.getId(), game);
                        game.setOneChips(Integer.parseInt(strings[4]));
                        game.setFiveChips(Integer.parseInt(strings[5]));
                        game.setTenChips(Integer.parseInt(strings[6]));
                        game.setLocation(strings[3]);
                    }
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("Unable to open games.csv");
            }
        }
        else {
            System.out.println("Unable to open games.csv");
        }
    }

    public void run() {
        /* Step 4 - We loop through map, showing each game of either type */
        for (Map.Entry<String, Game> game : games.entrySet()) {
            System.out.println(game.getKey() + ": " + game.getValue());
        }

        saveGames();

    }

    public void saveGames() {
        File f = new File("games.csv");
        try (PrintWriter writer = new PrintWriter(f)) {
            writer.println("GameId,Type,Name,Location,OneChips,FiveChips,TenChips");
            for (Map.Entry<String, Game> gameEntry : games.entrySet()) {
                Game game = gameEntry.getValue();
                game.setOneChips(game.getOneChips() + 1);
                writer.print(game.getId() + ",");
                if (game instanceof SlotGame) {
                    writer.print("SLOT,");
                }
                else if (game instanceof TableGame) {
                    writer.print("TABLE,");
                }
                else {
                    writer.print("UNKNOWN,");
                }
                writer.print(game.getName() + "," + game.getLocation() + "," + game.getOneChips() + ",");
                writer.println(game.getFiveChips() + "," + game.getTenChips());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Unable to open games.csv");
        }
    }

    private static int idGeneration = 1;

    public static String generateNewId(String prefix) {
        String generatedId = prefix + idGeneration;
        idGeneration++;
        return generatedId;
    }


}
