package com.example.postchallenge.di

import com.example.postchallenge.PostApplication
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.base.BaseActivity
import com.example.postchallenge.ui.base.BaseFragment
import com.example.postchallenge.ui.postlist.PostListPresenter
import dagger.Component

@ApplicationScope
@Component(modules = [
  (MainInjectorModule::class), (ApplicationModule::class),
  (NetworkModule::class), (SchedulerModule::class), (SerializationModule::class)
])
interface MainInjectorComponent {

  fun inject(application: PostApplication)

  fun inject(activity: BaseActivity)

  fun inject(fragment: BaseFragment)

  fun inject(presenter: PostListPresenter)

  fun inject(service: PostService)

}