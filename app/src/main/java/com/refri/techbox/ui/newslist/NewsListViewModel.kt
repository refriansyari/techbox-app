package com.refri.techbox.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refri.techbox.base.Resource
import com.refri.techbox.data.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(val repository: NewsListRepository) : ViewModel() {

    private val newsLiveData = MutableLiveData<Resource<List<Article>>>()


    fun getNewsList(){
        newsLiveData.value = Resource.Loading()

        viewModelScope.launch (Dispatchers.IO){
            try{
                val response = repository.getNewsList()
                viewModelScope.launch (Dispatchers.Main){
                    newsLiveData.value = response.articles.let{Resource.Success(it)}
                }
            } catch (e:Exception){
                viewModelScope.launch(Dispatchers.Main){
                    newsLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    fun getNewsListLiveData(): LiveData<Resource<List<Article>>> = newsLiveData

}