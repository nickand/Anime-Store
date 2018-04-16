package test.android.testmerlin.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_apps.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.android.testmerlin.BaseFragment
import test.android.testmerlin.R
import test.android.testmerlin.adapters.AppsListRecyclerViewAdapter
import test.android.testmerlin.interfaces.OnClickActivityListener
import test.android.testmerlin.models.AnimeApiResponse
import test.android.testmerlin.models.AnimeAttributes
import test.android.testmerlin.models.AnimeData
import test.android.testmerlin.services.AnimeApiClient
import test.android.testmerlin.utils.App
import test.android.testmerlin.utils.Constants
import test.android.testmerlin.utils.Utils
import java.util.*

class AppsFragment : BaseFragment() {
    private var mAdapter: AppsListRecyclerViewAdapter? = null
    private var things: List<AnimeData> = ArrayList()
    private var mListener: OnClickActivityListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return initViews(container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        setErrorConnectionOrShowPosts()

        val mLayoutManager = GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
        animeList.layoutManager = mLayoutManager

        animeList.setHasFixedSize(true)
        mAdapter = mListener?.let { AppsListRecyclerViewAdapter(things, it, Constants.TYPE_FROM_APP_VIEW) }
        animeList.adapter = mAdapter
    }

    private fun setListeners() {
        animeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fab.isShown) {
                    fab.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show()
                }

                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun setErrorConnectionOrShowPosts() {
        if (things.isEmpty()) {
            if (Utils.isNetworkConnected()) {
                containerProgressIndicator.visibility = View.VISIBLE
                getPostsRedditFromApiResponse()
            } else {
                containerProgressIndicator.visibility = View.GONE
                containerNoInternetMessage.visibility = View.VISIBLE
            }
        } else {
            mAdapter = mListener?.let { AppsListRecyclerViewAdapter(things, it, Constants.TYPE_FROM_APP_VIEW) }
            animeList.adapter = mAdapter
        }
    }

    private fun initViews(container: ViewGroup?): View? {
        val rootView = container?.inflate(R.layout.fragment_apps)

        mListener!!.setTitleToolbar(App.getContext().getString(R.string.app_name))

        //fab.setOnClickListener(View.OnClickListener { openCategoriesActivity() })

        return rootView
    }

    /*private fun openCategoriesActivity() {
        val i = Intent(activity, CategoriesActivity::class)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(i)
    }*/

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnClickActivityListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnClickActivityListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun getPostsRedditFromApiResponse() {
        val apiService = AnimeApiClient.apiClient?.create(AnimeApiClient.AnimeApiInterface::class.java)
        val call = apiService?.animeList
        call?.enqueue(object : Callback<AnimeApiResponse> {
            override fun onResponse(call: Call<AnimeApiResponse>, response: Response<AnimeApiResponse>) {
                val apiResponse = response.body()

                val animeAttributes = ArrayList<AnimeAttributes>()
                for (i in 0 until apiResponse.data.size) {

                    Log.e(TAG, "TEST API: " + apiResponse.data)
                    animeAttributes.add(apiResponse.data[i]!!.attributes)
                }

                if (response.isSuccessful) {
                    containerProgressIndicator.visibility = View.GONE

                    mAdapter = mListener?.let { AppsListRecyclerViewAdapter(apiResponse.data, it, Constants.TYPE_FROM_APP_VIEW) }
                    animeList.adapter = mAdapter
                }
            }

            override fun onFailure(call: Call<AnimeApiResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    companion object {

        val CLASS_TAG = AppsFragment::class.simpleName

        fun newInstance(): Fragment {
            return AppsFragment()
        }
    }
}
