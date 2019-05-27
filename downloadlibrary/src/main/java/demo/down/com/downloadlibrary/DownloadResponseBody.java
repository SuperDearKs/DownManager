package demo.down.com.downloadlibrary;


import java.io.File;
import java.io.IOException;

import demo.down.com.downloadlibrary.listener.DownFileCallback;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by wz on 2019/5/20.
 */

public class DownloadResponseBody extends ResponseBody {
    private ResponseBody responseBody;

    private DownFileCallback downFileCallback;

    // BufferedSource 是okio库中的输入流，这里就当作inputStream来使用。
    private BufferedSource bufferedSource;
    private String downUrl;


    public DownloadResponseBody(ResponseBody responseBody, DownFileCallback downFileCallback, String downUrl) {
        this.responseBody = responseBody;
        this.downFileCallback = downFileCallback;
        this.downUrl = downUrl;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            File file = new File(DownloadManager.getInstance().getTemporaryName(downUrl));

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (null != downFileCallback) {
                    if (bytesRead != -1) {
                        long loacalSize = file.length();//本地已下载的长度
                        long trueTotal = loacalSize + responseBody.contentLength() - totalBytesRead;//文件真实长度
                        downFileCallback.onProgress(trueTotal,loacalSize);
                    } else {

                    }

                }
                return bytesRead;
            }
        };

    }
}
