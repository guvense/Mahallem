package com.mahallem.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AsyncUtil {


    public <T> void invoke(Supplier<T> supplier, Consumer<String> dataChannel,Consumer<String> errorChannel ) {

          CompletableFuture.supplyAsync(supplier)
                                .thenApply(Object::toString)
                                .handleAsync((s,th) -> {
                                    errorChannel.accept(th.getMessage());
                                    return "Error Elastic";
                                })
                                .thenAccept(dataChannel)
                                .orTimeout(1, TimeUnit.SECONDS);

    }

}

