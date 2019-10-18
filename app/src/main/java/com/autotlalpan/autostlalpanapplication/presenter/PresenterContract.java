package com.autotlalpan.autostlalpanapplication.presenter;


import com.autotlalpan.autostlalpanapplication.view.ViewContract;

public interface PresenterContract {

    void initRetrofit();
    void onBindView(ViewContract view);
}
