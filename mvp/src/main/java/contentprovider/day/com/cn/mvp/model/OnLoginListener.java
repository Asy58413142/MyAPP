package contentprovider.day.com.cn.mvp.model;

/**
 * Created by ann on 2016/5/4.
 */
public interface OnLoginListener{
    void loginSuccess();
    void loginFailed(int code);
}
