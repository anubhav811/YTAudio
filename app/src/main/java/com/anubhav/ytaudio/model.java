package com.anubhav.ytaudio;

import android.os.Parcel;
import android.os.Parcelable;

public class model implements Parcelable {
        private String title = "";
        private String channelTitle = "";
        private String thumbnail = "";
        private String video_id = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(thumbnail);
        dest.writeString(video_id);
        dest.writeString(channelTitle);
    }

    public model() {
        super();
    }


    protected model(Parcel in) {
        this();
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.title = in.readString();
        this.channelTitle=in.readString();
        this.thumbnail = in.readString();
        this.video_id = in.readString();

    }

    public static final Creator<model> CREATOR = new Creator<model>() {
        @Override
        public model createFromParcel(Parcel in) {
            return new model(in);
        }

        @Override
        public model[] newArray(int size) {
            return new model[size];
        }
    };
}