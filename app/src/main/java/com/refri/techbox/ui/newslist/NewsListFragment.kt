package com.refri.techbox.ui.newslist
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.refri.techbox.R
import com.refri.techbox.base.BaseFragment
import com.refri.techbox.base.Resource
import com.refri.techbox.data.response.Article
import com.refri.techbox.databinding.FragmentNewsListBinding
import com.refri.techbox.ui.newslist.Adapter.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding, NewsListViewModel>(FragmentNewsListBinding::inflate) {

    private lateinit var mainAdapter: NewsListAdapter

    private fun setDataAdapter(data: List<Article>?){
        data?.let{ mainAdapter.setItems(it)}
    }

    override fun initView() {
        getData()
        initSwipeRefresh()
    }

    private fun initList(){
        mainAdapter = NewsListAdapter {
            startActivity(requireContext(), it.url)
        }

        getViewBinding().rvContent.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = this@NewsListFragment.mainAdapter
        }

    }

    companion object {
        const val EXTRAS_URL = "EXTRAS_URL"

        @JvmStatic
        fun startActivity(context: Context?, url: String?) {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(url)
            openUrl.putExtra(EXTRAS_URL, url)
            context?.startActivity(openUrl)
        }
    }

    private fun initSwipeRefresh(){
        getViewBinding().srlContent.setOnRefreshListener {
            getViewBinding().srlContent.isRefreshing = false
            getData()
        }
    }

    private fun getData(){
       getViewModel().getNewsList()
    }

    override fun observeData() {
       getViewModel().getNewsListLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let{data ->
                        if (data.isEmpty()) {
                            showError(true, getString(R.string.text_no_item))
                            showContent(false)
                        } else {
                            showContent(true)
                            showError(false, null)
                            initList()
                            setDataAdapter(it.data)
                        }

                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    fun showContent(isVisible: Boolean) {
        getViewBinding().rvContent.isVisible = isVisible
    }

    fun showLoading(isVisible: Boolean) {
       getViewBinding().pbLoading.isVisible = isVisible
    }

    fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().tvMessage.isVisible = isErrorEnabled
        getViewBinding().tvMessage.text = msg
    }





}