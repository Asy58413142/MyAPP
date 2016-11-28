package contentprovider.day.com.cn.day713_volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Volley-StringRequest";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        StringRequest
//        JsonArrayRequest
//        JSONObject
//        ImageRequest
        //1.创建请求队列
        requestQueue = Volley.newRequestQueue(this, 30);
        //StringRequest

        String url = "https://www.hao123.com/?tn=99682755_hao_pg";
        //2.1.创建字符串请求
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //2.2.创建Json对象请求
        //null 为请求参数
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //2.3.创建Image请求
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        //3.将字符串请求添加到请求队列中
        requestQueue.add(stringRequest);
        requestQueue.add(jsonObjectRequest);
        requestQueue.add(imageRequest);

        //ImageLoader
        //图片缓存

        final HashMap<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
        int max = (int) Runtime.getRuntime().maxMemory();
        //max/8 或者max/10
        final LruCache<String, Bitmap> lruCache = new LruCache<String,Bitmap>(max / 8){
            @Override
            //重新计算大小
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                if (evicted) {
                    SoftReference<Bitmap> sf = new SoftReference<Bitmap>(oldValue);
                    cache.put(key, sf);
                }
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                Bitmap bitmap = lruCache.get(url);
                if (bitmap != null) {
                    return bitmap;
                }

                SoftReference<Bitmap> sf = cache.get(url);
                if (sf != null) {
                    bitmap = sf.get();

                }
                return bitmap;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url, bitmap);
            }
        });
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap bitmap = response.getBitmap();
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //netWorkImageView
        NetworkImageView networkImageView = (NetworkImageView) findViewById(R.id.netWorkImageView);
        //默认的图片
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        //下载失败显示的图片
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher);

        networkImageView.setImageUrl("http://img3.imgtn.bdimg.com/it/u=3015689816,3340703369&fm=21&gp=0.jpg",imageLoader);


        //清除volley缓存
        ClearCacheRequest clearCacheRequest=new ClearCacheRequest(requestQueue.getCache(), new Runnable() {
            @Override
            public void run() {
                //清除缓存成功
            }
        });
        requestQueue.add(clearCacheRequest);


        //取消请求

        requestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            //有几个请求就会调用多少次
            public boolean apply(Request<?> request) {
                //过滤器
                String requestUrl = request.getUrl();
                if (request.equals("过滤条件")) {
                    return true;//true 会取消请求
                }
                return false;
            }
        });

        //volley post 请求
        StringRequest stringRequestPost = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {   //重新设置请求参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "values");
                return params;
            }
        };

        requestQueue.add(stringRequestPost);
    }
}
