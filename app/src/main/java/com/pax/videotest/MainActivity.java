package com.pax.videotest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import fm.jiecao.jcvideoplayer_lib.VideoInfo;


public class MainActivity extends AppCompatActivity {
    public VideoInfo[] videoInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean initResult = initVideoInfo();
                if (initResult) {
                    JCVideoPlayerStandard.startLoopPlaying(MainActivity.this, videoInfos);
                }
            }
        });
    }

    private boolean initVideoInfo() {
        String videoDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "exhibition" + File.separator + "video" + File.separator;
        File videoDir = new File(videoDirPath);
        if (!videoDir.exists()) {
            videoEmptyToast();
            return false;
        }

        File[] videoFiles = videoDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String fileName = pathname.getAbsolutePath().toLowerCase();
                return fileName.endsWith(".mp4") ||
                        fileName.endsWith(".rm") ||
                        fileName.endsWith(".rmvb") ||
                        fileName.endsWith(".mpg") ||
                        fileName.endsWith(".mpeg") ||
                        fileName.endsWith(".3gp") ||
                        fileName.endsWith(".m4v") ||
                        fileName.endsWith(".avi") ||
                        fileName.endsWith(".mkv") ||
                        fileName.endsWith(".flv");
            }
        });

        if (videoFiles == null || videoFiles.length == 0) {
            videoEmptyToast();
            return false;
        }

        videoInfos = new VideoInfo[videoFiles.length];
        String tempPath;
        String tempName;
        int nameStartIndex;
        int nameEndIndex;
        for (int i = 0, j = videoFiles.length; i < j; i++) {
            tempPath = videoFiles[i].getAbsolutePath();
            nameStartIndex = tempPath.lastIndexOf(File.separator)+1;
            nameEndIndex = tempPath.lastIndexOf(".");
            tempName = tempPath.substring(nameStartIndex, nameEndIndex);
            VideoInfo videoInfo = new VideoInfo(tempName, tempPath);
            videoInfos[i] = videoInfo;
        }
        return true;
    }

    private void videoEmptyToast() {
        Toast.makeText(this, "Please put video files to the directory of /exhibition/video/",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
