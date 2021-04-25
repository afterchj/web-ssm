package com.tpadsz.ssm.service.rabbit;

import org.apache.log4j.Logger;

/**
 * Created by hongjian.chen on 2019/1/24.
 */
public class RabbitmqConsumer {

    private Logger logger = Logger.getLogger(this.getClass());

    public void getMessage(String message) {
        logger.info("receive message: " + message);
    }
}
