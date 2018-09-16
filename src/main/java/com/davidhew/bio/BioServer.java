package com.davidhew.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by shouru on 2018/9/16.
 */
public class BioServer {

    private static final Logger logger = LoggerFactory.getLogger(BioServer.class.getName());
    private static ServerSocket serverSocket= null;

    private static Socket socket = null;

    private static final ArrayBlockingQueue ARRAY_QUEUE = new ArrayBlockingQueue(1000);
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(200,200,5, TimeUnit.SECONDS,ARRAY_QUEUE);

    static {

        if(logger.isInfoEnabled()){
            logger.info("Server initiate.......");
        }
        try{

            serverSocket = new ServerSocket(3306);
        }catch(Exception ex){

            logger.error("Server initiate failed",ex);
            System.exit(-1);
        }

    }

    public static void main(String[] args) throws Exception{

        while (true) {
            try {
                socket = serverSocket.accept(); // 这就是bio同步阻塞
                ServiceHandler handler = new ServiceHandler(socket);// 创建一个任务
                EXECUTOR_SERVICE.execute(handler);// 任务交给线程池
            }catch(Exception ex){
                logger.error("Exception occurs",ex);
            }
        }
    }
}
