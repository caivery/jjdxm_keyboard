package com.dou361.keyboard.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dou361.keyboard.adapter.PageSetAdapter;
import com.dou361.keyboard.bean.PageSetEntity;
import com.dou361.keyboard.utils.EmoticonsKeyboardUtils;
import com.dou361.keyboard.utils.ResourceUtils;

/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：陈冠明
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2016/6/22 11:50
 * <p>
 * 描 述：自定义简单键盘默认显示底部栏
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class CustomSimpleHideEmojiKeyBoard extends CustomEmojiKeyBoard {


    protected View vLine;
    private LinearLayout ll_keyboard;
    private onBackHideListener monBackHideListener;

    public CustomSimpleHideEmojiKeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateKeyboardBar() {
        mInflater.inflate(ResourceUtils.getResourceIdByName(mContext, "layout", "jjdxm_keyboard_view_keyboard_custom_simple"), this);
    }

    @Override
    protected View inflateFunc() {
        return mInflater.inflate(ResourceUtils.getResourceIdByName(mContext, "layout", "jjdxm_keyboard_view_func_emoticon_simple"), null);
    }

    @Override
    protected void initView() {
        ll_keyboard = ((LinearLayout) findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "ll_keyboard")));
        mEtChat = ((EmoticonsEditText) findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "et_chat")));
        mBtnFace = ((ImageView) findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "btn_face")));
        mBtnSend = ((Button) findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "btn_send")));
        mLyKvml = ((FuncLayout) findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "ly_kvml")));
        vLine = findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "v_line"));
        mBtnFace.setOnClickListener(this);
        mEtChat.setOnBackKeyClickListener(this);
    }

    @Override
    protected void showVoice() {

    }

    @Override
    protected void showText() {
    }

    @Override
    protected void checkVoice() {

    }

    @Override
    protected void initEmoticonFuncView() {
        View keyboardView = inflateFunc();
        mLyKvml.addFuncView(FUNC_TYPE_EMOTION, keyboardView);
        mEmoticonsFuncView = ((EmoticonsFuncView) findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "view_epv")));
        mEmoticonsIndicatorView = ((EmoticonsIndicatorView) findViewById(ResourceUtils.getResourceIdByName(mContext, "id", "view_eiv")));
        mEmoticonsFuncView.setOnIndicatorListener(this);
        mLyKvml.setOnFuncChangeListener(this);
    }

    @Override
    protected void initEditView() {
        mEtChat.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!mEtChat.isFocused()) {
                    mEtChat.setFocusable(true);
                    mEtChat.setFocusableInTouchMode(true);
                }
                return false;
            }
        });

        mEtChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    mBtnSend.setBackgroundResource(ResourceUtils.getResourceIdByName(mContext, "drawable", "jjdxm_keyboard_btn_send_bg"));
                } else {
                }
            }
        });
    }

    @Override
    public void onFuncChange(int key) {
        if (FUNC_TYPE_EMOTION == key) {
            mBtnFace.setImageResource(ResourceUtils.getResourceIdByName(mContext, "drawable", "jjdxm_keyboard_btn_voice_or_text_keyboard"));
        } else {
            mBtnFace.setImageResource(ResourceUtils.getResourceIdByName(mContext, "drawable", "jjdxm_keyboard_btn_emoji_or_text"));
        }
    }

    @Override
    public void setAdapter(PageSetAdapter pageSetAdapter) {
        mEmoticonsFuncView.setAdapter(pageSetAdapter);
    }

    @Override
    public void emoticonSetChanged(PageSetEntity pageSetEntity) {
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == ResourceUtils.getResourceIdByName(mContext, "id", "btn_face")) {
            togglePanl();
        }
    }

    /**
     * 点击开关
     */
    private void togglePanl() {
        if (vLine.isShown()) {
            /**表情*/
            vLine.setVisibility(GONE);
            mBtnFace.setImageResource(ResourceUtils.getResourceIdByName(mContext, "drawable", "jjdxm_keyboard_btn_voice_or_text_keyboard"));
        } else {
            /**软键盘*/
            vLine.setVisibility(VISIBLE);
            mBtnFace.setImageResource(ResourceUtils.getResourceIdByName(mContext, "drawable", "jjdxm_keyboard_btn_emoji_or_text"));
        }
        toggleFuncView(FUNC_TYPE_EMOTION);
    }

    /**
     * 显示面板
     */
    @Override
    public void reset() {
        EmoticonsKeyboardUtils.closeSoftKeyboard(this);
        mLyKvml.hideAllFuncView();
        mBtnFace.setImageResource(ResourceUtils.getResourceIdByName(mContext, "drawable", "jjdxm_keyboard_icon_face_nomal"));
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                if (mDispatchKeyEventPreImeLock) {
                    mDispatchKeyEventPreImeLock = false;
                    ll_keyboard.setVisibility(GONE);
                    if(monBackHideListener!=null){monBackHideListener.onBackHide();}
                    return true;
                }
                if (mLyKvml.isShown()) {
                    reset();
                    ll_keyboard.setVisibility(GONE);
                    if(monBackHideListener!=null){monBackHideListener.onBackHide();}
                    return true;
                } else {
                    return super.dispatchKeyEvent(event);
                }
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 显示面板
     */
    public void showUI() {
        if (ll_keyboard != null) {
            ll_keyboard.setVisibility(VISIBLE);
            togglePanl();
        }
    }

    public void hideUI() {
        if (ll_keyboard != null) {
            reset();
            ll_keyboard.setVisibility(GONE);
        }
    }

    /**
     * 判断是否显示面板
     */
    public boolean isPanlShow() {
        return (ll_keyboard != null && ll_keyboard.isShown());
    }

    public interface onBackHideListener{
        void onBackHide();
    }

    public void setonBackHideListener(onBackHideListener l){
        this.monBackHideListener = l;
    }


}
