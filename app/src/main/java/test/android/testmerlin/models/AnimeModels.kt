package test.android.testmerlin.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by nicolas.g on 24/11/2017.
 */
data class AnimeApiResponse(var data: List<AnimeData?>)

//region AnimeData POJO
data class AnimeData(var attributes: AnimeAttributes) : Parcelable {
    constructor(parcel: Parcel) : this(readParcelable())

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<AnimeData> {
        override fun createFromParcel(parcel: Parcel): AnimeData {
            return AnimeData(parcel)
        }

        override fun newArray(size: Int): Array<AnimeData?> {
            return arrayOfNulls(size)
        }
    }
}
//endregion

private fun readParcelable(): AnimeAttributes {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

data class AnimePosterImage(var medium: String)
data class AnimeCoverImage(var large: String)

data class AnimeAttributes(
        var createdAt: String,
        var synopsis: String,
        var canonicalTitle: String,
        var averageRating: String,
        var startDate: String,
        var ageRatingGuide: String,
        var endDate: String,
        var coverImage: AnimeCoverImage?,
        var posterImage: AnimePosterImage?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("coverImage"),
            TODO("posterImage"))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeString(synopsis)
        parcel.writeString(canonicalTitle)
        parcel.writeString(averageRating)
        parcel.writeString(startDate)
        parcel.writeString(ageRatingGuide)
        parcel.writeString(endDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimeAttributes> {
        override fun createFromParcel(parcel: Parcel): AnimeAttributes {
            return AnimeAttributes(parcel)
        }

        override fun newArray(size: Int): Array<AnimeAttributes?> {
            return arrayOfNulls(size)
        }
    }
}