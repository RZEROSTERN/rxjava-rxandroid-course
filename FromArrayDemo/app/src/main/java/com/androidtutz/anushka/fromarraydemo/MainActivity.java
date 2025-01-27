package com.androidtutz.anushka.fromarraydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "myApp";
    private Observable<Integer> myObservable;
    private DisposableObserver<Integer> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Integer[] nums={1,2,3,4,5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObservable = Observable.fromArray(nums);

        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );

    }

    private DisposableObserver getObserver(){

        myObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer s) {


                Log.i(TAG, " onNext invoked "+s);

            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " onError invoked");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " onComplete invoked");
            }
        };


        return myObserver;
    }
}
