package com.summertaker.fruits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_fruit.view.*

class FruitAdapter(private val list: ArrayList<Fruit>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_fruit, container, false)

        Glide.with(container.context).load(list[position].image).placeholder(R.drawable.placeholder).into(view.ivFruit);

        view.tvFruitName.text = list[position].name
        view.tvFruitFurigana.text = list[position].furigana
        view.tvFruitGroupTeam.text = list[position].group + " " + list[position].team

        val source = list[position].birthday
        val result = source.split("-").toTypedArray()
        val birthday = result[0] + "年 " + result[1] + "月 " + result[2] + "日"
        val age = " (" + list[position].age.toString() + "歳)"
        view.tvFruitBirthday.text = birthday + age

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