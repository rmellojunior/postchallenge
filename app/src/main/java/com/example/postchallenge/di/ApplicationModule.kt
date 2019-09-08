package com.example.postchallenge.di

import android.content.Context
import com.example.postchallenge.PostApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(
  private val postApplication: PostApplication) {

  @ApplicationScope
  @Provides
  fun provideMezuApplication(): PostApplication {
    return postApplication
  }

  @ApplicationScope
  @Provides
  fun provideApplicationContext(application: PostApplication): Context {
    return application.applicationContext
  }

}