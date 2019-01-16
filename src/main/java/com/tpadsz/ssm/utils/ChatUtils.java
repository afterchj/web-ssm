package com.tpadsz.ssm.utils;

import org.apache.log4j.Logger;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

/**
 * Created by hongjian.chen on 2019/1/16.
 */
public class ChatUtils {

    private static Logger logger = Logger.getLogger(ChatUtils.class);

    public static void sendPicture(WebSocketSession session, String fileName) {
        FileInputStream input;
        try {
            File file = new File(PropertiesUtils.getValue("file") + fileName);
            input = new FileInputStream(file);
            byte bytes[] = new byte[(int) file.length()];
            input.read(bytes);
            BinaryMessage byteMessage = new BinaryMessage(bytes);
            session.sendMessage(byteMessage);
        } catch (Exception e) {
            logger.error("文件发送失败！" + e.getMessage());
            e.printStackTrace();
        }
    }

    //这里没做控制，所以聊天室内的人物名称可能发生重复
    public static String getRandomNickName() {
        String[] nickNameArray = {"Captain America", "Tom", "Lucy", "Super hero", "Iron Man", "Spider Man", "Robot"};
        Random random = new Random();
        return nickNameArray[random.nextInt(nickNameArray.length)];
    }
}
