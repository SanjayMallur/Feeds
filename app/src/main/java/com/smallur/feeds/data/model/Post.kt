package com.smallur.feeds.data.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.Parcel
import com.smallur.feeds.BR
import com.sanjay.baselibrary.app.helper.ParcelableBase
import com.sanjay.baselibrary.app.helper.parcelableCreator

/**
 * Created by Sanjay
 *
 * Class which provides a model for post.
 * @constructor sets all the properties of the post
 * @param name name of the author of the post
 * @param body description of the post
 * @param url url for the image
 * @param thumbNailUrl image source of the author
 * @param date date of the post created
 * @param likes total likes of the post
 */

data class Post constructor(val name: String, val body: String, val url: String, val thumbNailUrl: String, val date: String, var likes: Int): BaseObservable(), ParcelableBase {

    constructor(parcel: Parcel): this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt()){
        this.like = parcel.readInt()
    }

    var like: Int? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.like)
        }
    override fun writeToParcel(dest: Parcel, flags: Int)= with(dest)  {
        writeString(name)
        writeString(body)
        writeString(url)
        writeString(thumbNailUrl)
        writeString(date)
        writeInt(likes)
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::Post)
    }
}