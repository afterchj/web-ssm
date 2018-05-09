package com.tpadsz.ssm.utils;

import com.tpadsz.ssm.test.MyTest;
import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by hongjian.chen on 2018/1/25.
 */
public class XMemcachedClient implements DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(XMemcachedClient.class);

//        private MemcachedClient memcachedClient= MyTest.getMemcachedClient();
    private MemcachedClient memcachedClient;
    private long optTimeout = 1000;


    public <T> T get(String key) {
        try {
            return memcachedClient.get(key, optTimeout);
        } catch (RuntimeException | TimeoutException | InterruptedException | MemcachedException e) {
            handleException(e, key);
        }
        return null;
    }

    public Map<String, Object> getBulk(List<String> keys) {
        try {
            return memcachedClient.get(keys, optTimeout);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            handleException(e, String.valueOf(keys));
            return null;
        }
    }

    public void set(String key, int expiredTime, Object value) {
        try {
            memcachedClient.set(key, expiredTime, value);
        } catch (Exception e) {
            handleException(e, key);
        }
    }

    public void delete(String key) {
        try {
            memcachedClient.delete(key, optTimeout);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            handleException(e, key);
        }
    }

    public boolean incr(String key, final int by, final long defaultValue) {
        try {
            return memcachedClient.cas(key, new CASOperation<Long>() {

                @Override
                public int getMaxTries() {
                    return 3;
                }

                @Override
                public Long getNewValue(long currentCAS, Long currentValue) {
                    if (currentValue == null) {
                        return defaultValue;
                    } else {
                        return currentValue + by;
                    }
                }
            });
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            handleException(e, key);
            return false;
        }
    }

    public boolean incr(String key, final int by, final long defaultValue, int exp) {
        try {
            return memcachedClient.cas(key, exp, new CASOperation<Long>() {

                @Override
                public int getMaxTries() {
                    return 3;
                }

                @Override
                public Long getNewValue(long currentCAS, Long currentValue) {
                    if (currentValue == null) {
                        return defaultValue;
                    } else {
                        return currentValue + by;
                    }
                }
            });
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            handleException(e, key);
            return false;
        }
    }

    public long decr(String key, int by, long defaultValue) {
        try {
            return memcachedClient.decr(key, by, defaultValue);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            handleException(e, key);
            return -1;
        }
    }

    private void handleException(Exception e, String key) {
        logger.error("xmemcached client receive an exception with key:" + key + "  " + e.getMessage());
    }

    public void destroy() {
        if (memcachedClient != null) {
            try {
                memcachedClient.shutdown();
            } catch (IOException e) {
                logger.error("memcached client receive an exception while shutting down.", e);
            }
        }
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setOptTimeout(long optTimeout) {
        this.optTimeout = optTimeout;
    }
}
