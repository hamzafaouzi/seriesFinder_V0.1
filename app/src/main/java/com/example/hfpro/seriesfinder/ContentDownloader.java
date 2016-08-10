package com.example.hfpro.seriesfinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ContentDownloader {

    public static String getStringData(String url)
    {
        try {
            DownloaderString downloader = new DownloaderString() ;
            return  downloader.execute(url).get() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "FAILED" ;

    }

    public static Bitmap getBitmapData(String url)
    {
        try {
            DownloaderBitmap downloaderBitmap = new DownloaderBitmap() ;
            return  downloaderBitmap.execute(url).get() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static class DownloaderString extends AsyncTask<String  , Void , String>
    {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... urls) {
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                StringBuilder stringBuilder = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                return stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }



        }
        @Override
        protected void onPostExecute(String s) {

        }
    }

    public static class DownloaderBitmap extends AsyncTask<String , Void , Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null ;
        }
    }


}