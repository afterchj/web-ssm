package com.tpadsz.ssm.function;

import java.util.List;

@FunctionalInterface
public interface ExcelConsumer<E> {

    void execute(List<E> e);
}