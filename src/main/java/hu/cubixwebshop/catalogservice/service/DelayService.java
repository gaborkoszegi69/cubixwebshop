package hu.cubixwebshop.catalogservice.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class DelayService {
    private Random random = new Random();

    public double getDelay(long id, double price) {
        System.out.println("DelayService.getDelay called at thread " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        //return random.nextInt(0, 1800);
        return  price;
    }

    @Async
    public CompletableFuture<Integer> getDelayAsync(long flightId) {
        System.out.println("DelayService.getDelay called at thread " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return CompletableFuture.completedFuture(random.nextInt(0, 1800));
    }
}
