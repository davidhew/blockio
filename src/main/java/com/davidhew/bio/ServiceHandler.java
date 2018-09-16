package com.davidhew.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * Created by shouru on 2018/9/16.
 */
public class ServiceHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ServiceHandler.class.getName());

    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private StringBuilder strB = null;

    public ServiceHandler(Socket sk) {
        this.socket = sk;
    }

    @Override
    public void run() {

        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            byte[] byteBuffer = new byte[1024];

            while(true) {
                strB = new StringBuilder(1024);
                while (!strB.toString().endsWith("\n")) {
                    int result = is.read(byteBuffer);

                    if(result == -1){
                        break;
                    }
                    String str = new String(byteBuffer, 0,result,"utf-8");
                    strB.append(str);
                    if(logger.isInfoEnabled()){
                        logger.info(str);
                    }

                }
                String request = strB.toString();

                String infos[] = request.split(";");

                if (logger.isInfoEnabled()) {
                    logger.info("Get request: " + request);
                }

                if (infos.length != 3) {

                    String errorInfo = "Request Format Error!\n";
                    os.write(errorInfo.getBytes());
                    os.flush();
                    logger.error(errorInfo);

                } else {

                    String requestor = infos[0];
                    String command = infos[1];
                    //去除掉最后的\n
                    String number = infos[2].substring(0, infos[2].length() - 1);

                    int intNumber = 0;
                    try {

                        intNumber = Integer.parseInt(number);
                    } catch (NumberFormatException e) {

                        String errorInfo = String.format("intNumber Error!,it should be an integer, but it's %s\n", number);
                        os.write(errorInfo.getBytes());
                        logger.error(errorInfo);
                    }

                    long result = 0L;
                    if (Command.ComputePrime.name().equals(infos[1])) {

                        result = new ComputePrimeWorker().getResult(intNumber);

                    } else if (Command.ComputeSum.name().equals(infos[1])) {

                        result = new ComputeSumWorker().getResult(intNumber);
                    }

                    String response = String.format("%s;%d\n", infos[0], result);
                    os.write(response.getBytes());
                    os.flush();

                }
            }

        } catch (Exception e) {
            logger.error("Exception occurs,",e);
        }
    }
}
