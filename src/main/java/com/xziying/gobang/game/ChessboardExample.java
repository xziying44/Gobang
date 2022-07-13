package com.xziying.gobang.game;

import com.xziying.gobang.exception.BeyondBordersException;
import com.xziying.gobang.exception.NotToPlayException;
import com.xziying.gobang.exception.WhereConflictException;
import com.xziying.gobang.factory.ChessFactory;
import com.xziying.gobang.factory.SingleFactory;
import com.xziying.gobang.pojo.Chess;
import com.xziying.gobang.pojo.GameConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChessboardExample implements Chessboard {
    int width;
    int height;
    int index = Chessboard.BLACK;  // 当前执子 黑棋先行
    ChessFactory chessFactory = SingleFactory.ChessFactory();
    Map<String, Chess> map = new ConcurrentHashMap<>();

    public ChessboardExample() {
        // 初始化棋盘
        GameConfig config = SingleFactory.Config();
        width = config.getWidth();
        height = config.getHeight();
    }

    /**
     * 下棋
     *
     * @param type 棋子颜色 1 黑色 2 白色
     * @param x    横坐标
     * @param y    纵坐标
     * @return 是否胜利
     * 异常 @BeyondBordersException 超出棋盘边界 @WhereConflictException 当前位置不可下
     */
    @Override
    public boolean play(int type, int x, int y) {
        if (index != type){
            throw new NotToPlayException(); // 当前不可下棋
        }
        if ((x < 0 || x >= width) || (y < 0 || y >= height)) {
            throw new BeyondBordersException(); // 超出边界
        }
        String position = chessFactory.getPositionInfo(Chessboard.NULL, x, y);  // 消除棋子信息
        if (map.containsKey(position)) {
            throw new WhereConflictException(); // 当前位置有其他的棋子
        }
        Chess chess = chessFactory.getChess(type, x, y);
        map.put(position, chess);

        // 切换对手下棋
        if (type == Chessboard.BLACK){
            index = Chessboard.WHITE;
        } else {
            index = Chessboard.BLACK;
        }

        // 判断局势
        int[] weights = {1, 1, 1, 1};
        for (int i = 0; i < 8; i++) {
            int _x = x;
            int _y = y;
            for (int j = 0; j < 5; j++) {
                switch (i) {
                    case 0 -> _x -= 1;
                    case 1 -> _x += 1;
                    case 2 -> _y -= 1;
                    case 3 -> _y += 1;
                    case 4 -> {
                        _x -= 1;
                        _y -= 1;
                    }
                    case 5 -> {
                        _x += 1;
                        _y += 1;
                    }
                    case 6 -> {
                        _x -= 1;
                        _y += 1;
                    }
                    case 7 -> {
                        _x += 1;
                        _y -= 1;
                    }

                }
                // 获取位置上的棋子
                String positionInfo = chessFactory.getPositionInfo(Chessboard.NULL, _x, _y);

                if (map.containsKey(positionInfo) && map.get(positionInfo).getType() == type) {
                    switch (i) {
                        case 0, 1 -> weights[0] += 1;
                        case 2, 3 -> weights[1] += 1;
                        case 4, 5 -> weights[2] += 1;
                        case 6, 7 -> weights[3] += 1;
                    }
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] >= 5){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Chess> getChess() {
        List<Chess> chess = new ArrayList<>();
        for (String s : map.keySet()){
            chess.add(map.get(s));
        }
        return chess;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * 打印棋盘到控制台
     */
    @Override
    public void display() {
        int[][] array = new int[width][height];
        List<Chess> chess = getChess();
        for (Chess s : chess){
            array[s.getX()][s.getY()] = s.getType();
        }

        System.out.print('~');
        System.out.print("\t");

        for (int i = 0; i < width; i++) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print(i);
            System.out.print("\t");
            for (int j = 0; j < width; j++) {
                System.out.print(array[j][i]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

}
