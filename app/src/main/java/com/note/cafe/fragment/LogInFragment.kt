package com.note.cafe.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View


import com.note.cafe.R
import com.note.cafe.databinding.FragmentLogInBinding



class LogInFragment : Fragment(R.layout.fragment_log_in) {

    private var fragmentLogInBinding: FragmentLogInBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLogInBinding.bind(view)
        fragmentLogInBinding = binding

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentLogInBinding = null
    }
}


