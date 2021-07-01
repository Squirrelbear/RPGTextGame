package DemoProject;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private Map map;
    private Random rand;
    private Scanner scan;

    public Game() {
        rand = new Random();
        scan = new Scanner(System.in);
    }

    public void startGame() {
        createPlayer();
        map = new Map(rand);

        String input;
        int enemiesDefeated = 0;
        while(player.getPlayerHealth() > 0) {
            int encounterEvent = 0;
            while(encounterEvent <= 0) {
                map.printMap();
                System.out.print("Where do you want to go: ");
                input = scan.nextLine();
                encounterEvent = map.movePlayer(input);
            }

            Encounter encounter = new Encounter(player, rand, scan);
            encounter.runEncounter();
            if(player.getPlayerHealth() > 0) {
                enemiesDefeated++;
                player.healPlayer();
                player.restoreMana();

                if(map.allEnemiesDefeated()) {
                    System.out.println("You defeated all the enemies! You win!");
                    return;
                }
            }
        }
        System.out.println("Oh no! You were defeated.");
        System.out.println("You defeated " + enemiesDefeated + " enemies before you fell!");
    }

    public void createPlayer() {
        System.out.println("Welcome new player!");
        System.out.print("Enter your name: ");
        String playerName = scan.nextLine().trim();
        player = new Player(playerName);
    }
}
