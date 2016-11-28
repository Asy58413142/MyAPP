package contentprovider.day.com.cn.mvp.model;

import contentprovider.day.com.cn.mvp.bean.UserBean;

/**
 * Created by ann on 2016/5/4.
 */
public interface LoginModel {

    public void login(String name, String password, UserBean userBean, OnLoginListener onLoginListener);
}

