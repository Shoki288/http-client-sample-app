package com.example.httpclientsample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.httpclientsample.databinding.FragmentMainBinding
import com.wada811.databinding.withBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding<FragmentMainBinding> { binding ->
            binding.viewModel = viewModel
        }
    }
}