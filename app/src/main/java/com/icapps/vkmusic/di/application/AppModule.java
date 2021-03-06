package com.icapps.vkmusic.di.application;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.icapps.vkmusic.model.albumart.AlbumArtProvider;
import com.icapps.vkmusic.model.albumart.BingAlbumArtProvider;
import com.vk.sdk.api.model.VKApiAudio;
import com.vk.sdk.api.model.VkAudioArray;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.paperdb.Paper;

/**
 * Created by maartenvangiel on 13/09/16.
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    AlbumArtProvider provideAlbumArtProvider() {
        return new BingAlbumArtProvider();
    }

    @Provides
    @Singleton
    VkAudioArray providePlaybackQueue() {
        return Paper.book().read("playbackQueue", new VkAudioArray());
    }

    @Provides
    @Singleton
    ObservableField<VKApiAudio> provideCurrentAudio() {
        return Paper.book().read("currentAudio", new ObservableField<>());
    }

    @Provides
    @Singleton
    @Named("shuffle")
    ObservableBoolean provideShuffleSetting() {
        return Paper.book().read("shuffle", new ObservableBoolean());
    }

    @Provides
    @Singleton
    @Named("repeat")
    ObservableBoolean provideRepeatSetting() {
        return Paper.book().read("repeat", new ObservableBoolean());
    }

    @Provides
    @Singleton
    ObservableField<Bitmap> provideCurrentAlbumArt() {
        return new ObservableField<>();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }
}
