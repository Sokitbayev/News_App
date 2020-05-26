package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MoviesAdapter(val news: List<Source>, private val context: Context) :
    RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(context, NewsDetail::class.java).also {
                it.putExtra(
                    "poster",
                    image_url + news[position].poster_path
                )
                it.putExtra("title", news[position].description)
                it.putExtra("overview", news[position].overview)
            }
            context.startActivity(intent)
        }
        return holder.bind(news[position])
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.movie_photo)
    private val title: TextView = itemView.findViewById(R.id.movie_title)
    private val overview: TextView = itemView.findViewById(R.id.movie_overview)

    @SuppressLint("SetTextI18n")
    fun bind(movie: Source) {
        Glide.with(itemView.context).load(image_url + movie.poster_path)
            .into(photo)
        title.text = "Title: " + movie.title
        overview.text = "Overview: " + movie.overview

    }
}