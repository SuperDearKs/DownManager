package demo.down.com.downloadlibrary;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by wz on 2019/5/20.
 */

public interface HttpService {

    /*大文件需要加入Streaming这个判断，防止下载过程中写入到内存中,造成oom*/
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("range") String start, @Url String url);
}
