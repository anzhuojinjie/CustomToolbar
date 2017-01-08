package com.hsg.customtoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Joe on 2017/1/7.
 */

public class CustomToolBar extends RelativeLayout {
    private String mTitleText;
    private float mTitleSize;
    private int mTitleColor;
    private TextView mTitleView;
    private ImageView mLeftView,mRightView;
    private Drawable leftImg,rightImg;
    public CustomToolBar(Context context) {
        super(context);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context,attrs);
    }

    private void initViews(Context context, AttributeSet attrs){
        //获得无类型数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomToolBar);
        //通过无类型数组获得属性
        mTitleText = typedArray.getString(R.styleable.CustomToolBar_mTitleText);
        mTitleSize = typedArray.getDimension(R.styleable.CustomToolBar_mTitleSize,20.0f);
        mTitleColor = typedArray.getColor(R.styleable.CustomToolBar_mTitleColor, Color.BLACK);
        leftImg = typedArray.getDrawable(R.styleable.CustomToolBar_leftImage);
        rightImg = typedArray.getDrawable(R.styleable.CustomToolBar_rightImage);
        typedArray.recycle();
        //初始化三个UI控件
        mTitleView = new TextView(context);
        mTitleView.setText(mTitleText);
        mTitleView.setTextSize(mTitleSize);
        mTitleView.setTextColor(mTitleColor);
        mLeftView = new ImageView(context);
        mLeftView.setImageDrawable(leftImg);
        mRightView = new ImageView(context);
        mRightView.setImageDrawable(rightImg);

        //设置三个控件的正确位置
        LayoutParams centerParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        centerParams.addRule(CENTER_IN_PARENT);
        addView(mTitleView,centerParams);

        LayoutParams leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(CENTER_VERTICAL);
        leftParams.addRule(ALIGN_PARENT_LEFT);

        addView(mLeftView,leftParams);

        LayoutParams rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(CENTER_VERTICAL);
        rightParams.addRule(ALIGN_PARENT_RIGHT);
        addView(mRightView,rightParams);

        mLeftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != imageClickListener){
                    imageClickListener.onLeftImageClick(view);
                }
            }
        });

        mRightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != imageClickListener){
                    imageClickListener.onRightImageClick(view);
                }
            }
        });


    }


    private ImageClickListener imageClickListener;
    public void setImageClickListener(ImageClickListener imageClickListener){
        this.imageClickListener = imageClickListener;
    }

    public interface ImageClickListener{
        public void onLeftImageClick(View view);
        public void onRightImageClick(View view);
    }
}
