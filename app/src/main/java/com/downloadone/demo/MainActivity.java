package com.downloadone.demo;


import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apeng.permissions.EsayPermissions;
import com.apeng.permissions.OnPermission;
import com.apeng.permissions.Permission;

import java.util.List;

import demo.down.com.downloadlibrary.DownloadInfo;
import demo.down.com.downloadlibrary.DownloadManager;
import demo.down.com.downloadlibrary.listener.DownFileCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button downloadBtn1, downloadBtn2, downloadBtn3;
    private Button cancelBtn1, cancelBtn2, cancelBtn3;
    private ProgressBar progress1, progress2, progress3;
    private String url1 = "http://imtt.dd.qq.com/16891/89E1C87A75EB3E1221F2CDE47A60824A.apk?fsname=com.snda.wifilocating_4.2.62_3192.apk";
    private String url2 = "http://imtt.dd.qq.com/16891/89E1C87A75EB3E1221F2CDE47A60824A.apk";
    private String url3 = "http://download.sdk.mob.com/apkbus.apk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();


        downloadBtn1 = bindView(R.id.main_btn_down1);
        downloadBtn2 = bindView(R.id.main_btn_down2);
        downloadBtn3 = bindView(R.id.main_btn_down3);

        cancelBtn1 = bindView(R.id.main_btn_cancel1);
        cancelBtn2 = bindView(R.id.main_btn_cancel2);
        cancelBtn3 = bindView(R.id.main_btn_cancel3);

        progress1 = bindView(R.id.main_progress1);
        progress2 = bindView(R.id.main_progress2);
        progress3 = bindView(R.id.main_progress3);

        downloadBtn1.setOnClickListener(this);
        downloadBtn2.setOnClickListener(this);
        downloadBtn3.setOnClickListener(this);

        cancelBtn1.setOnClickListener(this);
        cancelBtn2.setOnClickListener(this);
        cancelBtn3.setOnClickListener(this);


    }


    private <T extends View> T bindView(@IdRes int id) {
        View viewById = findViewById(id);
        return (T) viewById;
    }


    /**
     * 6.0 权限控制
     */
    public void requestPermission() {

        EsayPermissions.with(this)
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
//                .permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求6.0悬浮窗权限8.0请求安装权限
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "取权限成功，部分权限未正常授予", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            Toast.makeText(MainActivity.this, "被永久拒绝授权，请手动授予权限", Toast.LENGTH_LONG).show();
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            EsayPermissions.gotoPermissionSettings(MainActivity.this);
                        } else {
                            Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_down1:

                DownloadManager.getInstance().downloadPath(AppStoragePath.getCachePath()).download(url1, new DownFileCallback() {
                    @Override
                    public void onSuccess(DownloadInfo info) {

                        Toast.makeText(MainActivity.this, url1 + "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String msg) {
                        Toast.makeText(MainActivity.this, url1 + "下载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(final long totalSize, final long downSize) {
                        // 需要注意的是，如果文件总大小为50M，已下载的大小为10M，
                        // 再次下载时onProgress返回的totalSize是文件总长度
                        // 减去 已下载大小 10M， 即40M，downSize为本次下载的已下载量
                        // 好消息是，我已经在内部做过处理，放心使用吧，但是这个问题大家还是要知道的

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int progress = (int) (downSize * 100 / totalSize);
                                progress1.setProgress(progress);
                            }
                        });
                    }
                });

                break;
            case R.id.main_btn_down2:
                DownloadManager.getInstance().downloadPath(AppStoragePath.getCachePath()).download(url2, new DownFileCallback() {

                    @Override
                    public void onSuccess(DownloadInfo info) {

                        Toast.makeText(MainActivity.this, url2 + "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String msg) {
                        Toast.makeText(MainActivity.this, url2 + "下载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(final long totalSize, final long downSize) {
                        // 需要注意的是，如果文件总大小为50M，已下载的大小为10M，
                        // 再次下载时onProgress返回的totalSize是文件总长度
                        // 减去 已下载大小 10M， 即40M，downSize为本次下载的已下载量
                        // 好消息是，我已经在内部做过处理，放心使用吧，但是这个问题大家还是要知道的

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int progress = (int) (downSize * 100 / totalSize);
                                progress2.setProgress(progress);
                            }
                        });
                    }
                });
                break;
            case R.id.main_btn_down3:
                DownloadManager.getInstance().downloadPath(AppStoragePath.getCachePath()).download(url3, new DownFileCallback() {

                    @Override
                    public void onSuccess(DownloadInfo info) {

                        Toast.makeText(MainActivity.this, url3 + "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String msg) {
                        Toast.makeText(MainActivity.this, url3 + "下载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(final long totalSize, final long downSize) {
                        // 需要注意的是，如果文件总大小为50M，已下载的大小为10M，
                        // 再次下载时onProgress返回的totalSize是文件总长度
                        // 减去 已下载大小 10M， 即40M，downSize为本次下载的已下载量
                        // 好消息是，我已经在内部做过处理，放心使用吧，但是这个问题大家还是要知道的

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int progress = (int) (downSize * 100 / totalSize);
                                progress3.setProgress(progress);
                            }
                        });
                    }
                });
                break;
            case R.id.main_btn_cancel1:
                DownloadManager.getInstance().stop(url1);
                break;
            case R.id.main_btn_cancel2:
                DownloadManager.getInstance().stop(url2);
                break;
            case R.id.main_btn_cancel3:
                DownloadManager.getInstance().stop(url3);
                break;
        }

    }

}
