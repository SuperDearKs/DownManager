# DownManager
基于ok+rxjava+retrofit的断点下载工具

使用方法：

1、Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 
2、Add the dependency：

  dependencies {
	        implementation 'com.github.SuperDearKs:DownManager:1.0.0'
	}

3、在你的AndroidManifest文件的application标签下添加 
  android:networkSecurityConfig="@xml/network_security_config"

4.在你需要用的地方使用：（事先需要获取sd权限）

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

 5、引入
    implementation 'com.squareup.okhttp3:okhttp:3.6.0'
    
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    
    implementation 'io.reactivex.rxjava2:rxjava:2.0.1'
 
