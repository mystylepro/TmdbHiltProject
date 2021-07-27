package com.app.daggerhiltsapplicationtmdb.di.module

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.app.daggerhiltsapplicationtmdb.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object HomeActivityModule
{
    @ActivityScoped
    @Provides
    fun provideNavController(activity: Activity) : NavController
    {
        return activity.findNavController(R.id.nav_host_fragment)
    }

    @ActivityScoped
    @Provides
    fun provideAppBarConfiguration() : AppBarConfiguration
    {
        return AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        ))
    }
}