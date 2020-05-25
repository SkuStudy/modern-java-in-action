package Chapter16.ex;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class CompletableFuture_ex {
    private final static Logger log = Logger.getGlobal();

//    public static CompletableFuture<Void> runAsync(Runnable runnable)

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(()->{
            log.info("test");
        }).get();

        log.info("========================");

        log.info(CompletableFuture.supplyAsync(()->{
            log.info("supplyAsync-test");
            return "test";
        }).get());

        log.info("========================");

        CompletableFuture.supplyAsync(()->{
            log.info("test1");
            return "test1";
        }).thenAccept(s->log.info("complete1{}"));

        CompletableFuture.runAsync(()->{
            log.info("test2");
        }).thenAccept(s->log.info("complete2{}"));



    }


}
