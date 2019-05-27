package demo.down.com.downloadlibrary;


import java.io.IOException;

import demo.down.com.downloadlibrary.listener.DownFileCallback;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wz on 2019/5/20.
 */

public class Downloadinterceptor implements Interceptor {

    private DownFileCallback downFileCallback;

    private String downUrl;

    public Downloadinterceptor(DownFileCallback listener,String downUrl) {
        this.downFileCallback = listener;
        this.downUrl = downUrl;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        return response.newBuilder()
                .body(new DownloadResponseBody(response.body(), downFileCallback,downUrl))
                .build();
    }
}
