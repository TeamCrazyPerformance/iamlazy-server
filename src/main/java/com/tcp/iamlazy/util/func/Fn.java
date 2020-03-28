package com.tcp.iamlazy.util.func;

import java.util.function.Supplier;

/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 2:15 오전 29 User we at 02 15 To change this
 * template use File | Settings | File Templates.
 */
public class Fn {

  public static Supplier<RuntimeException> throwErr(Supplier<Throwable> supplier) {
    return () -> new RuntimeException(supplier.get());
  }

}
