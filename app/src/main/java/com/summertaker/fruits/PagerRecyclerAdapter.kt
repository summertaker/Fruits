package com.summertaker.fruits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PagerRecyclerAdapter(private val list: ArrayList<Fruit>) :
    RecyclerView.Adapter<PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_fruit, parent, false)
        )

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}

class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivPicture: ImageView = itemView.findViewById(R.id.ivPicture)
    private val tvName: TextView = itemView.findViewById(R.id.tvName)
    private val tvFurigana: TextView = itemView.findViewById(R.id.tvFurigana)
    private val tvGroupTeam: TextView = itemView.findViewById(R.id.tvGroupTeam)
    private val tvBirthdayAge: TextView = itemView.findViewById(R.id.tvBirthdayAge)

    fun bind(fruit: Fruit, position: Int) {
        //itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, bgColor))
        //Glide.with(context).load(fruit.image).placeholder(R.drawable.placeholder).into(kbvLocation);
        Picasso.get().load(fruit.image).into(ivPicture);
        tvName.text = fruit.name
        tvFurigana.text = fruit.furigana
        tvGroupTeam.text = fruit.group + " " + fruit.team
        tvBirthdayAge.text = fruit.birthday + " " + fruit.age
    }
}
