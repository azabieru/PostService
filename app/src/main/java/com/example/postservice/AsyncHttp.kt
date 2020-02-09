package com.example.postservice

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.properties.Delegates

class AsynkHttp : AsyncTask<String, Int, Boolean> {
    var urlConnection: HttpURLConnection by Delegates.notNull<HttpURLConnection>()
    var flg:Boolean = false

    var name:String =""
    var value:Double =0.0

    constructor(name:String,value:Double){
        this.name = name
        this.value = value
    }


    override fun doInBackground(vararg content: String?): Boolean {
        var urlinput:String = "http://10.206.0.85/upload/post.php"
        try {
            var url: URL = URL(urlinput)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.setRequestMethod("POST")
            urlConnection.setDoOutput(true)

            var postDataSample:String = "name="+name+"&text"+value
            var out: OutputStream = urlConnection.getOutputStream()
            out.write(postDataSample.toByteArray())
            out.flush()
            out.close()
            Log.d("test",postDataSample)
            urlConnection.getInputStream()
            flg = true
        }catch (e: MalformedURLException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }



        return flg
    }

}