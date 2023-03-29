package org.tensorflow.lite.examples.imageclassification.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import org.tensorflow.lite.examples.imageclassification.R
import org.tensorflow.lite.examples.imageclassification.databinding.FragmentHomeBinding
import org.tensorflow.lite.examples.imageclassification.utils.YogaPose
import org.tensorflow.lite.examples.imageclassification.utils.YogaVideoUrl

class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentHomeBinding
    private var yogaPose: YogaPose = YogaPose.DOWNDOG
    companion object{
        var SELECTED_YOGA_NAME = "Downdog"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtDowndog.setOnClickListener(this)
        binding.txtGoddess.setOnClickListener(this)
        binding.txtPlank.setOnClickListener(this)
        binding.txtTree.setOnClickListener(this)
        binding.txtWarrior2.setOnClickListener(this)
        binding.imgPlayIcon.setOnClickListener(this)
        binding.imgCameraFab.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.txtDowndog -> {
                binding.txtDowndog.setBackgroundResource(R.drawable.yoga_btn_selected_state)
                binding.txtGoddess.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtPlank.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtTree.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtWarrior2.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                yogaPose = YogaPose.DOWNDOG
                SELECTED_YOGA_NAME = "Downdog"
            }
            R.id.txtGoddess -> {
                binding.txtDowndog.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtGoddess.setBackgroundResource(R.drawable.yoga_btn_selected_state)
                binding.txtPlank.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtTree.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtWarrior2.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                yogaPose = YogaPose.GODDESS
                SELECTED_YOGA_NAME = "Goddess"
            }
            R.id.txtPlank -> {
                binding.txtDowndog.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtGoddess.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtPlank.setBackgroundResource(R.drawable.yoga_btn_selected_state)
                binding.txtTree.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtWarrior2.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                yogaPose = YogaPose.PLANK
                SELECTED_YOGA_NAME = "Plank"
            }
            R.id.txtTree -> {
                binding.txtDowndog.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtGoddess.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtPlank.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtTree.setBackgroundResource(R.drawable.yoga_btn_selected_state)
                binding.txtWarrior2.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                yogaPose = YogaPose.TREE
                SELECTED_YOGA_NAME = "Tree"
            }
            R.id.txtWarrior2 -> {
                binding.txtDowndog.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtGoddess.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtPlank.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtTree.setBackgroundResource(R.drawable.yoga_btn_non_selected_state)
                binding.txtWarrior2.setBackgroundResource(R.drawable.yoga_btn_selected_state)
                yogaPose = YogaPose.WARRIOR2
                SELECTED_YOGA_NAME = "Warrior II"
            }
            R.id.imgPlayIcon -> {
               when(yogaPose){
                   YogaPose.DOWNDOG -> openYoutube(YogaVideoUrl.downdog)
                   YogaPose.GODDESS -> openYoutube(YogaVideoUrl.goddess)
                   YogaPose.PLANK -> openYoutube(YogaVideoUrl.plank)
                   YogaPose.TREE -> openYoutube(YogaVideoUrl.tree)
                   YogaPose.WARRIOR2 -> openYoutube(YogaVideoUrl.warrior2)
               }
            }
            R.id.imgCameraFab -> {
                findNavController().navigate(R.id.action_homeFragment_to_permissions_fragment)
            }
        }
    }

    fun openYoutube(url:String){
        val intent =  Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}