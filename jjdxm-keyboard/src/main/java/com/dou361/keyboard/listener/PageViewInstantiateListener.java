package com.dou361.keyboard.listener;

import android.view.View;
import android.view.ViewGroup;

import com.dou361.keyboard.bean.PageEntity;

public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}
