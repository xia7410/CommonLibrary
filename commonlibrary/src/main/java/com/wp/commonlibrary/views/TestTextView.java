package com.wp.commonlibrary.views;

import android.content.Context;
import android.util.AttributeSet;

import com.wp.commonlibrary.rx.DownloadObservableManager;
import com.wp.commonlibrary.rx.ObservableManager;
import com.wp.commonlibrary.utils.LogUtils;

/**
 * Created by WangPing on 2018/2/9.
 */

public class TestTextView extends android.support.v7.widget.AppCompatTextView implements IViewProgressEvent {
    private String url;
    private long downloaded;

    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void cancelDownload() {
        DownloadObservableManager.getInstance().stopObserver(url);
    }


    @Override
    public void start(String url, long totalLength) {
        this.url = url;
        LogUtils.e("start ", url);
        LogUtils.e("url:" + url + "\ntotalLength:" + totalLength);
    }

    @Override
    public void updateProgress(int progress) {
        LogUtils.e("updateProgress " + progress);
        setText("" + progress);
    }

    @Override
    public void end() {

    }

    @Override
    public void cancel(long downloaded) {
        setText("cancel");
        LogUtils.e("cancel", "" + downloaded);
        this.downloaded = downloaded;
    }

    @Override
    public void interrupt(long downloaded) {
        setText("网络连接中断");
        LogUtils.e("interrupt", "" + downloaded);
    }
}
