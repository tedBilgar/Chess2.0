package com.company.figure;

import com.company.board.ChessBoard;
import com.company.types.ChessType;
import com.company.types.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ChessFigure {
    protected Side side;
    protected ChessType chessType;
    protected ChessBoard chessBoard;
    protected int x_coord;
    protected int y_coord;

    protected int[] vector;
    protected int step;

    public ChessFigure(Side side, ChessBoard chessBoard,int x_coord,int y_coord) {
        this.side = side;
        this.chessBoard = chessBoard;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }

    //функция движения фигуры
    //алгоритм одинаков для всех фигур
    public boolean move(){
        int coeff;
        boolean isDone  = false;
        List<Integer> usedVectors = new ArrayList<>();
        if (side == Side.WHITE) coeff = +1;
        else coeff = -1;

        //Получаем рандомный вектор для движения
        if(!setRandomVector(usedVectors)) return false;

        //Получаем валидный шаг для движения
        setStep();

        int potentialXCoord = x_coord + coeff * vector[0] * step;
        int potentialYCoord = y_coord + coeff * vector[1] * step;
        ChessFigure potentialKilled = null;

        for (ChessFigure figure: chessBoard.getChessFigureList()) {
            int differenceX = figure.getX_coord() - x_coord;
            int differenceY = figure.getY_coord() - y_coord;

            //данная фигура на пути у текущей фигуры или нет
            if (getVectorType(differenceX,differenceY) == usedVectors.get(usedVectors.size() - 1)){
                if (vector[0] * potentialXCoord > vector[0] * figure.getX_coord()
                        || vector[1] * potentialYCoord > vector[1] * figure.getY_coord()){
                    if (figure.side != this.side){
                        //если вражеская фигура стоит раньше то потенциально бьет её
                        potentialXCoord = figure.getX_coord();
                        potentialYCoord = figure.getY_coord();
                        potentialKilled = figure;
                    }else{
                        //TODO until still on own place
                        //если союзная фигура стоит раньше
                        //пока что стоит на месте своем
                        potentialXCoord = figure.getX_coord()- vector[0] * 1;
                        potentialYCoord = figure.getY_coord() - vector[1] * 1;
                    }
                }
            }

        }
        System.out.print("Elephant " + this.side + " was: "+x_coord + " " + y_coord);
        x_coord = potentialXCoord;
        y_coord = potentialYCoord;
        if(potentialKilled != null) {
            chessBoard.getChessFigureList().remove(potentialKilled);
            System.out.println(" and killed another ");
        }
        System.out.println(" Now : " + x_coord + " " + y_coord);
        return true;
    }

    // алгоритм выбора длины шага
    // одинаков для всех фигур
    public void setStep() {
        int coeff;
        boolean isDone = false;
        int randomStep;
        Random random = new Random();
        int maxLine, minLine;
        if (this.chessType == ChessType.ELEPHANT || this.chessType == ChessType.QUEEN || this.chessType == ChessType.ROOK){
            maxLine = 8;
            minLine = 1;
        }else if (this.chessType == ChessType.KING) {
            maxLine = 1;
            minLine = 1;
        }else {
            //TODO
            maxLine = 0;
            minLine = 0;
        }

        if (side == Side.WHITE) coeff = +1;
        else coeff = -1;
        do {
            randomStep = random.nextInt(maxLine) + minLine;
            if (x_coord + coeff * vector[0] * randomStep > 0 && x_coord + coeff * vector[0] * randomStep < 9
                    && y_coord + coeff * vector[1] * randomStep > 0 && y_coord + coeff * vector[1] * randomStep < 9) {
                isDone = true;
            }
        }while (!isDone);

        step = randomStep;
    }

    //абстрактные функции задания вектора движения (для каждой фигуры свои навправления)
    //и различия типов этих векторов
    abstract public boolean setRandomVector(List<Integer> usedVectors);
    abstract public int getVectorType(int x,int y);

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getX_coord() {
        return x_coord;
    }

    public void setX_coord(int x_coord) {
        this.x_coord = x_coord;
    }

    public int getY_coord() {
        return y_coord;
    }

    public void setY_coord(int y_coord) {
        this.y_coord = y_coord;
    }


}
