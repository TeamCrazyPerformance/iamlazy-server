package com.tcp.iamlazy.util.functor;

@FunctionalInterface
public interface ToFunctor<T, R> {

  R to(T t);

}
