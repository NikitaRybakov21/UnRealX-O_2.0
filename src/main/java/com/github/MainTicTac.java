package com.github;

import java.util.Random;
import java.util.Scanner;

public class MainTicTac {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static char[][] field;
    public static int fieldSizeX;
    public static int fieldSizeY;
    private static int scoreHuman;
    private static int scoreAI;
    private static int LENGTH_WIN = 5;

    public static int motion = 0;
    public static int[] party = new int[9];

    public static void main(String[] args) {

        while (true) {
            playRound();
            System.out.printf("SCORE IS: HUMAN   AI\n            %d      %d\n", scoreHuman, scoreAI);
            System.out.print("Wanna play again? Enter Y or N >>> ");
            if (!SCANNER.next().toLowerCase().equals("y")) break;
        }
    }

    private static void playRound() {
        initField(3, 3);
        LENGTH_WIN = 3;//длина последовательности
        printField();

        int rand = RANDOM.nextInt(2);//Розыгрыш 1 хода
        while (true) {
            if(rand == 0){
                aiTurn();
                printField();
                if (checkGameState(DOT_AI)) break;
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN)) break;}

            if(rand == 1){
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN)) break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI)) break;}
        }
    }
    private static boolean checkGameState(char dot) {
        if (checkDraw()) return true;

        if (checkWin(dot)) {
            if (dot == DOT_HUMAN) {
                System.out.println("Human win!");
                scoreHuman++;
            } else {
                System.out.println("AI win!");
                scoreAI++;
            }
            return true;
        }
        return false;
    }
    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(y, x)) return false;
            }
        }
        System.out.println("Ooops... It's DRAW!");
        return true;
    }
    /*
     * Переделать проверку победы с циклами
     * * Сделать проверку победы на поле 5х5 и длину последовательности 4
     * ** Сделать проверку на произвольные размеры поля и длину последовательности
     */
    private static boolean checkWin(char dot) {
        //Работает на произвольные поля X на Y и длины n последовательности

        //По горизонтали
        int SumX = 0;
        int SumO = 0;
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if(field[y][x] == 'X'){SumX++;}
                else{SumX = 0;}
                if(SumX == LENGTH_WIN) {return true;}

                if(field[y][x] == 'O'){SumO++;}
                else{SumO = 0;}
                if(SumO == LENGTH_WIN) {return true;}
            }
            SumX = 0;
            SumO = 0;
        }
        //По вертикали
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if(field[y][x] == 'X'){SumX++;}
                else{SumX = 0;}
                if(SumX == LENGTH_WIN) {return true;}

                if(field[y][x] == 'O'){SumO++;}
                else{SumO = 0;}
                if(SumO == LENGTH_WIN) {return true;}
            }
            SumX = 0;
            SumO = 0;
        }
        //По диагоналям
        //ОСТОРОЖНО этот код может сломать мозг!!!!!
        SumX = 0;
        SumO = 0;
        int L = 0;
        int x = 0;
        int y = fieldSizeY-1;
        //Слева направо
        for(int i = -(fieldSizeY-1); i<fieldSizeX; i++){
            if(i>0){x++;}

            //System.out.println(i+" "+y+" "+x+" "+L);
               for(int j = 0;j<=L; j++){
                   if(field[y+j][x+j] == 'X'){SumX++;}
                   else{SumX = 0;}
                   if(SumX == LENGTH_WIN) {return true;}

                   if(field[y+j][x+j] == 'O'){SumO++;}
                   else{SumO = 0;}
                   if(SumO == LENGTH_WIN) {return true;}
               }
            if(i<0){y--;}
            if(i<0){L++;}
            if(i>=(fieldSizeX-fieldSizeY)){L--;}

            SumX = 0;
            SumO = 0;
        }
        //Справа налево
         L = 0;
         x = fieldSizeX-1;
         y = fieldSizeY-1;
        for(int i = -(fieldSizeY-1); i<fieldSizeX; i++){
            if(i>0){x--;}

            //System.out.println(i+" "+y+" "+x+" "+L);
            for(int j = 0;j<=L; j++){
                if(field[y+j][x-j] == 'X'){SumX++;}
                else{SumX = 0;}
                if(SumX == LENGTH_WIN) {return true;}

                if(field[y+j][x-j] == 'O'){SumO++;}
                else{SumO = 0;}
                if(SumO == LENGTH_WIN) {return true;}
            }
            if(i<0){y--;}
            if(i<0){L++;}
            if(i>=(fieldSizeX-fieldSizeY)){L--;}

            SumX = 0;
            SumO = 0;
        }
        return false;
    }

    /*
     * *** Сделать более умного ИИ (чтобы ходил хоть как то осмысленно)
     */
    private static void aiTurn() {
        //Непобедимый алгоритм работает только 3 на 3
        if((fieldSizeX == 3) && (fieldSizeY == 3)) {
            UnRealAlgorithm.adapter(field);
            //Адаптер
            int Sum = 0;
            for (int y = 0; y < MainTicTac.fieldSizeY; y++) {
                for (int x = 0; x < MainTicTac.fieldSizeX; x++) {
                    if (party[Sum] == 1) { field[y][x] = 'O'; }
                    if (party[Sum] == 2) { field[y][x] = 'X'; }
                    Sum++;
                }
            }
        }
        //Работает на произвольные поля X на Y и длины n последовательности
        else{
            algorithm2();
        }
    }
    private static void algorithm2(){
        int key = 0;
        //По горизонтали
        int SumX = 0;
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if(field[y][x] == 'X'){SumX++;}
                else{SumX = 0;}
                if((SumX == LENGTH_WIN - 2)&&(key == 0)) {
                   if(field[y][x+1] == '.'){
                       field[y][x+1] = 'O'; key = 1;
                   }
                   if(isCellValid(y,x-LENGTH_WIN+2)){
                   if((field[y][x-LENGTH_WIN+2] == '.')&&(key == 0)){
                        field[y][x-LENGTH_WIN+2] = 'O'; key = 1;
                   }}
                }
            }
            SumX = 0;
        }
        //По вертикали
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if(field[y][x] == 'X'){SumX++;}
                else{SumX = 0;}
                if((SumX == LENGTH_WIN - 2)&&(key == 0)) {
                    if(field[y+1][x] == '.'){
                        field[y+1][x] = 'O'; key = 1;
                    }
                    if(isCellValid(y-LENGTH_WIN+2,x)){
                    if((field[y-LENGTH_WIN+2][x] == '.')&&(key == 0)){
                        field[y-LENGTH_WIN+2][x] = 'O'; key = 1;
                    }}
                }
            }
            SumX = 0;
        }
        //По диагоналям
        SumX = 0;
        int L = 0;
        int x = 0;
        int y = fieldSizeY-1;
        //Слева направо
        for(int i = -(fieldSizeY-1); i<fieldSizeX; i++){
            if(i>0){x++;}
            //System.out.println(i+" "+y+" "+x+" "+L);
            for(int j = 0;j<=L; j++){
                if(field[y+j][x+j] == 'X'){SumX++;}
                else{SumX = 0;}
                if((SumX == LENGTH_WIN - 2)&&(key == 0)) {
                    if(field[y+j+1][x+j+1] == '.'){
                        field[y+j+1][x+j+1] = 'O'; key = 1;
                    }
                    if(isCellValid(j+y-LENGTH_WIN+2,x+j-LENGTH_WIN+2)){
                    if((field[j+y-LENGTH_WIN+2][x+j-LENGTH_WIN+2] == '.')&&(key == 0)){
                        field[j+y-LENGTH_WIN+2][x+j-LENGTH_WIN+2] = 'O'; key = 1;
                    }}
                }
            }
            if(i<0){y--;}
            if(i<0){L++;}
            if(i>=(fieldSizeX-fieldSizeY)){L--;}
            SumX = 0;
        }
        //Справа налево
        L = 0;
        x = fieldSizeX-1;
        y = fieldSizeY-1;
        for(int i = -(fieldSizeY-1); i<fieldSizeX; i++){
            if(i>0){x--;}
            //System.out.println(i+" "+y+" "+x+" "+L);
            for(int j = 0;j<=L; j++){
                if(field[y+j][x-j] == 'X'){SumX++;}
                else{SumX = 0;}
                if(SumX == LENGTH_WIN - 2) {
                    if(field[y+j+1][x-j-1] == '.'){
                        field[y+j+1][x-j-1] = 'O'; key = 1;
                    }
                    if(isCellValid(j+y-LENGTH_WIN+2,x-j+LENGTH_WIN-2)){
                    if((field[j+y-LENGTH_WIN+2][x-j+LENGTH_WIN-2] == '.')&&(key == 0)){
                        field[j+y-LENGTH_WIN+2][x-j+LENGTH_WIN-2] = 'O'; key = 1;
                    }}
                }
            }
            if(i<0){y--;}
            if(i<0){L++;}
            if(i>=(fieldSizeX-fieldSizeY)){L--;}
            SumX = 0;
        }
        int key2 = 0;
        if(key == 0){
            for (int i = 0; i < fieldSizeY; i++) {
                for (int j = 0; j < fieldSizeX; j++) {
                    if (field[i][j] == 'X'){
                        if((field[i][j+1] == '.')&&(key2 == 0)){field[i][j+1] = 'O';key2 = 1;}
                        if((field[i+1][j] == '.')&&(key2 == 0)){field[i+1][j] = 'O';key2 = 1;}
                        if((field[i+1][j+1] == '.')&&(key2 == 0)){field[j+1][j+1] = 'O';key2 = 1;}

                        if(isCellValid(i-1,j-1)){
                        if((field[i][j-1] == '.')&&(key2 == 0)){field[i][j-1] = 'O';key2 = 1;}
                        if((field[i-1][j+1] == '.')&&(key2 == 0)){field[i-1][j+1] = 'O';key2 = 1;}
                        if((field[i-1][j-1] == '.')&&(key2 == 0)){field[i-1][j-1] = 'O';key2 = 1;}
                        if((field[i-1][j] == '.')&&(key2 == 0)){field[i-1][j] = 'O';key2 = 1;}
                        if((field[i+1][j-1] == '.')&&(key2 == 0)){field[i+1][j-1] = 'O';key2 = 1;}}
                    }
                }
            }
        }
        if(key2 == 0){
            field[RANDOM.nextInt(fieldSizeY)][RANDOM.nextInt(fieldSizeX)] = 'O';
        }
    }

    private static void humanTurn() {
        int x, y;

        do {
            System.out.print("Please enter coordinates of your turn x & y split with whitespace >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(y, x) || !isCellEmpty(y, x));

        field[y][x] = DOT_HUMAN;
    }

    private static boolean isCellValid(int y, int x) {
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;
    }
    private static boolean isCellEmpty(int y, int x) {
        return field[y][x] == DOT_EMPTY;
    }
    private static void initField(int sizeY, int sizeX) {
        fieldSizeY = sizeY;
        fieldSizeX = sizeX;
        field = new char[fieldSizeY+1][fieldSizeX+1];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i <= fieldSizeX * 2 + 1; i++) {
            System.out.print("_");
        }
        System.out.println();
//        for (int y = 0; y < fieldSizeY; y++) {
//            for (int x = 0; x < fieldSizeX; x++) {
//                System.out.print(field[y][x] + " ");
//            }
//            System.out.println();
//        }
    }
}
