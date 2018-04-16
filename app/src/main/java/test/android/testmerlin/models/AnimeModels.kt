package test.android.testmerlin.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by nicolas.g on 24/11/2017.
 */
data class AnimeApiResponse(var data: List<AnimeData?>)

//region AnimeData POJO
data class AnimeData(
    var id: Int,
    var type: String,
    var attributes: AnimeAttributes): Parcelable {
    constructor(source: Parcel): this(
        source.readInt(),
        source.readString(),
        source.readParcelable<AnimeAttributes>(AnimeAttributes::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(type)
        writeParcelable(attributes, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AnimeData> = object: Parcelable.Creator<AnimeData> {
            override fun createFromParcel(source: Parcel): AnimeData = AnimeData(source)
            override fun newArray(size: Int): Array<AnimeData?> = arrayOfNulls(size)
        }
    }
}
//endregion

//region AnimePosterImage POJO
data class AnimePosterImage(var medium: String)
//endregion

//region AnimeCoverImage POJO
data class AnimeCoverImage(var large: String)
//endregion

//region AnimeAttributes POJO
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
//endregion