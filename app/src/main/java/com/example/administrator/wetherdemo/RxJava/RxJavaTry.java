package com.example.administrator.wetherdemo.RxJava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/3.
 */

public class RxJavaTry {
    //Observable 被观察者
    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
        // create() 是 RxJava 最基本的创造事件序列的方法
        // 此处传入了一个 OnSubscribe 对象参数
        // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
        // 即观察者会依次调用对应事件的复写方法从而响应事件
        // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式

        // 2. 在复写的subscribe（）里定义需要发送的事件
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            emitter.onNext("一");
            emitter.onNext("二");
            emitter.onNext("三");
            emitter.onComplete();

        }
    });
    Observable observable1 = Observable.just("A", "B", "C");
    String[] words = {"A", "B", "C"};
    Observable observable2 = Observable.fromArray(words);

    //观察者

    Observer<String> observer = new Observer<String>() {
        // 观察者接收事件前，默认最先调用复写 onSubscribe（）
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String s) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }

    };

    //订阅了解观察者和被观察者
    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }

    };
    // 相同点：二者基本使用方式完全一致（实质上，在RxJava的 subscribe 过程中，Observer总是会先被转换成Subscriber再使用）
    // 不同点：Subscriber抽象类对 Observer 接口进行了扩展，新增了两个方法：
    // 1. onStart()：在还未响应事件前调用，用于做一些初始化工作
    // 2. unsubscribe()：用于取消订阅。在该方法被调用后，观察者将不再接收 & 响应事件
    // 调用该方法前，先使用 isUnsubscribed() 判断状态，确定被观察者Observable是否还持有观察者Subscriber的引用，
    // 如果引用不能及时释放，就会出现内存泄露

    //通过订阅（Subscribe）连接观察者和被观察者

    //observable.subscribe(observer);
    // 或者 observable.subscribe(subscriber);

    public void setObservable(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
