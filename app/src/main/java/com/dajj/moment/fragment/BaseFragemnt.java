package com.dajj.moment.fragment;

import androidx.fragment.app.Fragment;

public class BaseFragemnt extends Fragment {
    public static Fragment newInstance(String from) {

        return new BaseFragemnt();
    }
}
