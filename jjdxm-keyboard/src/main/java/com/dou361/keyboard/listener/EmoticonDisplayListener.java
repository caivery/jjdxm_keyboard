package com.dou361.keyboard.listener;

import android.view.ViewGroup;

import com.dou361.keyboard.adapter.EmoticonsAdapter;

public interface EmoticonDisplayListener<T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
