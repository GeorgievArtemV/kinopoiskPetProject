package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kinopoiskpetproject.MyApp
import com.example.kinopoiskpetproject.databinding.FragmentCoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class CoreFragment : Fragment() {
    private lateinit var binding: FragmentCoreBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCoreBinding.inflate(inflater,container,false)

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val result = MyApp().configureRetrofit().getFilms()
            val job1:Job = launch{
                println(result)
            }
            job1.start()
            val job2:Job = launch(Dispatchers.Main) {
                binding.textView.text = result.toString()
            }
            job2.join()
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}