package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.kinopoiskpetproject.databinding.FragmentDetailsBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.Shimmer.AlphaHighlightBuilder
import com.facebook.shimmer.ShimmerDrawable
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding:FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val bundle = this.arguments
        val proba = bundle!!.getInt("FilmId")

        val vm by activityViewModels<DetailsViewModel> (
            extrasProducer = {
                defaultViewModelCreationExtras.withCreationCallback<DetailsViewModelFactory> {
                   it.create(proba) } })

        vm.posterLiveData.observe(viewLifecycleOwner) {
            Glide.with(binding.imageDetails).load(it).placeholder(getShimmerDrawable())
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).into(binding.imageDetails)
        }

        vm.nameLiveData.observe(viewLifecycleOwner) {
            binding.nameDetails.text = it
        }

        vm.descLiveData.observe(viewLifecycleOwner) {
            binding.descriptionDetails.text = it
        }

        vm.booleanLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.mainLayout.visibility = View.VISIBLE
            }
        }

        binding.descriptionDetails.setOnClickListener {
            Toast.makeText(activity, "Фильм добавлен в избранное", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.viewModelStore?.clear()
    }
    private fun getShimmerDrawable(): ShimmerDrawable {
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