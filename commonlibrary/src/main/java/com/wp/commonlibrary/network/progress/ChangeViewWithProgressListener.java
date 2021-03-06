package com.wp.commonlibrary.network.progress;

import com.wp.commonlibrary.views.IViewProgressEvent;

/**
 * 会随着进度更新View的ProgressListener
 * Created by WangPing on 2018/2/9.
 */

public class ChangeViewWithProgressListener extends MainThreadProgressListener {
    private IViewProgressEvent event;

    public ChangeViewWithProgressListener(IViewProgressEvent event) {
        this.event = event;
    }

    @Override
    public void start(String url, long totalLength) {
        event.start(url, totalLength);
    }

    @Override
    public void updateProgress(int progress) {
        event.updateProgress(progress);
    }

    @Override
    public void end() {
        event.end();
    }

    @Override
    public void cancel(long downloaded) {
        event.cancel(downloaded);
    }

    @Override
    public void interrupt(long downloaded) {
        event.interrupt(downloaded);
    }

}
