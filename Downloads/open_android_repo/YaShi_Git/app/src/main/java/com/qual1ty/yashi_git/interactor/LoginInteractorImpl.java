package com.qual1ty.yashi_git.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.qual1ty.yashi_git.bean.User;
import com.qual1ty.yashi_git.database.DataBaseHelper;
import com.qual1ty.yashi_git.database.UserDao;

import java.util.List;

/**
 * Created by Tianci on 16/4/5.
 */
public class LoginInteractorImpl implements LoginInteractor, NavigationCommand {


    //    private String username_succ;
//    private String psw_succ;
//    private XmlResourceParser xmlParser;
//
    private static final String USERNAME = "admin";
    private static final String PSW = "admin";

    private User user;

//    public LoginInteractorImpl() {
//        xmlParser = activity.getApplication().getResources().getXml(R.xml.user);
//        getXmlString();
//    }

//    String getXmlString() {
//
//        int eventCode;
//        try {
//            eventCode = xmlParser.getEventType();
//            while (eventCode != XmlPullParser.END_DOCUMENT) {
//                switch (eventCode) {
//                    case XmlPullParser.START_DOCUMENT:
//                        break;
//                    case XmlPullParser.START_TAG:
//                        if (USERNAME.equals(xmlParser.getName())) {
//                            this.username_succ = xmlParser.nextText();
//                            Log.e("xxxxxxxxx", xmlParser.nextText());
//                            eventCode = xmlParser.next();
//
//                        } else if (PSW.equals(xmlParser.getName())) {
//                            this.psw_succ = xmlParser.nextText();
//                            Log.e("ccccccccc", xmlParser.nextText());
//                            eventCode = xmlParser.next();
//                        }
//                        break;
//                    case XmlPullParser.END_TAG:
//                        break;
//                    default:
//                        break;
//                }
//                eventCode = xmlParser.next();
//            }
//        } catch (final XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (final IOException e) {
//            e.printStackTrace();
//        } finally {
//            xmlParser.close();
//        }
//        return null;
//    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void login(@NonNull final String username, @NonNull final String password, @NonNull final Context context, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    listener.onUsernameError();
                } else if (TextUtils.isEmpty(password) && !TextUtils.isEmpty(username)) {
                    listener.onPasswordError();
                } else if (username.equals(USERNAME) && password.equals(PSW))
                    listener.onSuccess();
                else if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    DataBaseHelper helper = new DataBaseHelper(context);//参数是上下文
                    UserDao userDao = new UserDao(context);
                    List<User> users = userDao.query();
                    if (users.size() > 0) {
                        for (User user : users) {
                            if (user.username.equals(username)) {
                                if (userDao.getUserPsw(username).equals(password)){
                                    setUser(user);
                                    listener.onSuccess();
                                }
                                   
                                else {
                                    listener.onPasswordError();
                                }
                            } else {
                                listener.onUsernameError();
                            }
                        }
                    } else {
                        listener.onUsernameError();
                    }

                } else {
                    listener.onUsernameError();
                }
            }
        }, 500);
    }

    @Override
    public void navigate(Activity src, Class<?> clazz) {
        Intent intent = new Intent(src, clazz);
        intent.putExtra("user",getUser());
        src.startActivity(intent);
    }

}
