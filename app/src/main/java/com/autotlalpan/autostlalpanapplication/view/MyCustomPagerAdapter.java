package com.autotlalpan.autostlalpanapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.autotlalpan.autostlalpanapplication.R;
import com.bumptech.glide.Glide;

class MyCustomPagerAdapter extends PagerAdapter {

    Context context;
    String photos[];
    LayoutInflater layoutInflater;

    public MyCustomPagerAdapter(Context context, String photos[]){
        this.context = context;
        this.photos = photos;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photos.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.car_view, container, false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        Glide.with(context).load(photos[position]).into(imageView);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
