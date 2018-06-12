package cifer.com.java_use.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cifer.com.java_use.R;
import cifer.com.java_use.Util.EncryptUtil;
import cifer.com.java_use.Util.RetrofitUtils;
import cifer.com.java_use.bean.Signlogin;
import cifer.com.java_use.bean.UserBean;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cifer
 * on 2018/4/19 13:14.
 */

public class LoginActivity extends BaseActivity /*implements View.OnClickListener*/{

    @BindView(R.id.input_phone)
    EditText phoneedit;

    @BindView(R.id.input_password)
    EditText passedit;

    @BindView(R.id.btn_login)
    AppCompatButton loginbutton;

    Observable<UserBean> observable1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);

        //loginbutton.setOnClickListener(this);

    }

    @OnClick(R.id.btn_login)
    public void onClick(View v) {
        if (!validate()) {
            onLoginFailed();
            return;
        }
        loginbutton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.Theme_AppCompat);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String phone = phoneedit.getText().toString();
        String password = passedit.getText().toString();

        // TODO: Implement your own authentication logic here.
        Log.d("xiao111"," 1111111111");
//http://116.62.44.77/readServer/api/user/login?loginName="+"18616331232"+"&password="+password
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://116.62.44.77/readServer/api/user/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitUtils.getOkHttpClient())//添加打印请求
                .build();

        Signlogin signin = retrofit.create(Signlogin.class);
        observable1 = signin.signin(phone, EncryptUtil.md5(password));//md5加密

        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean value) {
                        //if(value.getCourseid() == 1){
                            Log.d("xiao111","SUCCESS");
                            Toast.makeText(LoginActivity.this, "Login SUCCESS", Toast.LENGTH_SHORT).show();
                        //}
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginbutton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String phone = phoneedit.getText().toString();
        String password = passedit.getText().toString();

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            phoneedit.setError("enter a valid  Phone number");
            valid = false;
        } else {
            phoneedit.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passedit.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passedit.setError(null);
        }

        return valid;
    }

}
