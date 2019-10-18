package com.autotlalpan.autostlalpanapplication.view;


import com.autotlalpan.autostlalpanapplication.model.CarsPojo;

import java.util.ArrayList;

public interface ViewContract {

    void onDataPopulated(ArrayList<CarsPojo> data);

}
