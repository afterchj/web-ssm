package com.tpadsz.ssm.model.genericity;

public interface Generator<T> {
    T next(Class clazz);
    T next();
}
