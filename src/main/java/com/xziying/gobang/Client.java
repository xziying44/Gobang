package com.xziying.gobang;

import com.xziying.gobang.factory.SingleFactory;
import com.xziying.gobang.game.Chessboard;
import com.xziying.gobang.game.ChessboardExample;
import com.xziying.gobang.pojo.GameConfig;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        // 自定义棋盘配置
        SingleFactory.Config(
                new GameConfig()
                        .setWidth(8)
                        .setHeight(10)
        );
        Chessboard chessboard = new ChessboardExample();

        Scanner sc = new Scanner(System.in);
        int x, y;

        while (true){
            System.out.println("黑方下棋：");
            x = sc.nextInt();
            y = sc.nextInt();
            try {
                if (chessboard.play(Chessboard.BLACK, x, y)){
                    chessboard.display();
                    System.out.println("黑色胜利");
                    break;
                }
                chessboard.display();
            } catch (Exception e){
                e.printStackTrace();
            }


            System.out.println("白方下棋：");
            x = sc.nextInt();
            y = sc.nextInt();
            try {
                if (chessboard.play(Chessboard.WHITE, x, y)){
                    chessboard.display();
                    System.out.println("白色胜利");
                    break;
                }
                chessboard.display();
            } catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}
