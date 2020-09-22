package com.example.runningtrackerapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.runningtrackerapp.db.RunningDatabase
import com.example.runningtrackerapp.other.Constants.KEY_FIRST_TOGGLE
import com.example.runningtrackerapp.other.Constants.KEY_NAME
import com.example.runningtrackerapp.other.Constants.KEY_WEIGHT
import com.example.runningtrackerapp.other.Constants.RUNNING_DATABASE_NAME
import com.example.runningtrackerapp.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    fun providerRoomDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun providerSharedPreferances(@ApplicationContext app: Context)=app.getSharedPreferences(
        SHARED_PREFERENCES_NAME,MODE_PRIVATE)

    @Singleton
    @Provides
    fun providerName (sharedPreferences: SharedPreferences)=sharedPreferences.getString(KEY_NAME,"") ?:""
    @Singleton
    @Provides
    fun providerWeight (sharedPreferences: SharedPreferences)=sharedPreferences.getFloat(KEY_WEIGHT,80f)
    @Singleton
    @Provides
    fun providerFirstTimeToggle (sharedPreferences: SharedPreferences)=sharedPreferences.getBoolean(
        KEY_FIRST_TOGGLE,true)

}