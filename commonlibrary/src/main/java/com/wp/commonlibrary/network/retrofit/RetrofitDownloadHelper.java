package com.wp.commonlibrary.network.retrofit;

import com.wp.commonlibrary.network.DownloadFile;
import com.wp.commonlibrary.network.FileCallBack;
import com.wp.commonlibrary.network.IDownloadService;
import com.wp.commonlibrary.network.ProgressManager;
import com.wp.commonlibrary.rx.ThreadTransformer;
import com.wp.commonlibrary.utils.FileIOUtils;

import java.io.File;

/**
 * Retrofit下载服务
 * Created by WangPing on 2018/1/26.
 */

public class RetrofitDownloadHelper implements IDownloadService {
    @Override
    public void download(DownloadFile downloadFile, FileCallBack callBack) {
        ProgressManager.addListener(downloadFile.getUrl(), downloadFile.getListener()); //监听进度

        RetrofitHelper.getDefault()
                .getService(DownloadService.class)
                .download(downloadFile.getUrl())
                .map(responseBody -> {
                    File file = downloadFile.getFile();
                    if (file == null)
                        throw new IllegalArgumentException("下载文件不能为空");

                    if (FileIOUtils.writeFileFromIS(file, responseBody.byteStream())) {
                        return file;
                    } else {
                        return null;
                    }
                })
                .compose(ThreadTransformer.io2main())
                .subscribe(file -> {
                    if (file == null) {
                        callBack.downloadFail(new Exception("文件IO有问题"));
                    } else {
                        callBack.downloadSuccess(file);
                    }
                }, callBack::downloadFail);
    }
}
