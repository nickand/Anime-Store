package test.android.testmerlin.adapters

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import test.android.testmerlin.R
import test.android.testmerlin.fragments.DetailFragment
import test.android.testmerlin.interfaces.OnClickActivityListener
import test.android.testmerlin.models.AnimeData
import test.android.testmerlin.utils.Constants


class AppsListRecyclerViewAdapter(items: List<AnimeData?>, listener: OnClickActivityListener, viewType: Int) : RecyclerView.Adapter<AppsListRecyclerViewAdapter.ViewHolder>() {

    private var mValues: List<AnimeData?> = items
    private var mListener: OnClickActivityListener? = listener
    private var mContext: Context? = null
    private var mViewType: Int = viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_scalable, parent, false)
        mContext = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]

        /*holder.mAppName.setText(holder.mItem.getAnimeAttributes().getCanonicalTitle());
        holder.mAppCompany.setText(holder.mItem.getAnimeAttributes().getAverageRating());
        holder.mAppCategory.setText(holder.mItem.getAnimeAttributes().getStartDate());*/

        if (mViewType == Constants.TYPE_FROM_APP_VIEW) {
            val imagePipeline = Fresco.getImagePipeline()

            val uri: Uri = Uri.parse(holder.mItem!!.attributes.posterImage?.medium)
            val imageRequest: ImageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build()
            imagePipeline.prefetchToDiskCache(imageRequest, null)

            holder.mAppImage.setImageURI(uri, this)

        } else {
            holder.mAppImage.visibility = View.GONE
            /*   holder.mAppCategory.setVisibility(View.GONE);
            holder.mAppCompany.setVisibility(View.GONE);
            holder.mAppName.setTextColor(Color.parseColor("#009452"));*/
        }

        holder.mView.setOnClickListener {
            if (null != mListener) {
                when (mViewType) {
                    Constants.TYPE_FROM_APP_VIEW -> {
                        mListener!!.navigateTo(DetailFragment.newInstanceWithArguments(holder.mItem))
                    }
                    Constants.TYPE_FROM_BROWSER -> {
                    }
                }

                //mListener.openWebView((Activity) mContext, holder.mItem.attributes.);
            }
        }


    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mAppImage: SimpleDraweeView = mView.findViewById<View>(R.id.thumbnail) as SimpleDraweeView

        var mItem: AnimeData? = null

    }
}
