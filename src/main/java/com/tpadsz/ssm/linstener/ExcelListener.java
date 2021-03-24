package com.tpadsz.ssm.linstener;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.tpadsz.ssm.function.ExcelConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    private Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<T> list = new ArrayList<T>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。这里我们传入自己的function作为参数
     */
    ExcelConsumer consumer;

    public ExcelListener() {
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public ExcelListener(ExcelConsumer consumer) {
        this.consumer = consumer;
    }


    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
//        logger.warn("line {}",JSON.toJSONString(t));
        list.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        System.out.println(("line:"+JSON.toJSONString(list)));
        //执行函数

        consumer.execute(list);
    }

}
