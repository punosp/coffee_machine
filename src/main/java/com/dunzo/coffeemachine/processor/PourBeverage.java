package com.dunzo.coffeemachine.processor;

import com.dunzo.coffeemachine.model.enums.BeverageType;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PourBeverage {

    private int outlets;
    private ExecutorService threadPool;

    @Autowired
    public PourBeverage() {
        this.outlets = 0;
        threadPool = Executors.newFixedThreadPool(20);
    }

    public void setOutlets(int outlets) {
        this.outlets = outlets;
    }


    public void pour(Collection<BeverageType> beverageTypes) {
        try {
            Flowable.fromIterable(beverageTypes)
                    .parallel(outlets)
                    .runOn(Schedulers.from(threadPool))
                    .sequential()
                    .forEach(beverageType -> {
                        Thread.sleep(2000); // just saying that every task takes 2 secs to complete
                        System.out.println(beverageType.name() + " forEach, thread is " +
                                Thread.currentThread().getName());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
