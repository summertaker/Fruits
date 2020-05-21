package com.summertaker.fruits

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_fruit.view.*


class FruitAdapter(private val list: ArrayList<Fruit>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_fruit, container, false)

        Glide.with(container.context).load(list[position].image).placeholder(R.drawable.placeholder)
            .into(view.ivFruit);

        view.tvFruitName.text = list[position].name
        view.tvFruitFurigana.text = list[position].furigana
        view.tvFruitGroupTeam.text = list[position].group + " " + list[position].team
        view.tvFruitBirthday.text = list[position].birthday + " " + list[position].age

        if (list[position].twitter.isEmpty()) {
            view.ivTwitter.visibility = View.GONE
        } else {
            view.ivTwitter.visibility = View.VISIBLE
            view.ivTwitter.setOnClickListener {
                container.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(list[position].twitter))
                )
            }
        }
        if (list[position].instagram.isEmpty()) {
            view.ivInstagram.visibility = View.GONE
        } else {
            view.ivInstagram.visibility = View.VISIBLE
            view.ivInstagram.setOnClickListener {
                container.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(list[position].instagram))
                )
            }
        }
        if (list[position].wiki.isEmpty()) {
            view.ivWiki.visibility = View.GONE
        } else {
            view.ivWiki.visibility = View.VISIBLE
            view.ivWiki.setOnClickListener {
                container.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(list[position].wiki))
                )
            }
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }
}