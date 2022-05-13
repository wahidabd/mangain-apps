package com.wahidabd.mangain.view.account

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wahidabd.mangain.data.models.BugReport
import com.wahidabd.mangain.databinding.FragmentBugReportBinding
import com.wahidabd.mangain.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class BugReportFragment : Fragment() {

    private var _binding: FragmentBugReportBinding? = null
    private val binding get() = _binding!!

    private val args: BugReportFragmentArgs by navArgs()
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBugReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database.reference

        binding.imgBack.setOnClickListener { findNavController().navigateUp() }



        binding.btnSend.setOnClickListener {
            val report = binding.edtReport.text.toString().trim()
            val data = BugReport(args.email, args.name, report)

            writeToFirebase(data)
            alertDialog()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun writeToFirebase(data: BugReport){
        database.child(Constant.BUG_REPORT).child(LocalDateTime.now().toString().replace(".", "")).setValue(data)
    }

    private fun alertDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Berhasil")
            .setMessage("Terimakasih sudah melaporkan bug, akan segera admin perbaiki:)")
            .setPositiveButton("YA"){_, _ ->
                findNavController().navigateUp()
            }
            .show()
    }

}