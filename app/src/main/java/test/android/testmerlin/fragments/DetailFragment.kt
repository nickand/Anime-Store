package test.android.testmerlin.fragments

import android.content.Context
import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_detail_app.*
import test.android.testmerlin.BaseFragment
import test.android.testmerlin.R
import test.android.testmerlin.interfaces.OnClickActivityListener
import test.android.testmerlin.models.AnimeData

class DetailFragment : BaseFragment(), View.OnClickListener {

    private var data: AnimeData? = null
    private var isShowing = false

    private var mListener: OnClickActivityListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            Log.d(CLASS_TAG, " getting args")

            data = arguments?.getParcelable(ARG_PARAM1)

            //Log.d(CLASS_TAG, data?.attributes?.coverImage?.large)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return initViews(container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setListeners()
    }

    private fun setListeners() {
        mListener!!.setTitleToolbar(data!!.attributes.canonicalTitle)
        btnGoToWeb.setOnClickListener(this)
        tvShowMore.setOnClickListener(this)
    }

    private fun initViews(container: ViewGroup?): View? {

        return container?.inflate(R.layout.fragment_detail_app)
    }

    private fun initViews() {
        btnGoToWeb.visibility = View.VISIBLE
        tvShowMore.visibility = View.VISIBLE
        tvTitleDescription.visibility = View.VISIBLE

        tvDetailAppName.text = data!!.attributes.canonicalTitle
        tvDetailDescription.text = data!!.attributes.synopsis

        tvDetailAppMadeBy.text = data!!.attributes.endDate

        val imagePipeline = Fresco.getImagePipeline()

        val uri: Uri = parse(data!!.attributes.posterImage?.medium)
        val path = getCoverImage(data!!.attributes.coverImage?.large)
        val uriCover: Uri = parse(path)
        val imageRequest: ImageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build()
        val imageCoverRequest: ImageRequest = ImageRequestBuilder.newBuilderWithSource(uriCover).build()
        imagePipeline.prefetchToDiskCache(imageRequest, null)
        imagePipeline.prefetchToDiskCache(imageCoverRequest, null)

        ivDetailIconApp.setImageURI(uri, this)
        ivCoverApp?.setImageURI(uriCover, this)

    }

    private fun getCoverImage(coverImage: String?) = when (coverImage) {
        is String -> coverImage
        else -> "res:/" + R.drawable.img_me
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickActivityListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnClickActivityListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnGoToWeb -> mListener!!.openWebView(activity, data!!.attributes.posterImage.toString())
            R.id.tvShowMore -> setShowMoreOrLess()
        }
    }

    private fun setShowMoreOrLess() {
        if (!isShowing) {
            isShowing = true
            tvDetailDescription.maxLines = Integer.MAX_VALUE
            tvShowMore.setText(R.string.show_less)
        } else {
            isShowing = false
            tvDetailDescription.maxLines = 4
            tvShowMore.setText(R.string.read_more)
        }
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val CLASS_TAG = DetailFragment::class.java.simpleName

        fun newInstanceWithArguments(e: AnimeData?): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM1, e)
            fragment.arguments = args
            return fragment
        }
    }
}
