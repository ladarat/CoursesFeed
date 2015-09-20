package com.sprint3r.ronin.udemyfeedreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_udemy_feed_reader);

        textCourse = (TextView) findViewById(R.id.label_output_course);

        IUdemyCourses client;
        retrofitApi retrofit;

        retrofit = new retrofitApi();
        client = retrofit.getData();

        final Call<CoursesDetail> call =  client.getFeed();

        call.enqueue(new Callback<CoursesDetail>() {
            @Override
            public void onResponse(Response<CoursesDetail> response) {
                CoursesDetail feedUdemy = response.body();
                textCourse.setText(feedUdemy.next);
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });


//         CreateCourses();
    }


//    private CoursesDetail feedUdemy;
//    private retrofitApi retrofit;
//    public CoursesDetail CreateCourses(){
//            retrofit = new retrofitApi();
//
//
//        feedUdemy = retrofit.getData();
//        feedUdemy.getCourses();
//        return feedUdemy;
//    }
}
