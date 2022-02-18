package com.example.lesson2android3.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.lesson2android3.App;
import com.example.lesson2android3.model.Hit;
import com.example.lesson2android3.model.ImageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaBayRepository {

    static PixaBayRepository pixaBayRepository;

    public static PixaBayRepository getInstance() {
        if (pixaBayRepository == null) {
            return new PixaBayRepository();
        }
        return pixaBayRepository;
    }

    public MutableLiveData<List<Hit>> getImages(String word) {
        MutableLiveData<List<Hit>> listImages = new MutableLiveData<>();

        App.api.getImageBySearch(word).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    listImages.postValue(response.body().getHits());
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.e("ololo", t.getMessage());
            }
        });
        return listImages;
    }
}
