package com.example.learninghilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
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

        //println(someClass.doAThing())
    }
}

class SomeClass
@Inject
constructor(
    private val gson: Gson
    //private val someInterfaceImpl: SomeInterface
) {
    //fun doAThing() = "Look I got: ${someInterfaceImpl.getAThing()}"
}

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {

    override fun getAThing() = "A thing"
}

interface SomeInterface {
    fun getAThing(): String
}