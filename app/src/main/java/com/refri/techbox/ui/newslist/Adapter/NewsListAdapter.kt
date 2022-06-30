package com.refri.techbox.ui.newslist.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.refri.techbox.data.response.Article
import com.refri.techbox.databinding.ItemNewsListBinding
import com.refri.techbox.ui.splashscreen.SplashScreenActivity.Companion.startActivity

class NewsListAdapter(private val itemClick: (Article) -> Unit) :
    RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    private var items: MutableList<Article> = mutableListOf()

    fun setItems(items: List<Article>){
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Article>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bindView(items[position])

    }

    override fun getItemCount() : Int{
        return Math.min(items.size, 50)
    }

    class NewsListViewHolder(private val binding: ItemNewsListBinding,  val itemClick: (Article) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Article) {
            with(item) {
                binding.btnReadMore.setOnClickListener { itemClick(this) }
                binding.ivShowImage.load(item.image){
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                binding.tvTitleArticle.text = item.title
                binding.tvSummary.text = item.description.toString().take(70) + " . . ."
                binding.tvAuthor.text = "- " + item.source!!.name

            }
        }

    }
}