//
//Created by Glazyrin Maksim
//
package geekbrains_lesson4;

import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checktowin(DOT_X, "человек")) break;
            aiTurn();
            printMap();
            if (checktowin(DOT_O, "компьютер")) break;
        }
    }

    public static boolean checktowin(char dot, String winner){
        if (checkWin0(dot)) {
            System.out.println("Победил " + winner);
           return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
        System.out.println("Игра завершина");
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    public static void aiTurn() {
        Random random = new Random();
        int x;
        int y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static boolean checkWin0 (char symb) {
        int result = 0, result2 = 0, result3 = 0, result4 = 0;
        for (int i = 0; i < SIZE; i++) {
            result = 0;
            result2 = 0;
            if (map[i][i] == symb)
                result3 += 1;
            if (map[(SIZE - 1) - i][i] == symb)
                result4 += 1;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb)
                    result += 1;
                if (map[j][i] == symb)
                    result2 += 1;
            }
            if (result == DOTS_TO_WIN || result2 == DOTS_TO_WIN || result3 == DOTS_TO_WIN || result4 == DOTS_TO_WIN)
                return true;
        }
        return false;

    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

