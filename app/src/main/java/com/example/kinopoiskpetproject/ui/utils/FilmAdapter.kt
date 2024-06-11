package com.example.kinopoiskpetproject.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.kinopoiskpetproject.R
import com.example.kinopoiskpetproject.model.Film
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.Shimmer.AlphaHighlightBuilder
import com.facebook.shimmer.ShimmerDrawable


class FilmAdapter(val filmList:List<Film>, val click:OnItemClick): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView
        val textView: TextView
        val textViewSecond: TextView

        init {
            imageView = itemView.findViewById(R.id.imageItemFilm)
            textView = itemView.findViewById(R.id.textItemFilm)
            textViewSecond = itemView.findViewById(R.id.previewMessage)

            itemView.setOnClickListener {
                if (click != null) {
                    val pos = getAdapterPosition()
                    if (pos != RecyclerView.NO_POSITION) {
                        click.onItemClick(pos)
                    }
                }
            }
            val shimmer = AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()
            val shimmerDrawable = ShimmerDrawable()
            shimmerDrawable.setShimmer(shimmer)
        }
        fun getShimmer():ShimmerDrawable{
            val shimmer = AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()
            val shimmerDrawable = ShimmerDrawable()
            shimmerDrawable.setShimmer(shimmer)
            return shimmerDrawable
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlist,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewSecond.text = filmList[position].year
        holder.textView.text = filmList[position].nameRu
        Glide.with(holder.imageView.context).load(filmList.get(position).posterUrlPreview).placeholder(holder.getShimmer()).diskCacheStrategy(
            DiskCacheStrategy.ALL).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return  filmList.size
    }
}