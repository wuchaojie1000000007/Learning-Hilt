package com.example.learninghilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

class SomeClass
@Inject
constructor(
    private val someInterfaceImpl: SomeInterface,
    private val gson: Gson
) {
    fun doAThing() = "Look I got: ${someInterfaceImpl.getAThing()}"
}

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {

    override fun getAThing() = "A thing"
}

interface SomeInterface {
    fun getAThing(): String
}


@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule {

    @Binds
    abstract fun bindSomeDependency(someInterfaceImpl: SomeInterfaceImpl): SomeInterface
}

@InstallIn(ActivityComponent::class)
@Module
object MySecondModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}