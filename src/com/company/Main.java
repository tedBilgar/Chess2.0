package com.company;

import com.company.board.ChessBoard;
import com.company.figure.ChessFigure;
import com.company.figure.Elephant;
import com.company.types.Side;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        List<ChessFigure> chessFigures = new LinkedList<>();
        for (int i=0;i<4;i++){
            chessFigures.add(new Elephant(Side.WHITE,chessBoard,3+i,1));
            chessFigures.add(new Elephant(Side.BLACK,chessBoard,3+i,8));
        }
        chessBoard.setChessFigureList(chessFigures);
        boolean isWhitePick = true;
        Random random = new Random();
        boolean isEnd;
        do{
            isEnd = true;
            if (isWhitePick){
                int randomNum = random.nextInt(chessBoard.getChessFigureList().size()-1);
                System.out.println("white");
                if(chessBoard.getChessFigureList().get(randomNum).getSide() == Side.WHITE){
                    chessBoard.getChessFigureList().get(randomNum).setChessBoard(chessBoard);
                    chessBoard.getChessFigureList().get(randomNum).move();
                    isWhitePick = false;

                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                int randomNum = random.nextInt(chessBoard.getChessFigureList().size()-1);
                System.out.println("black");
                for (ChessFigure chessFigure:
                        chessBoard.getChessFigureList()) {
                    System.out.println(chessFigure.getSide().toString() + " ");
                }
                if(chessBoard.getChessFigureList().get(randomNum).getSide() == Side.BLACK){
                    chessBoard.getChessFigureList().get(randomNum).setChessBoard(chessBoard);
                    chessBoard.getChessFigureList().get(randomNum).move();
                    isWhitePick = true;

                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            if(chessBoard.getChessFigureList().size()>1) {
                Side side = chessBoard.getChessFigureList().get(0).getSide();
                for (int i = 1;i<chessBoard.getChessFigureList().size();i++){
                    if (side != chessBoard.getChessFigureList().get(i).getSide()){
                        isEnd = false;
                        break;
                    }
                }
                if (isEnd) {
                    System.out.println("IS END");
                }
            }else if (chessBoard.getChessFigureList().isEmpty() || chessBoard.getChessFigureList().size() == 1) isEnd = true;
        }while (!isEnd);

        for (ChessFigure chessFigure:
             chessBoard.getChessFigureList()) {
            System.out.println(chessFigure.getSide().toString() + " ");
        }
    }
}
