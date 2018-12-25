package com.sanjay.baselibrary.presentation;

import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Sanjay
 * BaseAdapter class for data binding
 */
public class BindingAdapterBase {

    /**
     * Method to set text to text view
     *
     * @param textView    view to apply text
     * @param stringResId string id
     */
    @BindingAdapter({"android:text"})
    public static void setText(TextView textView, @StringRes int stringResId) {
        if (stringResId != 0) {
            textView.setText(stringResId);
        }
    }

    /**
     * Method to load image to image view
     *
     * @param view      view to apply image
     * @param url       source of the image
     * @param thumbnail boolean value to check for thumbnail image
     */
    @BindingAdapter(value = {"url", "thumbnail"}, requireAll = false)
    public static void loadImage(ImageView view, @Nullable String url, boolean thumbnail) {
        if (!TextUtils.isEmpty(url) && URLUtil.isValidUrl(url)) {
            if (thumbnail) {
                Glide.with(view).load(url).thumbnail(Glide.with(view).load(url)).into(view);
            } else {
                Glide.with(view).load(url).into(view);
            }
        }
    }
}
