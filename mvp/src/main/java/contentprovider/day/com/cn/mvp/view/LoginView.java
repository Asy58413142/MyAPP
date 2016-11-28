package contentprovider.day.com.cn.mvp.view;

import contentprovider.day.com.cn.mvp.bean.UserBean;

/**
 * Created by ann on 2016/5/4.
 */
public interface LoginView {

    String setName();
    String  setPassword();
    void showLoading();
    void showStuta(int codo);
    void clearInfo();
    void toNext();
    void getUserInfo(UserBean userBeen);
}
