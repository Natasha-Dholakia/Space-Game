import edu.princeton.cs.algs4.*;

import java.util.Random;

public class Game {
    public static void main(String[] args) {

        int numPlayers;                                     // Accept number of players from command line argument between 1 - 4
        try {
            numPlayers = Integer.parseInt(args[0]);         // Number of players from command line argument
        } catch (Exception e) {
            numPlayers = 2;                                 // Exception is 2 players
        }
        if (numPlayers > 4) {
            StdOut.println("Too many players. Reduced to 4.");
            StdOut.println();
            numPlayers = 4;                                 // Max number of players set to 4
        }

        In in = new In("spaces.txt");                        // Create board map from space.txt file
        Digraph board = new Digraph(in);

        // Variables
        final int NODES = board.V();                        // Variable for number of nodes
        final int DICE = 6;                                 // playing with 6 sided dice
        Integer[] playerPos = new Integer[numPlayers];      // player position on the board (nodes)
        Integer[] playerScore = new Integer[numPlayers];    // player score tally
        final int winningScore = 50;                        // winning condition

        Integer[] points = new Integer[NODES];              // points to collect at each node
        In inP = new In("scoringSystem.txt");               // Assigns points to each node
        for (int i = 0; i < NODES; i++) {                   // if 0, it is a trap
            points[i] = inP.readInt();                      // if 1, it is a bonus
        }

        String[] planetNames = new String[NODES];           // Names of nodes
        In inN = new In("planetNames.txt");                       // Assign names from file
        for (int i = 0; i < NODES; i++) {
            planetNames[i] = inN.readLine();
        }

        for (int i = 0; i < numPlayers; i++) {              // Initialize values
            playerPos[i] = 14;                              // Starting position in middle of board (node 14)
            playerScore[i] = 0;                             // Player score starts at 0
        }

        // Variables for Game Statistics
        int[] TotalDieRoll = new int[numPlayers];
        int[] playerTurns = new int[numPlayers];
        int[] totalLost = new int[numPlayers];
        Integer[] visitedSpace = new Integer[board.V()];     // Array to store times visit a space
        for (int v = 0; v < board.V(); v++) {
            visitedSpace[v] = 0;                             // Initialize to 0
        }
        Integer[] commonDieRolls = new Integer[DICE];                       // Array to hold most common die rolls
        for (int j = 0; j < DICE; j++)                                      // Initialize all the values in commonDiceRolls to 0
            commonDieRolls[j] = 0;
        Integer[][] playerCommonRolls = new Integer[numPlayers][DICE];      // Array to hold player's common die roll
        for (int j = 0; j < numPlayers; j++) {                              // Initialize all the values in all arrays to 0
            for (int k = 0; k < DICE; k++) {
                playerCommonRolls[j][k] = 0;
            }
        }
        int trapTotal = 0;
        int boostTotal = 0;
        String[] rank = {"first ", "second", "third ", "fourth"};           // rank for statistics

        // Add Canvas
        Map.drawMap();

        // Game Start
        StdOut.println("*************************************************************");
        StdOut.println("                      Galactic Pane");
        StdOut.println("                      " + numPlayers + " player game");
        StdOut.println("In a galaxy filled with riches and dangers, space explorers");
        StdOut.println("must travel to new planets to collect resources and transport");
        StdOut.println("them back home to save their planet and civilization.");
        StdOut.println("                      Game Begins!");
        StdOut.println("*************************************************************");

        // Main game loop
        for (int i = 0; i < numPlayers * 1000; i++) {       // Number of turns to play through
            int player = i % numPlayers;                    // Calculate which player's turn it is
            Random r = new Random();                        // Random number
            int roll = r.nextInt((DICE)) + 1;               // Roll the die to begin moving
            playerTurns[player]++;                          // Increment player turn
            TotalDieRoll[player] += roll;                   // Total spaces moved by player
            commonDieRolls[roll - 1]++;                     // Increment each time roll value comes up
            playerCommonRolls[player][roll - 1]++;          // Increment die roll for each player

            if (player == 0) {
                StdOut.println("              ***** Turn " + playerTurns[player] + " *****");
            }

            StdOut.print("Player " + (player + 1));         // Print current player
            StdOut.println(" rolls a " + roll);             // print roll

            if (playerScore[player] < winningScore) {
                BreadthFirstDirectedPaths bfp = new BreadthFirstDirectedPaths(board, playerPos[player]);

                // Select new position to move to based on roll
                Stack<Integer> stack = new Stack<Integer>();        // Create stack
                for (int v = 0; v < board.V(); v++) {               // for every vertex
                    if (bfp.hasPathTo(v)) {                         // if there is a path to it from the starting location
                        if (roll == bfp.distTo(v)) {                // and if the roll is equal to the distance
                            stack.push(v);                          // push it onto the stack
                        }                                           // This creates stack of possible destinations
                    }
                }
                int stackSize = stack.size();                       // fixed stack size needed in for loop
                Integer[] destOptions = new Integer[stackSize];     // Create array size of available destinations
                for (int j = 0; j < stackSize; j++) {               // For every object in the stack
                    destOptions[j] = stack.pop();                   // Add it to array and remove from stack
                }
                StdRandom.shuffle(destOptions);                     // Shuffle options to select randomly

                playerPos[player] = destOptions[0];                 // Move player to new position
                visitedSpace[playerPos[player]]++;                  // Increment when new space is visited

                // Animation move to new location
                // Use bfp.pathTo(playerPos[player]); to jump to each node to final destination

                // Traps, Bonuses, and Scoring
                switch (playerPos[player]) {
                    case 3:
                        // Player loses all points and returns to starting position
                        trapTotal++;                                // Number of traps set off
                        totalLost[player] += playerScore[player];   // Total points lost by player
                        playerScore[player] = 0;                    // Lose all points
                        playerPos[player] = 14;                     // Return to Home Base Planet
                        visitedSpace[playerPos[player]]++;          // Increment when new space is visited
                        StdOut.println("         has fallen into a black hole and lost all cargo! Returns to Home Base");
                        break;
                    case 12:
                        trapTotal++;                                    // Number of traps set off
                        totalLost[player] += playerScore[player] / 2;   // Total points lost by player
                        playerScore[player] /= 2;                       // Lose half of points
                        StdOut.println("         has been been hit by an asteroid and loses half of the cargo!");
                        break;
                    case 18:
                        trapTotal++;                                        // Number of traps set off
                        totalLost[player] += playerScore[player] * 3 / 4;   // Total points lost by player
                        playerScore[player] = playerScore[player] * 3 / 4;  // Lose a quarter of resources
                        StdOut.println("         has been been hit by an asteroid and loses a quarter of the cargo!");
                        break;
                    case 24:
                        trapTotal++;                                        // Number of traps set off
                        playerScore[player] -= 10;                          // Lose 10 points
                        int lost = 10;
                        if (playerScore[player] < 0) {                      // Can't lose more than 10
                            lost = 10 - Math.abs(playerScore[player]);      // lose only current points if less than 10
                            playerScore[player] = 0;
                        }
                        totalLost[player] += lost;                          // Total points lost by player
                        StdOut.println("         has been attacked by Martian Spaceships! They take away " + lost + " resources!");
                        break;
                    case 28:
                        trapTotal++;                                        // Number of traps set off
                        playerScore[player] -= 5;
                        lost = 5;
                        if (playerScore[player] < 0) {
                            lost = Math.abs(playerScore[player]);
                            playerScore[player] = 0;
                        }
                        totalLost[player] += lost;                          // Total points lost by player
                        StdOut.println("         has to pay " + lost + " resources to the Space Federation!");
                        break;
                    case 7:
                        boostTotal++;                               // Total number of boosts
                        playerScore[player] *= 2;
                        StdOut.println("         has found a binary star system and doubles resources");
                        break;
                    case 16:
                        boostTotal++;                               // Total number of boosts
                        playerScore[player] += 10;
                        StdOut.println("         has found an abandoned space station with 10 resources");
                        break;
                    case 22:
                        boostTotal++;                               // Total number of boosts
                        playerScore[player] += 5;
                        playerPos[player] = StdRandom.uniform(NODES);
                        visitedSpace[playerPos[player]]++;                  // Increment when new space is visited
                        StdOut.println("         has found a wormhole! Collects 5 resources and space jumps");
                        playerScore[player] += points[playerPos[player]];   // Add points to player score
                        StdOut.println("         has space jumped to planet " + planetNames[playerPos[player]] + " and collects " + points[playerPos[player]] + " resources");
                        break;
                    case 26:
                        boostTotal++;                               // Total number of boosts
                        playerScore[player] += 10;
                        StdOut.println("         has received 10 resources from the Space Federation!");
                        break;
                    case 30:
                        boostTotal++;                               // Total number of boosts
                        playerScore[player] += 10;
                        StdOut.println("         has found and abandoned Space Federation station with 10 resources");
                        break;
                    default:
                        playerScore[player] += points[playerPos[player]];   // Add points to player score
                        if (points[playerPos[player]] > 0) {
                            StdOut.println("         has travelled to planet " + planetNames[playerPos[player]] + " and collects " + points[playerPos[player]] + " resources");
                            points[playerPos[player]] = 0;
                        } else {
                            StdOut.println("         has travelled to planet " + planetNames[playerPos[player]] + ". All resources have been collected");
                        }
                        break;
                }
                StdOut.println("         has " + playerScore[player] + " resources");
            }
            if (playerScore[player] >= winningScore) {
                playerPos[player] = 14;                             // Return to Base
                StdOut.println("*************************************************************");
                StdOut.println("Game Over: Player " + (player + 1) + " has saved the home planet and civilization with " + playerScore[player] + " resources");
                StdOut.println("*************************************************************");
                break;
            }
        }

        // Game Rankings
        StdOut.println();
        StdOut.println("    Rankings");                                         // Print rankings
        StdOut.println("*************************************************************");
        BinarySearchST<Integer, Integer> bs = new BinarySearchST<Integer, Integer>();   // Create BSST
        for (int i = 0; i < numPlayers; i++)                                            // For every player
            bs.put(playerScore[i], i);                                                  // put score and player number in ST
        for (int i = 0; i < numPlayers; i++) {                                          // For every player
            StdOut.print("Player " + (bs.get(bs.max()) + 1) + " comes in ");            // Print the top player and score in ST
            StdOut.print(rank[i] + " with ");
            StdOut.println(bs.max() + " resources");                                    // then delete to go down the list
            bs.deleteMax();
        }

        // Game Statistics
        StdOut.println();
        StdOut.println("    Game Statistics");
        StdOut.println("*************************************************************");

        // Visited Locations
        StdOut.print("Most visited location(s): ");
        MaxPQ<Integer> pq = new MaxPQ<Integer>();                                           // Used to find largest value in dice rolls
        for (int i = 0; i < board.V(); i++)                                                 // the common die rolls for each die are stored using MaxPQ
            pq.insert(visitedSpace[i]);                                                     // this allows to get the most common (max) die value later
        BinarySearchST<Integer, Integer> visit = new BinarySearchST<Integer, Integer>();    // Used for key value pair
        for (int i = 0; i < board.V(); i++)
            visit.put(i, visitedSpace[i]);                                                   // put keys(player) and values(num of rolls) into ST
        for (int i = 0; i < board.V(); i++) {                                               // For every die number (1-6)
            if (visit.get(i).equals(pq.max()))                                              // check if maximum value in pq is equal to the number of rolls in ST
                StdOut.print("[" + planetNames[(visit.select(i))] + "] ");              // return the index + 1 that represents the die number

        }                                                                                   // allows to print multiple in case of tie
        StdOut.print(": " + pq.max() + " visits");
        StdOut.println();
        for (int i = 0; i < board.V(); i++) {
            StdOut.println(visitedSpace[i] + " visits to " + planetNames[i]);
        }

        // Traps
        StdOut.println();
        StdOut.println("Total traps activated: " + trapTotal);
        StdOut.println("Times " + planetNames[3] + " activated : " + visitedSpace[3]);
        StdOut.println("Times " + planetNames[12] + " activated : " + visitedSpace[12]);
        StdOut.println("Times " + planetNames[18] + " activated : " + visitedSpace[18]);
        StdOut.println("Times " + planetNames[24] + " activated : " + visitedSpace[24]);
        StdOut.println("Times " + planetNames[28] + " activated : " + visitedSpace[28]);
        // Bonuses
        StdOut.println();
        StdOut.println("Total bonuses activated: " + boostTotal);
        StdOut.println("Times " + planetNames[7] + " activated : " + visitedSpace[7]);
        StdOut.println("Times " + planetNames[16] + " activated : " + visitedSpace[16]);
        StdOut.println("Times " + planetNames[22] + " activated : " + visitedSpace[22]);
        StdOut.println("Times " + planetNames[26] + " activated : " + visitedSpace[26]);
        StdOut.println("Times " + planetNames[30] + " activated : " + visitedSpace[30]);

        // Dice Statistics
        StdOut.println();
        for (int j = 0; j < DICE; j++)                                                      // Times each die was rolled
            StdOut.println("Total rolls for die [" + (j + 1) + "] : " + commonDieRolls[j]);
        StdOut.println();

        StdOut.print("Most common die roll(s) for the game : ");
        for (int i = 0; i < numPlayers; i++)                                                // the common die rolls for each die are stored using MaxPQ
            pq.insert(commonDieRolls[i]);                                                   // this allows to get the most common (max) die value later
        BinarySearchST<Integer, Integer> die = new BinarySearchST<Integer, Integer>();      // Used for key value pair
        for (int i = 0; i < DICE; i++)
            die.put(i, commonDieRolls[i]);                                                  // put keys(player) and values(num of rolls) into ST
        for (int i = 0; i < DICE; i++) {                                                    // For every die number (1-6)
            if (die.get(i).equals(pq.max()))                                                // check if maximum value in pq is equal to the number of rolls in ST
                StdOut.print("[" + (die.select(i) + 1) + "] ");                             // return the index + 1 that represents the die number
        }                                                                                   // allows to print multiple in case of tie
        StdOut.println();

        // Individual Player stats
        StdOut.println();
        StdOut.println("    Player Statistics: ");
        StdOut.println("*************************************************************");
        for (int x = 0; x < numPlayers; x++) {
            StdOut.println("Player " + (x + 1));
            StdOut.println("Total number of turns: " + playerTurns[x]);
            StdOut.printf("Avg spaces moved: " + "%.2f", ((double) TotalDieRoll[x] / (double) playerTurns[x]));
            StdOut.println();
            StdOut.println("Total resources gained: " + (playerScore[x] + totalLost[x]));
            StdOut.println("Total resources lost: " + totalLost[x]);
            StdOut.println("Total resources collected: " + playerScore[x]);
            for (int k = 0; k < DICE; k++)
                StdOut.println("Times rolled [" + (k + 1) + "] : " + playerCommonRolls[x][k]);
            StdOut.println();
        }

        Map.movePlanets();
    }
}
