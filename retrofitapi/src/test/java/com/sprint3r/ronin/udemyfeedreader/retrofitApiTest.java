package com.sprint3r.ronin.udemyfeedreader;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static org.junit.Assert.assertEquals;

public class retrofitApiTest {

    private retrofitApi retrofitApi;
    private IUdemyCourses client;
    private CoursesDetail feedUdemy;


    @Test
    public void testCallNextPageUrl() throws InterruptedException {
        final CountDownLatch ying = new CountDownLatch(1);
        retrofitApi = new retrofitApi();
        client = retrofitApi.getData();

        final Call<CoursesDetail> call =  client.getFeed();

        call.enqueue(new Callback<CoursesDetail>() {
            @Override
            public void onResponse(Response<CoursesDetail> response) {

                feedUdemy = response.body();
                ying.countDown();
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
        ying.await();
        assertEquals("https://www.udemy.com/api-2.0/courses?page=2", feedUdemy.next);
    }

    @Test
    public void testCallTitleResults() throws InterruptedException {
        final CountDownLatch ying = new CountDownLatch(1);
        retrofitApi = new retrofitApi();
        client = retrofitApi.getData();

        final Call<CoursesDetail> call =  client.getFeed();

        call.enqueue(new Callback<CoursesDetail>() {
            @Override
            public void onResponse(Response<CoursesDetail> response) {

                feedUdemy = response.body();
                ying.countDown();
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
        ying.await();
        String expect = "Learn and Understand AngularJS";
        assertEquals(expect, feedUdemy.results.get(0).getAsJsonObject().get("title").getAsString());
    }

//    @Test
//    public void testCallDetailAllResults(){
//        String actual = feedUdemy.results.get(0).getAsJsonObject().toString();
////        JsonObject actual = feedUdemy.results.get(0).getAsJsonObject();
//        String expect = "{\"_class\": \"course\",\"id\": 289230}";
//        assertEquals(expect, actual);
//    }
}
