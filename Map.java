import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Map {

    private static double scale = 0.04;

    public static void drawMap() {

        StdDraw.setCanvasSize(1000, 1000);                      // Set canvas size
        StdDraw.setPenColor(0, 25, 51);                         // Dark Color
        StdDraw.filledSquare(0.50, 0.50, 0.50);
        StdDraw.picture(0.50, 0.50, "board.png", 1, 0.7);       // Inputs the backdrop for the game "board.png"

        // Draw Hexagon pieces
        StdDraw.setPenColor(0, 25, 51);                         // Dark Color
        double[] A = {0.10, 0.20, 0.25, 0.20, 0.10, 0.05};
        double[] B = {0.50, 0.50, 0.60, 0.70, 0.70, 0.60};
        StdDraw.filledPolygon(A, B);                            // Hexagon 1

        double[] G = {0.25, 0.35, 0.40, 0.35, 0.25, 0.20};
        double[] H = {0.20, 0.20, 0.30, 0.40, 0.40, 0.30};
        StdDraw.filledPolygon(G, H);                            // Hexagon 5

        double[] K = {0.40, 0.50, 0.55, 0.50, 0.40, 0.35};
        double[] L = {0.50, 0.50, 0.60, 0.70, 0.70, 0.60};
        StdDraw.filledPolygon(K, L);                            // Hexagon 6

        double[] Q = {0.55, 0.65, 0.70, 0.65, 0.55, 0.50};
        double[] R = {0.20, 0.20, 0.30, 0.40, 0.40, 0.30};
        StdDraw.filledPolygon(Q, R);                            // Hexagon 9

        double[] S = {0.70, 0.80, 0.85, 0.80, 0.70, 0.65};
        double[] T = {0.50, 0.50, 0.60, 0.70, 0.70, 0.60};
        StdDraw.filledPolygon(S, T);                            // Hexagon 10

        StdDraw.setPenColor(0, 42, 90);                         // Medium Color
        double[] C = {0.10, 0.20, 0.25, 0.20, 0.10, 0.05};
        double[] D = {0.30, 0.30, 0.40, 0.50, 0.50, 0.40};
        StdDraw.filledPolygon(C, D);                            // Hexagon 2

        double[] I = {0.25, 0.35, 0.40, 0.35, 0.25, 0.20};
        double[] J = {0.60, 0.60, 0.70, 0.80, 0.80, 0.70};
        StdDraw.filledPolygon(I, J);                            // Hexagon 3

        double[] U = {0.70, 0.80, 0.85, 0.80, 0.70, 0.65};
        double[] V = {0.30, 0.30, 0.40, 0.50, 0.50, 0.40};
        StdDraw.filledPolygon(U, V);                            // Hexagon 7

        double[] M = {0.40, 0.50, 0.55, 0.50, 0.40, 0.35};
        double[] N = {0.30, 0.30, 0.40, 0.50, 0.50, 0.40};
        StdDraw.filledPolygon(M, N);                            // Hexagon 10

        StdDraw.setPenColor(6, 60, 122);                        // Light Color
        double[] E = {0.25, 0.35, 0.40, 0.35, 0.25, 0.20};
        double[] F = {0.40, 0.40, 0.50, 0.60, 0.60, 0.50};
        StdDraw.filledPolygon(E, F);                            // Hexagon 4

        double[] O = {0.55, 0.65, 0.70, 0.65, 0.55, 0.50};
        double[] P = {0.40, 0.40, 0.50, 0.60, 0.60, 0.50};
        StdDraw.filledPolygon(O, P);                            // Hexagon 8


        StdDraw.picture(0.10, 0.70, "planet1.png", scale, scale);       // planet 0
        StdDraw.picture(0.20, 0.70, "planet2.png", scale, scale);       // planet 1
        StdDraw.picture(0.25, 0.80, "planet3.png", scale, scale);       // planet 2
        StdDraw.picture(0.35, 0.80, "BlackHole.png", scale, scale);     // Black Hole 3
        StdDraw.picture(0.40, 0.70, "planet4.png", scale, scale);       // planet 4
        StdDraw.picture(0.50, 0.70, "planet5.png", scale, scale);       // planet 5
        StdDraw.picture(0.70, 0.70, "planet6.png", scale, scale);       // planet 6
        StdDraw.picture(0.80, 0.70, "BinaryStar.png", 0.07, 0.07);      // Binary Star 7
        StdDraw.picture(0.85, 0.60, "planet7.png", scale, scale);       // planet 8
        StdDraw.picture(0.80, 0.50, "planet8.png", scale, scale);       // planet 9
        StdDraw.picture(0.70, 0.50, "planet9.png", scale, scale);       // planet 10
        StdDraw.picture(0.65, 0.60, "planet10.png", scale, scale);      // planet 11
        StdDraw.picture(0.55, 0.60, "Asteroid1.png", scale, scale);     // Asteroid 12
        StdDraw.picture(0.50, 0.50, "planet1.png", scale, scale);       // planet 13
        StdDraw.picture(0.40, 0.50, "BasePlanet14.png", scale, scale);  // base planet 14
        StdDraw.picture(0.35, 0.60, "planet2.png", scale, scale);       // planet 15
        StdDraw.picture(0.25, 0.60, "DeathStar.png", scale, scale);     // abandoned space station 16
        StdDraw.picture(0.20, 0.50, "planet3.png", scale, scale);       // planet 17
        StdDraw.picture(0.10, 0.50, "Asteroid2.png", scale, scale);     // planet 18
        StdDraw.picture(0.05, 0.60, "planet4.png", scale, scale);       // planet 19
        StdDraw.picture(0.05, 0.40, "planet5.png", scale, scale);       // planet 20
        StdDraw.picture(0.10, 0.30, "planet6.png", scale, scale);       // planet 21
        StdDraw.picture(0.20, 0.30, "Wormhole.png", scale, scale);      // wormhole 22
        StdDraw.picture(0.25, 0.40, "planet7.png", scale, scale);       // planet 23
        StdDraw.picture(0.35, 0.40, "Alien.png", 0.03, 0.03);         // Alien Spaceship 24
        StdDraw.picture(0.40, 0.30, "planet8.png", scale, scale);       // planet 25
        StdDraw.picture(0.50, 0.30, "SpaceStation.png", 0.03, 0.03);    // space station 26
        StdDraw.picture(0.55, 0.40, "planet9.png", scale, scale);       // planet 27
        StdDraw.picture(0.65, 0.40, "SpaceStation.png", 0.03, 0.03);    // space station 28
        StdDraw.picture(0.70, 0.30, "planet10.png", scale, scale);      // planet 29
        StdDraw.picture(0.80, 0.30, "SpaceStation.png", 0.03, 0.03);    // space station 30
        StdDraw.picture(0.85, 0.40, "planet1.png", scale, scale);       // planet 31
        StdDraw.picture(0.65, 0.20, "planet2.png", scale, scale);       // planet 32
        StdDraw.picture(0.55, 0.20, "planet3.png", scale, scale);       // planet 33
        StdDraw.picture(0.35, 0.20, "planet4.png", scale, scale);       // planet 34
        StdDraw.picture(0.25, 0.20, "planet5.png", scale, scale);       // planet 35

        StdDraw.picture(0.125, 0.10, "ship1.png", scale, scale);        // Ship 1
        StdDraw.picture(0.375, 0.10, "ship2.png", scale, scale);        // Ship 2
        StdDraw.picture(0.625, 0.10, "ship3.png", scale, scale);        // Ship 3
        StdDraw.picture(0.875, 0.10, "ship4.png", scale, scale);        // Ship 4

        StdDraw.setPenColor(255, 131, 6);
        Font font = new Font("Arial", Font.BOLD, 12);
        StdDraw.setFont(font);
        StdDraw.text(0.125, 0.05, "Player 1");
        StdDraw.text(0.375, 0.05, "Player 2");
        StdDraw.text(0.625, 0.05, "Player 3");
        StdDraw.text(0.875, 0.05, "Player 4");

    }

    public static void movePlanets() {

        int degrees = 0;
        while (true) {
//            StdDraw.picture(0.20, 0.70, "planet2.png", scale, scale, degrees);       // planet 1
//            StdDraw.picture(0.35, 0.80, "BlackHole.png", scale, scale, degrees);     // Black Hole 3
//            StdDraw.picture(0.40, 0.70, "planet4.png", scale, scale, degrees);       // planet 4
//            StdDraw.picture(0.50, 0.70, "planet5.png", scale, scale, degrees);       // planet 5
//            StdDraw.picture(0.70, 0.70, "planet6.png", scale, scale, degrees);       // planet 6
//            StdDraw.picture(0.85, 0.60, "planet7.png", scale, scale, degrees);       // planet 8
//            StdDraw.picture(0.80, 0.50, "planet8.png", scale, scale, degrees);       // planet 9
//            StdDraw.picture(0.70, 0.50, "planet9.png", scale, scale, degrees);       // planet 10
//            StdDraw.picture(0.65, 0.60, "planet10.png", scale, scale, degrees);      // planet 11
            StdDraw.picture(0.40, 0.50, "BasePlanet14.png", scale, scale, degrees);  // base planet 14
//            StdDraw.picture(0.35, 0.60, "planet2.png", scale, scale, degrees);       // planet 15
//            StdDraw.picture(0.05, 0.60, "planet4.png", scale, scale, degrees);       // planet 19
//            StdDraw.picture(0.05, 0.40, "planet5.png", scale, scale, degrees);       // planet 20
//            StdDraw.picture(0.10, 0.30, "planet6.png", scale, scale, degrees);       // planet 21
//            StdDraw.picture(0.25, 0.40, "planet7.png", scale, scale, degrees);       // planet 23
//            StdDraw.picture(0.40, 0.30, "planet8.png", scale, scale, degrees);       // planet 25
//            StdDraw.picture(0.55, 0.40, "planet9.png", scale, scale, degrees);       // planet 27
//            StdDraw.picture(0.70, 0.30, "planet10.png", scale, scale, degrees);      // planet 29
//            StdDraw.picture(0.65, 0.20, "planet2.png", scale, scale, degrees);       // planet 32
//            StdDraw.picture(0.35, 0.20, "planet4.png", scale, scale, degrees);       // planet 34
//            StdDraw.picture(0.25, 0.20, "planet5.png", scale, scale, degrees);       // planet 35

            degrees = (degrees + 1) % 360;
            StdDraw.enableDoubleBuffering();
            StdDraw.pause(1);
            StdDraw.disableDoubleBuffering();
        }
    }
}
