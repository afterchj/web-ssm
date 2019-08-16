package com.tpadsz.ssm.model.genericity;

public class BaseGenerator<T> implements Generator<T> {

    private Class<T> type;

    public BaseGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BaseGenerator<T>(type);
    }

    @Override
    public T next(Class clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
