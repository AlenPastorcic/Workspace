package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class House {
    private Map<String, Game> games = new HashMap<>();
    /* Step 2 - Instantiating the object calls the constructor */
    public House() {
        /* Step 3 - We create five slot games and five table games and added to map */
        for (int i = 0; i < 5; i++) {
            TableGame tablegame = new TableGame();
            SlotGame slotGame = new SlotGame();
            games.put(tablegame.getId(), tablegame);
            tablegame.setName("Blackjack");
            games.put(slotGame.getId(), slotGame);
            slotGame.setName("Super Slots");
        }

        File f = new File("games.csv");
        if (f.exists() && f.isFile()) {
            /* Open the file and read each line */
            try (Scanner reader = new Scanner(f)) {
                String lineOfText;
                if (reader.hasNextLine()) {
                    lineOfText = reader.nextLine();
                }
                while (reader.hasNextLine()) {
                    lineOfText = reader.nextLine();
                    String[] strings = lineOfText.split(",");
                    if (strings[1].equalsIgnoreCase("SLOT")) {
                        SlotGame slotGame = new SlotGame(strings[0]);
                        slotGame.setName(strings[2]);
                        games.put(slotGame.getId(), slotGame);
                    }
                    else if (strings[1].equalsIgnoreCase("TABLE")) {
                        TableGame tableGame = new TableGame(strings[0]);
                        tableGame.setName(strings[2]);
                        games.put(tableGame.getId(), tableGame);
                    }
                }
            }catch (FileNotFoundException e) {
                System.out.println("Error found");


            }

        }
    }

    public void run() {
        /* Step 4 - We loop thru the map, showing each game of either type */
        for (Map.Entry<String, Game> game : games.entrySet()) {
            System.out.println(game.getKey() + ": " + game.getValue());
        }

    }

    public void saveGames() {
        File f = new File("games.csv");
        try (PrintWriter writer = new PrintWriter(f)) {
            writer.println("GameId,Type,Name,Location,OneChips,FIveChips,TenChips");
            for (Map.Entry<String, Game> gameEntry : games.entrySet()) {
                Game game = gameEntry.getValue();
                writer.print(game.getId());
                if (game instanceof SlotGame) {
                    writer.print("SLOT");
                } else if (game instanceof) {

                }
            }
    }

    private static int idGeneration = 1;

    public static String generateNewId(String prefix) {
        String generatedId = prefix + idGeneration;
        idGeneration++;
        return generatedId;
    }




}
