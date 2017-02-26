package com.example.android.svce.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.android.svce.R;

/**
 * Created by jennifernghinguyen on 2/25/17.
 */

public class MyInfoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.my_info_fragment, container, false);

        return rootView;
    }
}
