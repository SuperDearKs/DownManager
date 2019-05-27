package demo.down.com.downloadlibrary;


import io.reactivex.observers.DisposableObserver;

/**
 * Created by wz on 2019/5/20.
 * 观察者
 */

public class ProgressDownSubscriber<T> extends DisposableObserver<T> {

    public T downinfo;


    @Override
    public void onNext(T t) {
        this.downinfo = t;

    }


    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
