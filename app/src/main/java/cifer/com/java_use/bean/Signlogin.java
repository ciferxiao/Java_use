package cifer.com.java_use.bean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by cifer on 2018/6/12 14:26.
 */
public interface Signlogin {

    @POST("login")
    Observable<UserBean> signin(@Query("loginName") String user,@Query("password")String password);

}
