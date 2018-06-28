package com.example.parmeshmahore.movielistdemo.activity

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.parmeshmahore.movielistdemo.R
import com.example.parmeshmahore.movielistdemo.adapter.ListAdapter
import com.example.parmeshmahore.movielistdemo.model.MovieResponse
import com.example.parmeshmahore.movielistdemo.rest.ApiClient
import com.example.parmeshmahore.movielistdemo.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import com.example.parmeshmahore.movielistdemo.model.Movie
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import android.text.method.TextKeyListener.clear
import android.widget.Toast
import io.reactivex.observers.DisposableSingleObserver
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {

    val API_KEY = "047fc3427900863d21313cb50ca33b14"
    var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val all_permission = 1
        val permissions = listOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(!hasPermission(this, permissions)){
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), all_permission)
        }

        //getMovieList()
        getMovieListByRxJava()

        val countryListObservable = Observable.just("India", "Germany",
                "Austria", "England", "France", "Australia", "Auckland")
        val countryListObserver = getAnimalsObserver()

        countryListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter( {x -> x.startsWith("A") })
                .map { x -> x.toUpperCase() }
                .subscribeWith(countryListObserver)
    }

    private fun getAnimalsObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("MAIN_ACTIVITY", "onSubscribe")
                disposable = d
            }

            override fun onNext(s: String) {
                Log.d("MAIN_ACTIVITY", "ON onNext:$s")
            }

            override fun onError(e: Throwable) {
                Log.d("MAIN_ACTIVITY", "ON onError")
            }

            override fun onComplete() {
                Log.d("MAIN_ACTIVITY", "ON onComplete")
            }
        }
    }

    private fun getMovieList() {
        val apiInterface = ApiClient().getClient()!!.create(ApiInterface::class.java)
        val call = apiInterface.getTopMoviesList(API_KEY)
        /*call.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                updateUIWithMovieList(response.body()!!.results)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }
        })*/
    }

    private fun getMovieListByRxJava() {
        val apiInterface = ApiClient().getClient()!!.create(ApiInterface::class.java)
        apiInterface.getTopMoviesList(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>() {
                    override fun onSuccess(movieResponse: MovieResponse) {
                        updateUIWithMovieList(movieResponse.results)
                        Toast.makeText(applicationContext, "Hey, I made first RxJava API Call", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }

    private fun updateUIWithMovieList(list : List<Movie>?) {
        //val list : List<String> = Arrays.asList("India", "Austria", "India", "Austria", "India", "Austria")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = ListAdapter(list, applicationContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable!!.dispose()
    }

    fun hasPermission(context: Context, permissions: List<String>) : Boolean{

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }
}