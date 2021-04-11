package com.anubhav.ytaudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import androidx.recyclerview.widget.RecyclerView;


class playerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private static String GOOGLE_YOUTUBE_API = "AIzaSyAVKQRWe1QalX-mD0zgVVf0wLZ36dcHqjM";
    private model youtubeDataModel = null;
    TextView textViewName;
    public static final String VIDEO_ID = "c2UNv38V6y4";
    private YouTubePlayerView mYoutubePlayerView = null;
    private YouTubePlayer mYoutubePlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        youtubeDataModel = getIntent().getParcelableExtra(model.class.toString());
        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        mYoutubePlayerView.initialize(GOOGLE_YOUTUBE_API, this);
        textViewName = (TextView) findViewById(R.id.textViewName);

    }
    public void back_btn_pressed(View view) {
        finish();
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        if (!wasRestored) {
            youTubePlayer.cueVideo(youtubeDataModel.getVideo_id());
        }
        mYoutubePlayer = youTubePlayer;
    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
