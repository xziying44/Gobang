package com.xziying.gobang.factory;

import com.xziying.gobang.pojo.GameConfig;

/**
 * 游戏配置工厂
 */
public class SingleFactory {
    private static volatile GameConfig gameConfig;
    private static final ChessFactory chessFactory = new SingleChessFactory();


    // 懒加载单例
    public static GameConfig Config(){
       if (gameConfig == null){
           synchronized (SingleFactory.class){
               if (gameConfig == null){
                   // 默认配置
                   gameConfig = new GameConfig()
                           .setWidth(10)
                           .setHeight(10);
               }
           }
       }
       return gameConfig;
    }

    // 带参数懒加载单例
    public static GameConfig Config(GameConfig config){
        synchronized (SingleFactory.class){
            gameConfig = config;
        }
        return gameConfig;
    }

    public static ChessFactory ChessFactory(){
        return chessFactory;
    }
}
