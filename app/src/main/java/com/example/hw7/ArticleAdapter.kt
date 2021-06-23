package com.example.hw7

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext


class ArticleAdapter(private val articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val titleview = itemView.findViewById<TextView>(R.id.article_name)
        val sectionview = itemView.findViewById<TextView>(R.id.section_name)
        val dateview = itemView.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val articleview = inflater.inflate(R.layout.articlerow, parent, false)
        return ViewHolder(articleview)
    }

    override fun onBindViewHolder(viewHolder: ArticleAdapter.ViewHolder, position: Int) {
        val article: Article = articles[position]
        val title = viewHolder.titleview
        val section = viewHolder.sectionview
        val date = viewHolder.dateview
        title.text = article.title
        section.text = article.section.toUpperCase()
        date.text = article.date.substring(0, 10)
        viewHolder.itemView.setOnClickListener {
            val context = viewHolder.itemView.context
            val webIntent: Intent = Uri.parse(article.url).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            context.startActivity(webIntent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}