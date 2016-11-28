package contentprovider.day.com.cn.mvp.model;

import contentprovider.day.com.cn.mvp.bean.UserBean;

/**
 * Created by ann on 2016/5/4.
 */
public class LoginModelImpl implements LoginModel{
    @Override
    public void login(final String name, final String password, final UserBean userBean, final OnLoginListener onLoginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (name.equals("123")&&password.equals("123")){
                    onLoginListener.loginSuccess();
                    userBean.setUserName(name);
                    userBean.setUserPassword(password);
                }else{
                    onLoginListener.loginFailed(3000);
                }
            }
        }).start();
    }
}
