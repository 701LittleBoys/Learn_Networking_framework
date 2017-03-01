package com.jikexueyuan.learnokhttp_001;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.imooc.download.file.FileStorageManager;
import com.imooc.download.http.DownloadCallback;
import com.imooc.download.http.HttpManager;
import com.imooc.download.untils.Logger;

import java.io.File;

public class DownLoadActivity extends AppCompatActivity {

    private ImageView imageView_001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);

        this.imageView_001 = (ImageView) findViewById(R.id.imageview_001);


        String download_src = "http://pic9.nipic.com/20100820/2531170_182750348398_2.jpg";


        File file = FileStorageManager.getInstance()
                .getFileByName("http://www.imooc.com");

        Logger.debug("Mr__LinLin", "file path=" + file.getAbsoluteFile());

        HttpManager.getInstance().a_syncRequest(download_src
                , new DownloadCallback() {
                    @Override
                    public void http_success(File file) {

                        final Bitmap bitmap = BitmapFactory
                                .decodeFile(file.getAbsolutePath());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView_001.setImageBitmap(bitmap);
                            }
                        });


                        Logger.debug("Mr_LlinLin","success " + file.getAbsoluteFile());
                    }

                    @Override
                    public void http_fail(int errorcode, String errorMessage) {
                        Logger.debug("Mr_LinLin" , "fail , " + errorcode + "错误详尽信息 : " + errorMessage);
                    }

                    @Override
                    public void http_progress(int progress) {

                    }
                });



    }
}
