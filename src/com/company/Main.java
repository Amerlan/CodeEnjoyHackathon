package com.company;

import java.util.Random;
public class Main {
    public static int stage = 1;
    public static int [][] matrix = new int[15][5];
    public static int current_pos = 2;


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int[][] gen() {
        final Random random = new Random();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = 0;
            }
        }
        int state[] = {0, 2, 3};
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 14 && j == 2) {
                    matrix[14][2] = 1;
                } else {
                    if (i <= 9 && i % 2 == 0) {
                        matrix[i][j] = state[getRandomNumberInRange(0, 2)];
                    } else if (i <= 9) {
                        matrix[i][j] = state[getRandomNumberInRange(0, 1)];
                    }

                }
            }

        }
        return matrix;
    }
    public int[][] nextStep(int stage){

        int state[] = {0, 2, 3,0,2,0,2,0};
        if(stage % 2 == 0){
            for(int j = 0; j < 5; j++){
                matrix [0][j] = state[getRandomNumberInRange(0, 7)];
            }
        }
        stage++;
        return matrix;
    }

    public int[][] command(String com, int cur_pos){
        if(cur_pos >= 4 || cur_pos <= 0){
            ;
        }
        else{
            matrix[14][cur_pos] = 0;
            if(com == "left"){
                cur_pos -= 1;
            }else if(com == "right"){
                cur_pos += 1;
            }
            if(matrix[13][cur_pos] == 0){
                matrix[13][cur_pos] = 1;
            }else {
                matrix[13][cur_pos] = -1;
            }
        }
        return matrix;
    }
    public int[][] refresh_matrix(String com, int stage, int cur_pos){
        command(com, cur_pos);
        for(int i = 13; i > 0; i--){
            for(int j = 0; j < 5; j++){
                matrix[i][j] = matrix[i-1][j];
            }

        }
        nextStep(stage);
        return matrix;
    }
}
