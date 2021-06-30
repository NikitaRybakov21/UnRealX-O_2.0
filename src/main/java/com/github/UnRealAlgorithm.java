package com.github;
import java.util.Random;

public class UnRealAlgorithm {

static Random random = new Random();

public static String Tac; //Метка хранит текущую тактику

static int save = 1;
static int n2,q,b,y,n,g = 0;

    public static void adapter(char[][] field){
        MainTicTac.motion++;
        int Sum = 0;
        for (int y = 0; y < MainTicTac.fieldSizeY; y++) {
            for (int x = 0; x < MainTicTac.fieldSizeX; x++) {
                if(field[y][x] == '.'){ MainTicTac.party[Sum] = 0;}
                if(field[y][x] == 'O'){ MainTicTac.party[Sum] = 1;}
                if(field[y][x] == 'X'){ MainTicTac.party[Sum] = 2;}
                Sum++;
            }
        }
        registerChanges();
    }

    public static void registerChanges(){
        boolean bt = false;

        if(MainTicTac.motion == 1) { //Блок определяет кто первый ходил
            for (int i = 0; i < 9; i++) {
                if (MainTicTac.party[i] != 0) {bt = true;}
            }

            if(bt) {processingСhanges(); } //Если человек делает 1 ход
            else   {randomAttackTactic();} //Если компьютер делает 1 ход
        }
        //////////////////////////////////////////////////////////////////////
        if(save != MainTicTac.motion){ //Блок определяет любые изменения
            controlTactic();
        }
        save = MainTicTac.motion;
    } //Регистрирует изменения (определяет кто ходит первым)
    private static void processingСhanges(){
        //Считывает массив и на основании 1 хода человека выбирает тактику обороны
        if(MainTicTac.party[4] == 2){ defense1();}

        if (MainTicTac.party[0] == 2){defense2(); g = 0;}
        if (MainTicTac.party[2] == 2){defense2(); g = 2;}
        if (MainTicTac.party[6] == 2){defense2(); g = 6;}
        if (MainTicTac.party[8] == 2){defense2(); g = 8;}

        if (MainTicTac.party[1] == 2){defense3(); g = 1;}
        if (MainTicTac.party[3] == 2){defense3(); g = 3;}
        if (MainTicTac.party[5] == 2){defense3(); g = 5;}
        if (MainTicTac.party[7] == 2){defense3(); g = 7;}
    } //В случае 1 хода человека определяет тактику обороны
    private static void controlTactic() {
        //Исходя из метки Tac вызывает методы тактик
        if(Tac == "A1"){ AttackTactic1();}
        if(Tac == "A2"){ AttackTactic2();}
        if(Tac == "D1"){ defense1();}
        if(Tac == "D2"){ defense2();}
        if(Tac == "D3"){ defense3();}
    }//Контролирует ходы тактик обороны и атак

    private static void defense3(){
        //////////////////////////////////////////////////////////////////Ход 1
        if(MainTicTac.motion == 1){

            MainTicTac.party[4] = 1;

            Tac = "D3";
            System.out.println("Компьютер Ход 1");
        }
        //////////////////////////////////////////////////////////////////Ход 2
        if(MainTicTac.motion == 2){

            if((g == 1) && (MainTicTac.party[7] == 2)){MainTicTac.party[2] = 1;}
            if((g == 5) && (MainTicTac.party[3] == 2)){MainTicTac.party[8] = 1;}
            if((g == 7) && (MainTicTac.party[1] == 2)){MainTicTac.party[6] = 1;}
            if((g == 3) && (MainTicTac.party[5] == 2)){MainTicTac.party[8] = 1;}

            if((MainTicTac.party[1] == 2) && (g == 3)) {MainTicTac.party[0] = 1;}
            if((MainTicTac.party[2] == 2) && (g == 3)) {MainTicTac.party[0] = 1;}
            if((MainTicTac.party[7] == 2) && (g == 3)) {MainTicTac.party[6] = 1;}
            if((MainTicTac.party[8] == 2) && (g == 3)) {MainTicTac.party[6] = 1;}

            if((MainTicTac.party[8] == 2) && (g == 1)) {MainTicTac.party[2] = 1;}
            if((MainTicTac.party[5] == 2) && (g == 1)) {MainTicTac.party[2] = 1;}
            if((MainTicTac.party[3] == 2) && (g == 1)) {MainTicTac.party[0] = 1;}
            if((MainTicTac.party[6] == 2) && (g == 1)) {MainTicTac.party[0] = 1;}

            if((MainTicTac.party[0] == 2) && (g == 5)) {MainTicTac.party[2] = 1;}
            if((MainTicTac.party[1] == 2) && (g == 5)) {MainTicTac.party[2] = 1;}
            if((MainTicTac.party[6] == 2) && (g == 5)) {MainTicTac.party[8] = 1;}
            if((MainTicTac.party[7] == 2) && (g == 5)) {MainTicTac.party[8] = 1;}

            if((MainTicTac.party[3] == 2) && (g == 7)) {MainTicTac.party[6] = 1;}
            if((MainTicTac.party[0] == 2) && (g == 7)) {MainTicTac.party[6] = 1;}
            if((MainTicTac.party[2] == 2) && (g == 7)) {MainTicTac.party[8] = 1;}
            if((MainTicTac.party[5] == 2) && (g == 7)) {MainTicTac.party[8] = 1;}

            stopWin();
            System.out.println("Компьютер Ход 2");
        }
        //////////////////////////////////////////////////////////////////Ход 3
        if(MainTicTac.motion == 3){
            b = 0;
            win();
            if(Tac == "D3") {stopWin();}
            if((Tac == "D3") && (b == 0)){
                int y = 0;
                if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                if((MainTicTac.party[4] == 0) && (y == 0)){MainTicTac.party[4] = 1; y = 1;}
                if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
            }
            System.out.println("Компьютер Ход 3");
        }
        ///////////////////////////////////////////////////////////////////Ход 4
        if(MainTicTac.motion == 4){
            b = 0;
            win();
            if(Tac == "D3"){stopWin();}
            if((Tac == "D3") && (b == 0)){
                int y = 0;
                if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                if((MainTicTac.party[4] == 0) && (y == 0)){MainTicTac.party[4] = 1; y = 1;}
                if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
            }
            System.out.println("Компьютер Ход 4");
        }
        //////////////////////////////////////////////////////////////////Ход 5
        if(MainTicTac.motion == 5){
            Tac = "No";
        }
        //////////////////////////////////////////////////////////////////Конец
    }//Тактика обороны 3
    private static void defense2(){
        //////////////////////////////////////////////////////////////////Ход 1
        if(MainTicTac.motion == 1){

            MainTicTac.party[4] = 1;

            System.out.println("Компьютер Ход 1");
            Tac = "D2";
        }
        /////////////////////////////////////////////////////////////////Ход 2
        if(MainTicTac.motion == 2){
           if((g == 0) && (MainTicTac.party[8] == 2)){MainTicTac.party[1] = 1;}
           if((g == 2) && (MainTicTac.party[6] == 2)){MainTicTac.party[3] = 1;}
           if((g == 6) && (MainTicTac.party[2] == 2)){MainTicTac.party[5] = 1;}
           if((g == 8) && (MainTicTac.party[0] == 2)){MainTicTac.party[7] = 1;}

           if((MainTicTac.party[3] == 2) && (g == 2)) {MainTicTac.party[0] = 1;}
           if((MainTicTac.party[7] == 2) && (g == 2)) {MainTicTac.party[8] = 1;}

           if((MainTicTac.party[1] == 2) && (g == 6)) {MainTicTac.party[0] = 1;}
           if((MainTicTac.party[5] == 2) && (g == 6)) {MainTicTac.party[8] = 1;}

           if((MainTicTac.party[5] == 2) && (g == 0)) {MainTicTac.party[2] = 1;}
           if((MainTicTac.party[7] == 2) && (g == 0)) {MainTicTac.party[6] = 1;}

           if((MainTicTac.party[3] == 2) && (g == 8)) {MainTicTac.party[6] = 1;}
           if((MainTicTac.party[1] == 2) && (g == 8)) {MainTicTac.party[2] = 1;}

            stopWin();
            System.out.println("Компьютер Ход 2");
        }
        //////////////////////////////////////////////////////////////////Ход 3
        if(MainTicTac.motion == 3){
            b = 0;
            win();
            if(Tac == "D2"){stopWin();}
            if((Tac == "D2") && (b == 0)){
                int y = 0;
                if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                if((MainTicTac.party[4] == 0) && (y == 0)){MainTicTac.party[4] = 1; y = 1;}
                if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
            }
            System.out.println("Компьютер Ход 3");
        }
        /////////////////////////////////////////////////////////////////////Ход 4
        if(MainTicTac.motion == 4){
            b = 0;
            win();
            if(Tac == "D2"){stopWin();}
            if((Tac == "D2") && (b == 0)){
                int y = 0;
                if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                if((MainTicTac.party[4] == 0) && (y == 0)){MainTicTac.party[4] = 1; y = 1;}
                if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
            }
            System.out.println("Компьютер Ход 4");
        }
        ////////////////////////////////////////////////////////////////////Ход 5
        if(MainTicTac.motion == 5){
            Tac = "No";
        }
        ///////////////////////////////////////////////////////////////////Конец
    }//Тактика обороны 2
    private static void defense1(){
        /////////////////////////////////////////////////////////////////Ход 1
        if(MainTicTac.motion == 1){

            MainTicTac.party[2] = 1;

            Tac = "D1";
            System.out.println("Компьютер Ход 1");
        }
        /////////////////////////////////////////////////////////////////Ход 2
        if(MainTicTac.motion == 2){

            stopWin();
            if(MainTicTac.party[6] == 2){MainTicTac.party[8] = 1;}

            System.out.println("Компьютер Ход 2");
        }
        /////////////////////////////////////////////////////////////////Ход 3
        if(MainTicTac.motion == 3){
            b = 0;
            win();
            if(Tac == "D1") {stopWin();}
            if((Tac == "D1") && (b == 0)){
                    int y = 0;
                    if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                    if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                    if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                    if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                    if((MainTicTac.party[4] == 0) && (y == 0)){MainTicTac.party[4] = 1; y = 1;}
                    if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                    if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                    if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                    if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
            }
            System.out.println("Компьютер Ход 3");
        }
        //////////////////////////////////////////////////////////////////Ход 4
        if(MainTicTac.motion == 4){
            b = 0;
            win();
            if(Tac == "D1"){stopWin();}
            if((Tac == "D1") && (b == 0)){
                int y = 0;
                if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                if((MainTicTac.party[4] == 0) && (y == 0)){MainTicTac.party[4] = 1; y = 1;}
                if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
            }
            System.out.println("Компьютер Ход 4");
        }
        //////////////////////////////////////////////////////////////////Ход 5
        if(MainTicTac.motion == 5){
            Tac = "No";
        }
        /////////////////////////////////////////////////////////////////Конец
    }//Тактика обороны 1

    private static void randomAttackTactic() {
        switch (random.nextInt(2) + 1) {
           case 1: AttackTactic1(); break;
           case 2: AttackTactic2(); break;
        }
    }//В случае 1 хода компьютера случайным образом определяет тактику атаки
    private static void AttackTactic2(){
        /////////////////////////////////////////////////////////////////Ход 1
        if(MainTicTac.motion == 1){

            MainTicTac.party[4] = 1;

            Tac = "A2";
            System.out.println("Компьютер Ход 1");
        }
        /////////////////////////////////////////////////////////////////Ход 2
        if(MainTicTac.motion == 2){
            if(MainTicTac.party[0] == 2){MainTicTac.party[8] = 1; n=1;q=8;}
            if(MainTicTac.party[2] == 2){MainTicTac.party[6] = 1; n=1;q=6;}
            if(MainTicTac.party[6] == 2){MainTicTac.party[2] = 1; n=1;q=2;}
            if(MainTicTac.party[8] == 2){MainTicTac.party[0] = 1; n=1;q=0;}

            if(MainTicTac.party[3] == 2){MainTicTac.party[2] = 1;}
            if(MainTicTac.party[1] == 2){MainTicTac.party[8] = 1;}
            if(MainTicTac.party[5] == 2){MainTicTac.party[6] = 1;}
            if(MainTicTac.party[7] == 2){MainTicTac.party[0] = 1;}

            System.out.println("Компьютер Ход 2");
        }
        //////////////////////////////////////////////////////////////////Ход 3
        if(MainTicTac.motion == 3){
            if((n == 0)){ win();stopWin();}
            if (n == 1) {
                b = 0;
                stopWin();
                if(b == 0){
                    if(q == 0){
                        if(MainTicTac.party[1] == 2){MainTicTac.party[6] = 1;}
                        if(MainTicTac.party[3] == 2){MainTicTac.party[2] = 1;}
                    }
                    if(q == 2){
                        if(MainTicTac.party[1] == 2){MainTicTac.party[8] = 1;}
                        if(MainTicTac.party[5] == 2){MainTicTac.party[0] = 1;}
                    }
                    if(q == 6){
                        if(MainTicTac.party[7] == 2){MainTicTac.party[0] = 1;}
                        if(MainTicTac.party[3] == 2){MainTicTac.party[8] = 1;}
                    }
                    if(q == 8){
                        if(MainTicTac.party[5] == 2){MainTicTac.party[6] = 1;}
                        if(MainTicTac.party[7] == 2){MainTicTac.party[2] = 1;}
                    }
                }
            }
            System.out.println("Компьютер Ход 3");
        }
        ////////////////////////////////////////////////////////////////////Ход 4
        if(MainTicTac.motion == 4){
            if(n == 0){ win();}
            if(n == 1){
                win();
                if(Tac == "A2") {
                    int y = 0;
                    if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                    if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                    if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                    if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                }
            }
            System.out.println("Компьютер Ход 4");
        }
        /////////////////////////////////////////////////////////////////////Ход 5
        if(MainTicTac.motion == 5){
            win();
            if(Tac == "A2") {
                int y = 0;
                if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
                Tac = "No";
            }
            System.out.println("Компьютер Ход 5");
        }
        /////////////////////////////////////////////////////////////////////Конец
    }//Тактика атаки 2
    private static void AttackTactic1() {
        ///////////////////////////////////////////////////////////////Ход 1
        int n = random.nextInt(4)+1;
        if (MainTicTac.motion == 1) {
            switch (n) {
                case 1: MainTicTac.party[0] = 1; n2 = 8; break;
                case 2: MainTicTac.party[2] = 1; n2 = 6; break;
                case 3: MainTicTac.party[6] = 1; n2 = 2; break;
                case 4: MainTicTac.party[8] = 1; n2 = 0; break;
            }
            Tac = "A1"; //Метка тактики
            System.out.println("Компьютер Ход 1");
        }
        //////////////////////////////////////////////////////////////Ход 2
        if (MainTicTac.motion == 2) {
            if (MainTicTac.party[n2] != 2){ MainTicTac.party[n2] = 1; }
            else{
                if(n2 == 0){MainTicTac.party[6] = 1; }
                if(n2 == 2){MainTicTac.party[8] = 1; }
                if(n2 == 6){MainTicTac.party[0] = 1; }
                if(n2 == 8){MainTicTac.party[6] = 1; }
            }
            System.out.println("Компьютер Ход 2");
        }
        ////////////////////////////////////////////////////////////////Ход 3
        if (MainTicTac.motion == 3) {
            b = 0;
            if((Tac == "A1")) {win();}
            if(Tac == "A1") stopWin();
            if((Tac == "A1")  && (b == 0)) {
                int y = 0;
            if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
            if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
            if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
            if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
            }
            System.out.println("Компьютер Ход 3");
        }
        //////////////////////////////////////////////////////////////////Ход 4
        if(MainTicTac.motion == 4) {
            b = 0;
            if((Tac == "A1") && (b == 0)) {win();}
            if(Tac == "A1") {stopWin();}
            System.out.println("Компьютер Ход 4");
        }
        //////////////////////////////////////////////////////////////////Ход 5
        if(MainTicTac.motion == 5) {
            if((Tac == "A1")) {win();}
            if(Tac == "A1")   {
                int y = 0;
                if((MainTicTac.party[0] == 0) && (y == 0)){MainTicTac.party[0] = 1; y = 1;}
                if((MainTicTac.party[1] == 0) && (y == 0)){MainTicTac.party[1] = 1; y = 1;}
                if((MainTicTac.party[2] == 0) && (y == 0)){MainTicTac.party[2] = 1; y = 1;}
                if((MainTicTac.party[3] == 0) && (y == 0)){MainTicTac.party[3] = 1; y = 1;}
                if((MainTicTac.party[4] == 0) && (y == 0)){MainTicTac.party[4] = 1; y = 1;}
                if((MainTicTac.party[5] == 0) && (y == 0)){MainTicTac.party[5] = 1; y = 1;}
                if((MainTicTac.party[6] == 0) && (y == 0)){MainTicTac.party[6] = 1; y = 1;}
                if((MainTicTac.party[7] == 0) && (y == 0)){MainTicTac.party[7] = 1; y = 1;}
                if((MainTicTac.party[8] == 0) && (y == 0)){MainTicTac.party[8] = 1; y = 1;}
                Tac = "No";
            }
            System.out.println("Компьютер Ход 5");
        }
        ////////////////////////////////////////////////////////////////Конец
    }//Тактика атаки 1

    private static void win(){

        int X = 0 ,n = -1;y = 0;

        if(y == 0){ //Проверяет линию 0 3 6
        if(MainTicTac.party[0] == 1){ X++; }
        if(MainTicTac.party[3] == 1){ X++; }
        if(MainTicTac.party[6] == 1){ X++; }
        if(MainTicTac.party[0] == 0){ n = 0;}
        if(MainTicTac.party[3] == 0){ n = 3;}
        if(MainTicTac.party[6] == 0){ n = 6;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1; Tac = "Win"; y = 1;}
        X = 0; n = -1;}

        if(y == 0){
        if(MainTicTac.party[1] == 1){ X++; }
        if(MainTicTac.party[4] == 1){ X++; }
        if(MainTicTac.party[7] == 1){ X++; }
        if(MainTicTac.party[1] == 0){ n = 1;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[7] == 0){ n = 7;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1; Tac = "Win"; y = 1;}
        X = 0; n = -1;}

        if(y == 0){
        if(MainTicTac.party[2] == 1){ X++; }
        if(MainTicTac.party[5] == 1){ X++; }
        if(MainTicTac.party[8] == 1){ X++; }
        if(MainTicTac.party[2] == 0){ n = 2;}
        if(MainTicTac.party[5] == 0){ n = 5;}
        if(MainTicTac.party[8] == 0){ n = 8;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1; Tac = "Win"; y = 1;}
        X = 0; n = -1;}

        if(y == 0){
        if(MainTicTac.party[0] == 1){ X++; }
        if(MainTicTac.party[1] == 1){ X++; }
        if(MainTicTac.party[2] == 1){ X++; }
        if(MainTicTac.party[0] == 0){ n = 0;}
        if(MainTicTac.party[1] == 0){ n = 1;}
        if(MainTicTac.party[2] == 0){ n = 2;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;Tac = "Win";y = 1; }
        X = 0; n = -1;}

        if(y == 0){
        if(MainTicTac.party[3] == 1){ X++; }
        if(MainTicTac.party[4] == 1){ X++; }
        if(MainTicTac.party[5] == 1){ X++; }
        if(MainTicTac.party[3] == 0){ n = 3;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[5] == 0){ n = 5;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1; Tac = "Win";y = 1;}
        X = 0; n = -1;}

        if(y == 0){
        if(MainTicTac.party[6] == 1){ X++; }
        if(MainTicTac.party[7] == 1){ X++; }
        if(MainTicTac.party[8] == 1){ X++; }
        if(MainTicTac.party[6] == 0){ n = 6;}
        if(MainTicTac.party[7] == 0){ n = 7;}
        if(MainTicTac.party[8] == 0){ n = 8;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1; Tac = "Win";y = 1; }
        X = 0; n = -1;}

        if(y == 0){
        if(MainTicTac.party[6] == 1){ X++; }
        if(MainTicTac.party[4] == 1){ X++; }
        if(MainTicTac.party[2] == 1){ X++; }
        if(MainTicTac.party[6] == 0){ n = 6;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[2] == 0){ n = 2;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1; Tac = "Win";y = 1;}
        X = 0; n = -1;}

        if(y == 0){
        if(MainTicTac.party[0] == 1){ X++; }
        if(MainTicTac.party[4] == 1){ X++; }
        if(MainTicTac.party[8] == 1){ X++; }
        if(MainTicTac.party[0] == 0){ n = 0;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[8] == 0){ n = 8;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1; Tac = "Win";y = 1;}
        X = 0; n = -1;}


    }//Делает победный ход (3 в ряд) если есть (2 в ряд)
    public static void stopWin(){
        int X = 0 ,n = -1;
        if(MainTicTac.party[0] == 2){ X++; } //Проверяет линию 0 3 6
        if(MainTicTac.party[3] == 2){ X++; }
        if(MainTicTac.party[6] == 2){ X++; }
        if(MainTicTac.party[0] == 0){ n = 0;}
        if(MainTicTac.party[3] == 0){ n = 3;}
        if(MainTicTac.party[6] == 0){ n = 6;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;

        if(MainTicTac.party[1] == 2){ X++; }
        if(MainTicTac.party[4] == 2){ X++; }
        if(MainTicTac.party[7] == 2){ X++; }
        if(MainTicTac.party[1] == 0){ n = 1;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[7] == 0){ n = 7;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;

        if(MainTicTac.party[2] == 2){ X++; }
        if(MainTicTac.party[5] == 2){ X++; }
        if(MainTicTac.party[8] == 2){ X++; }
        if(MainTicTac.party[2] == 0){ n = 2;}
        if(MainTicTac.party[5] == 0){ n = 5;}
        if(MainTicTac.party[8] == 0){ n = 8;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;

        if(MainTicTac.party[0] == 2){ X++; }
        if(MainTicTac.party[1] == 2){ X++; }
        if(MainTicTac.party[2] == 2){ X++; }
        if(MainTicTac.party[0] == 0){ n = 0;}
        if(MainTicTac.party[1] == 0){ n = 1;}
        if(MainTicTac.party[2] == 0){ n = 2;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;

        if(MainTicTac.party[3] == 2){ X++; }
        if(MainTicTac.party[4] == 2){ X++; }
        if(MainTicTac.party[5] == 2){ X++; }
        if(MainTicTac.party[3] == 0){ n = 3;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[5] == 0){ n = 5;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;

        if(MainTicTac.party[6] == 2){ X++; }
        if(MainTicTac.party[7] == 2){ X++; }
        if(MainTicTac.party[8] == 2){ X++; }
        if(MainTicTac.party[6] == 0){ n = 6;}
        if(MainTicTac.party[7] == 0){ n = 7;}
        if(MainTicTac.party[8] == 0){ n = 8;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;

        if(MainTicTac.party[6] == 2){ X++; }
        if(MainTicTac.party[4] == 2){ X++; }
        if(MainTicTac.party[2] == 2){ X++; }
        if(MainTicTac.party[6] == 0){ n = 6;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[2] == 0){ n = 2;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;

        if(MainTicTac.party[0] == 2){ X++; }
        if(MainTicTac.party[4] == 2){ X++; }
        if(MainTicTac.party[8] == 2){ X++; }
        if(MainTicTac.party[0] == 0){ n = 0;}
        if(MainTicTac.party[4] == 0){ n = 4;}
        if(MainTicTac.party[8] == 0){ n = 8;}
        if((n>-1) && ( X == 2)){MainTicTac.party[n] = 1;b = 1;}
        X = 0; n = -1;


    }//Блокирует клетку противнику если есть (2 в ряд)
}
