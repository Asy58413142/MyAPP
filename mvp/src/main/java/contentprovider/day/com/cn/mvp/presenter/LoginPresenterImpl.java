package contentprovider.day.com.cn.mvp.presenter;

import android.os.Handler;

import contentprovider.day.com.cn.mvp.bean.UserBean;
import contentprovider.day.com.cn.mvp.model.LoginModelImpl;
import contentprovider.day.com.cn.mvp.model.OnLoginListener;
import contentprovider.day.com.cn.mvp.view.LoginView;

/**
 * Created by ann on 2016/5/4.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginModelImpl loginModel;
    private Handler handler=new Handler();
    private UserBean userBean;
    public LoginPresenterImpl(LoginView loginView,UserBean userBean) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
        this.userBean=userBean;
    }

    @Override
    public void login() {

        loginModel.login(loginView.setName(), loginView.setPassword(),userBean, new OnLoginListener() {
            @Override
            public void loginSuccess() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showLoading();
                        loginView.toNext();
                        loginView.getUserInfo(userBean);
                    }
                });
            }

            @Override
            public void loginFailed(final int code) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       loginView.showStuta(code);
                   }
               });
            }
        });
    }
}
