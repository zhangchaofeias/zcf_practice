package com.example.huoyunren.popwindow;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class CMyService extends Service {

    public static final String TAG = "ZCF_MyService";
    public static final String KEY_NAME = "name";
    public static final String KEY_SEX = "sex";
    public static final String KEY_STUDENT = "student";
    public static final String KEY_NAME_CHANGED = "name_changed";
    public static final String KEY_SEX_CHANGED = "sex_changed";

    private AsyncTaskExample mAsyncTaskExample;
    private StudentBinder student;

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return mAsyncTaskExample.getBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        mAsyncTaskExample = new AsyncTaskExample();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");
        student = new StudentBinder();
        student.setName(intent.getStringExtra(KEY_NAME));
        student.setSex(intent.getStringExtra(KEY_SEX));
        Map<String, Object> map = new HashMap<>();
        map.put(KEY_STUDENT, student);
        map.put(KEY_NAME_CHANGED, intent.getStringExtra(KEY_NAME_CHANGED));
        map.put(KEY_SEX_CHANGED, intent.getStringExtra(KEY_SEX_CHANGED));
        if (mAsyncTaskExample.cancel(true))
        mAsyncTaskExample.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, map);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    private class AsyncTaskExample extends AsyncTask<Map, Float, StudentBinder> {

        private StudentBinder mStudentBinder;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute()");
            mStudentBinder = new StudentBinder();
        }

        @Override
        protected void onPostExecute(StudentBinder bind) {
            super.onPostExecute(bind);
            Log.d(TAG, "onPostExecute()");
            mStudentBinder = bind;
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "onProgressUpdate()");
        }

        @Override
        protected StudentBinder doInBackground(Map... params) {
            Log.d(TAG, "doInBackground()");
            if (params[0] != null) {
                StudentBinder student = (StudentBinder) params[0].get(KEY_STUDENT);
                if (student != null) {
                    student.setName(params[0].get(KEY_NAME_CHANGED).toString());
                    student.setSex(params[0].get(KEY_SEX_CHANGED).toString());
                }
            }
            return student;
        }

        private StudentBinder getBind() {
            return mStudentBinder;
        }
    }

    public class StudentBinder extends Binder {
        private String name;
        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
