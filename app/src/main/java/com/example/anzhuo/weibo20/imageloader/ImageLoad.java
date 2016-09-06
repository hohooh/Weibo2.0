package com.example.anzhuo.weibo20.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anzhuo on 2016/8/29.
 */
public class ImageLoad {
    private ImageView mImageView;
    private String mUrl;
    Handler mHander;

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);

            mImageView.setImageBitmap((Bitmap) msg.obj);
        };
    };

    public void showImageByThead(ImageView iv, final String url){
        mImageView = iv;
        mUrl = url;
        new Thread(){
            public void run() {
                Bitmap bitmap = getBitmapFromUrl(url);
                Message message = Message.obtain();
                message.obj=bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }
    private Bitmap getBitmapFromUrl(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL mUrl= new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap= BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
