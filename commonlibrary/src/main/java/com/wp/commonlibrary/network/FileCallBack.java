package com.wp.commonlibrary.network;

import java.io.File;

/**
 * Created by WangPing on 2018/1/26.
 */

public interface FileCallBack {
    void downloadSuccess(File file);

    void downloadFail(Throwable e);
}
