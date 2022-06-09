package com.dicoding.picodiploma.besti.view.result

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.view.home.ui.detail.DetailActivity
import com.dicoding.picodiploma.besti.view.home.ui.detail.DetailSaranActivity
import com.dicoding.picodiploma.besti.view.home.ui.home.ListBeritaAdapter

class ListSaranAdapter(private val listSaran: ArrayList<Saran>): RecyclerView.Adapter<ListSaranAdapter.ListViewHolder>() {
    class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_saran)
        var tvName: TextView = itemView.findViewById(R.id.tv_name_saran)
        fun bind(saran: Saran) {
            Glide.with(itemView.context)
                .load(saran.photo)
                .circleCrop()
                .into(imgPhoto)
            tvName.text = saran.name

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailSaranActivity::class.java)
                intent.putExtra("Saran", saran)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(imgPhoto, "profile"),
                        Pair(tvName, "name")
                    )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }

    }

    private lateinit var onItemClickCallback: ListBeritaAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: ListBeritaAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_saran, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listSaran[position])
    }

    override fun getItemCount(): Int {
        return listSaran.size
    }
}