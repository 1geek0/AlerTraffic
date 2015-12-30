package com.geekydreaams.alertraffic;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Data_View extends LinearLayout {
    //Views
    TextView dataTitle;
    TextView dataTextView;
    //Resource Variables
    private String mTitle;
    private String mData;
    private String mDataHint;

    public Data_View(Context context) {
        super(context);
    }

    public Data_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Data_View, 0, 0);

        mTitle = a.getString(R.styleable.Data_View_dataTitle);
        mDataHint = a.getString(R.styleable.Data_View_dataHint);
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.data_view_layout, this, true);

        dataTitle = (TextView) findViewById(R.id.data_title);
        dataTitle.setText(mTitle);

        dataTextView = (TextView) findViewById(R.id.data_view);
        dataTextView.setText(mDataHint);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getmTitle() {
        return mTitle;
    }

    public String getmData() {
        return mData;
    }

    public Data_View setmData(String mData) {
        this.mData = mData;
        dataTextView.setText(mData);
        return this;
    }
}
