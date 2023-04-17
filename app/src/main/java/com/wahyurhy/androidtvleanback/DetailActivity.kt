package com.wahyurhy.androidtvleanback

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.wahyurhy.androidtvleanback.api.Response
import com.wahyurhy.androidtvleanback.databinding.ActivityDetailBinding
import com.wahyurhy.androidtvleanback.fragment.ListFragment
import com.wahyurhy.androidtvleanback.model.response.detail.DetailResponse
import com.wahyurhy.androidtvleanback.viewmodel.DetailViewModel
import com.wahyurhy.androidtvleanback.viewmodel.DetailViewModelFactory

class DetailActivity : FragmentActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewmodel: DetailViewModel
    private val castFragment = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        addFragment(castFragment)

        val movieId = intent.getIntExtra("id", 0)

        val repository = (application as MyApplication).tmdbRepo

        viewmodel = ViewModelProvider(
            this,
            DetailViewModelFactory(repository, movieId)
        )[DetailViewModel::class.java]

        viewmodel.movieDetails.observe(this) {
            when (it) {
                is Response.Loading -> {

                }
                is Response.Success -> {
                    setData(it.data)
                }
                is Response.Error -> {

                }
            }
        }

        viewmodel.castDetails.observe(this) {
            when (it) {
                is Response.Loading -> {

                }
                is Response.Success -> {
                    if (!it.data?.cast.isNullOrEmpty()) {
                        castFragment.bindCastData(it.data?.cast!!)
                    }
                }
                is Response.Error -> {

                }
            }

            binding.addToMylist.setOnKeyListener { view, keyCode, keyEvent ->
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_DOWN -> {
                        if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                            castFragment.requestFocus()
                        }
                    }
                }

                false
            }
        }
    }

    private fun addFragment(castFragment: ListFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.cast_fragment, castFragment)
        transaction.commit()
    }

    private fun setData(data: DetailResponse?) {

        binding.title.text = data?.title ?: ""
        binding.subtitle.text = getSubtitle(data)
        binding.description.text = data?.overview ?: ""

        val path = ("https://www.themoviedb.org/t/p/w780" + data?.backdropPath)
        Glide.with(this)
            .load(path)
            .into(binding.imgBanner)
    }

    private fun getSubtitle(data: DetailResponse?): String {

        val rating = if ((data!!.adult)) {
            "18+"
        } else {
            "13+"
        }

        val genres = data.genres.joinToString(
            prefix = " ",
            postfix = " • ",
            separator = " • "
        ) { it.name }

        val hours: Int = data.runtime / 60
        val min: Int = data.runtime % 60

        return rating + genres + hours + "h " + min + "m"

    }
}