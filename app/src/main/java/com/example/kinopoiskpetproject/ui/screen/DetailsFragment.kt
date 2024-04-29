package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.kinopoiskpetproject.MyApp
import com.example.kinopoiskpetproject.databinding.FragmentDetailsBinding
import com.example.kinopoiskpetproject.model.FilmDetails
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.Shimmer.AlphaHighlightBuilder
import com.facebook.shimmer.ShimmerDrawable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {
    lateinit var binding:FragmentDetailsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val bundle = this.arguments
        val proba = bundle!!.getInt("FilmId")
        var filmDetails: FilmDetails? = null
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val result = MyApp().configureRetrofit().getFilmDetails(proba)
            val jobGetting: Job = launch{
                filmDetails = result?.body()
            }
            jobGetting.start()
            val jobSetting: Job = launch(Dispatchers.Main) {
                Glide.with(binding.imageDetails).load(filmDetails?.posterUrl).placeholder(getShimmerDrawable()).diskCacheStrategy(
                    DiskCacheStrategy.ALL).into(binding.imageDetails);
                binding.nameDetails.text = filmDetails!!.nameRu
                binding.descriptionDetails.text = filmDetails!!.description
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.mainLayout.visibility = View.VISIBLE
            }
            jobSetting.join()
        }
        binding.descriptionDetails.setOnClickListener(View.OnClickListener {
            Toast.makeText(activity,"Фильм добавлен в избранное", Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }
    fun getShimmerDrawable(): ShimmerDrawable {
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