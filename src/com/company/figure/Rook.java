package com.company.figure;

import com.company.board.ChessBoard;
import com.company.types.ChessType;
import com.company.types.Side;

import java.util.List;

public class Rook extends ChessFigure {

    public Rook(Side side, ChessBoard chessBoard, int x_coord, int y_coord) {
        super(side, chessBoard, x_coord, y_coord);
        chessType = ChessType.ROOK;
    }

    @Override
    public boolean setRandomVector(List<Integer> usedVectors) {
        return false;
    }

    @Override
    public int getVectorType(int x, int y) {
        return 0;
    }
}
