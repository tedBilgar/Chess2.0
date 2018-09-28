package com.company.figure;

import com.company.board.ChessBoard;
import com.company.types.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Elephant extends ChessFigure {

    public Elephant(Side side, ChessBoard chessBoard, int x_coord, int y_coord) {
        super(side, chessBoard, x_coord, y_coord);
        vector  = new int[2];
    }

    @Override
    public boolean setRandomVector(List<Integer> usedVectors) {
        boolean isNext = false;
        int coeff;
        if (this.side == Side.WHITE) coeff = 1;
        else coeff = -1;

        Random random = new Random();
        int randomNum;

        do {
            if (usedVectors.size() == 4) return false;
            randomNum = random.nextInt(4) + 1;

            boolean itWas = false;
            for (int num: usedVectors) {
                if(randomNum == num) {
                    itWas = true;
                    break;
                }
            }

            if(randomNum == 1){
                vector[0] = 0;
                vector[1] = +1;
                usedVectors.add(1);
            }
            if (randomNum == 2){
                vector[0] = +1;
                vector[1] = 0;
                usedVectors.add(2);
            }
            if (randomNum == 3){
                vector[0] = 0;
                vector[1] = -1;
                usedVectors.add(3);
            }
            if(randomNum == 4){
                vector[0] = -1;
                vector[1] = 0;
                usedVectors.add(4);
            }

            if (x_coord + coeff * vector[0] < 1 || x_coord + coeff * vector[0]> 8 || y_coord + coeff * vector[1] <1 || y_coord + coeff* vector[1] > 8) {
                if (itWas) usedVectors.remove(usedVectors.size()-1);
                isNext = false;
            }else if (itWas) isNext = false;
            else isNext = true;

        }while(!isNext);

        return true;
    }

    @Override
    public int getVectorType(int x,int y){
        int type = 0;
        if (x == 0 && y > 0) type = 1;

        if (x > 0 && y == 0 ) type = 2;

        if (x == 0 && y < 0) type = 3;

        if (x < 0 && y==0) type = 4;

        return type;
    }
}
