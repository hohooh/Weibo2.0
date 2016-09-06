package com.example.anzhuo.weibo20.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anzhuo on 2016/8/26.
 */
public class NewsAsyncTask extends AsyncTask<String,Void,Bitmap> {
    private ImageView myImageView;
    private String mUrl;

    public NewsAsyncTask(ImageView imageView,String url){
         this.myImageView = imageView;
         this. mUrl = url;
    }
    //String...params是可变参数接受execute中传过来的参数
    @Override
    protected Bitmap doInBackground(String... params) {

        String url=params[0];
        //这里同样调用我们的getBitmapFromeUrl
        Bitmap bitmap = getBitmapFromUrl(params[0]);
        return bitmap;
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

    //这里的bitmap是从doInBackgroud中方法中返回过来的
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        myImageView.setImageBitmap(bitmap);
    }
}
