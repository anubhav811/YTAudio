package com.anubhav.ytaudio;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class searchPage extends AppCompatActivity {
    private static String GOOGLE_YOUTUBE_API_KEY = "AIzaSyAVKQRWe1QalX-mD0zgVVf0wLZ36dcHqjM";
    SearchView searchView;

    private RecyclerView mList_videos = null;
    private Adapter adapter = null;
    private ArrayList<model> mListData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchView searchView= (SearchView)findViewById(R.id.search_query);
    }

    private void initList(ArrayList<model> mListData){
        mList_videos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, mListData, new OnItemClickListener() {
            @Override
            public void onItemClick(model item) {
                model youtubeDataModel = item;
                Intent intent = new Intent(searchPage.this, playerActivity.class);
                intent.putExtra(model.class.toString(), youtubeDataModel);
                startActivity(intent);


        }


        });
        mList_videos.setAdapter(adapter);

    }
    public class RequestYoutubeAPI extends AsyncTask<Void, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String GET_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&&q="+ search_query +"order=date&channelId=" + "&maxResults=20&key=" + GOOGLE_YOUTUBE_API_KEY + "";
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(GET_URL);
            Log.e("URL", GET_URL);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String json = EntityUtils.toString(httpEntity);
                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    mListData = parseVideoListFromResponse(jsonObject);
                    initList(mListData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<model> parseVideoListFromResponse(JSONObject jsonObject) {
        ArrayList<model> mList = new ArrayList<>();

        if (jsonObject.has("items")) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    if (json.has("id")) {
                        JSONObject jsonID = json.getJSONObject("id");
                        String video_id = "";
                        if (jsonID.has("videoId")) {
                            video_id = jsonID.getString("videoId");
                        }
                        if (jsonID.has("kind")) {
                            if (jsonID.getString("kind").equals("youtube#video")) {
                                model youtubeObject = new model();
                                JSONObject jsonSnippet = json.getJSONObject("snippet");
                                String title = jsonSnippet.getString("title");
                                String channel = jsonSnippet.getString("channelTitle");
                                String thumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");

                                youtubeObject.setTitle(title);
                                youtubeObject.setChannelTitle(channel);
                                youtubeObject.setThumbnail(thumbnail);
                                youtubeObject.setVideo_id(video_id);
                                mList.add(youtubeObject);

                            }
                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return mList;

    }


}