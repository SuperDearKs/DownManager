package demo.down.com.downloadlibrary.listener;


import demo.down.com.downloadlibrary.DownloadInfo;

/**
 * Created by wz on 2019/5/20.
 */


public interface DownFileCallback {

    void onSuccess(DownloadInfo info);

    void onFail(String msg);

    void onProgress(long totalSize, long downSize);
}
