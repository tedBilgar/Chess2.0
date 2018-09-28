package com.company.board;

import com.company.figure.ChessFigure;

import java.util.List;

public class ChessBoard {
    private List<ChessFigure> chessFigureList;

    public List<ChessFigure> getChessFigureList() {
        return chessFigureList;
    }

    public void setChessFigureList(List<ChessFigure> chessFigureList) {
        this.chessFigureList = chessFigureList;
    }
}
