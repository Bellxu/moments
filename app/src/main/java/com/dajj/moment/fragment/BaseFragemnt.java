package com.dajj.moment.fragment;

import androidx.fragment.app.Fragment;

public abstract class BaseFragemnt extends Fragment {



    public abstract void refresh();

    public abstract <E>void setData(E e);


}
