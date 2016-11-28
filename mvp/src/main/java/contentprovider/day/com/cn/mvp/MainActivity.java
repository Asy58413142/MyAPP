package contentprovider.day.com.cn.mvp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import contentprovider.day.com.cn.mvp.bean.UserBean;
import contentprovider.day.com.cn.mvp.presenter.LoginPresenterImpl;
import contentprovider.day.com.cn.mvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView{

    private EditText editText_name;
    private EditText editText_password;
    private LoginPresenterImpl loginPresenter;
    private TextView textView;
    private UserBean userBean=new UserBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_password = (EditText) findViewById(R.id.editText_password);
        loginPresenter = new LoginPresenterImpl(this,userBean);
    }
    public void onLogin(View view){
        loginPresenter.login();
    }

    @Override
    public String setName() {
        return editText_name.getText().toString();
    }

    @Override
    public String setPassword() {
        return editText_password.getText().toString();
    }

    @Override
    public void showLoading() {
        final ProgressDialog dialog=ProgressDialog.show(this,"提示","正在登陆");
    }

    @Override
    public void showStuta(int codo) {
       if (codo==3000){
           Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void clearInfo() {

    }

    @Override
    public void toNext() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void getUserInfo(UserBean userBeen) {
        textView.setText("用户名："+userBeen.getUserName()+"\n"+"密码"+userBeen.getUserPassword());
    }
}
