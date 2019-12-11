package com.xiaohong.wu.collections.reactiveX;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;


/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/11/13 15:05
 **/
public class ReactiveTest {

    public static void hello(String... names) {
        //Observable.from(names).subscribe(s -> System.out.println("Hello " + s + "!"));
    }

    public static void main(String[] args) throws InterruptedException {
        //hello("name","hehe");
        //Disposable hello_world = Flowable.just("Hello world").subscribe(System.out::println);
        Disposable d = Flowable.just("Hello world!")
                .delay(1, TimeUnit.SECONDS)
                .subscribeWith(new DisposableSubscriber<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("Start!");
                        request(1);
                    }

                    @Override
                    public void onNext(String t) {
                        System.out.println(t);
                        request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        Thread.sleep(500);
        // the sequence can now be cancelled via dispose()
        d.dispose();
    }
}
