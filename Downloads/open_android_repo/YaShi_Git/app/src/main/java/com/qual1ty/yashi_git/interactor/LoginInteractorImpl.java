package com.qual1ty.yashi_git.interactor;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by Tianci on 16/4/5.
 */
public class LoginInteractorImpl implements LoginInteractor {


    //    private String username_succ;
//    private String psw_succ;
//    private XmlResourceParser xmlParser;
//
    private static final String USERNAME = "username";
    private static final String PSW = "password";

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


    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    listener.onUsernameError();
                } else if (TextUtils.isEmpty(password) && !TextUtils.isEmpty(username)) {
                    listener.onPasswordError();
                } else if (username.equals(USERNAME) && password.equals(PSW))
                    listener.onSuccess();
                else {
                    listener.onPasswordError();
                }
            }
        }, 500);
    }

    @Override
    public void register(Activity sre, Class<?> clazz) {
        sre.startActivity(new Intent(sre, clazz));
    }
}
