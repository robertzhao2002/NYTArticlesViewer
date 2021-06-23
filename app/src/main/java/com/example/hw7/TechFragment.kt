package com.example.hw7

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private val client = OkHttpClient()
private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
private val resultsJsonAdapter : JsonAdapter<Result> = moshi.adapter(Result::class.java)

/**
 * A simple [Fragment] subclass.
 * Use the [TechFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TechFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var recyclerView : RecyclerView
    lateinit var mAdapter : RecyclerView.Adapter<*>
    lateinit var layoutManager : RecyclerView.LayoutManager
    private var key: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            key = it.getString("key")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var requestGet = Request.Builder().url("https://api.nytimes.com/svc/topstories/v2/technology.json?api-key="+key).build()

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        client.newCall(requestGet).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) throw IOException("Unexpected code $response")
                    var result: Result? = resultsJsonAdapter.fromJson(response.body!!.string())
                    val articlesList: List<Article> = result!!.results
                    Log.d("result", articlesList.toString())
                    var articles = arrayListOf<Article>()
                    articles.addAll(articlesList)
                    activity!!.runOnUiThread(java.lang.Runnable {
                        recyclerView = root.findViewById(R.id.homearticles)
                        mAdapter = ArticleAdapter(articles)
                        recyclerView.adapter = mAdapter
                        layoutManager = LinearLayoutManager(container!!.context)
                        recyclerView.layoutManager = layoutManager
                    })
                }
            }
        })
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TechFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
                TechFragment().apply {
                    arguments = Bundle().apply {
                        putString("key", param1)
                    }
                }
    }
}