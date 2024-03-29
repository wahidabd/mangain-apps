package com.wahidabd.mangain.view.account

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.wahidabd.mangain.databinding.FragmentAccountBinding
import com.wahidabd.mangain.utils.MySharedPreference
import com.wahidabd.mangain.utils.circularProgress
import com.wahidabd.mangain.utils.quickShowToast
import com.wahidabd.mangain.utils.setImageChapter
import com.wahidabd.mangain.view.splash.AuthActivity
import com.wahidabd.mangain.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val localViewModel: LocalViewModel by viewModels()

    private lateinit var auth: FirebaseAuth
    @Inject lateinit var pref: MySharedPreference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // set profile
        binding.apply {
            tvName.text = pref.name
            tvEmail.text = pref.email

            val circular = circularProgress(requireContext())
            imgProfile.setImageChapter(pref.photo.toString(), circular)
        }

        binding.linearClearCache.setOnClickListener { clearCache(requireContext()) }
        binding.ivLogout.setOnClickListener { logout() }
        binding.linearSendRating.setOnClickListener { sendRating() }
        binding.linearClearFavorite.setOnClickListener { deleteAllBookmark() }
        binding.linearClearHistory.setOnClickListener { deleteAllHistory() }

        binding.linearBugAndReport.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToBugReportFragment(pref.email, pref.name)
            findNavController().navigate(action)
        }

        binding.linearPrivacyPolicy.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToPrivacyPolicyFragment()
            findNavController().navigate(action)
        }
    }

    private fun deleteAllHistory(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Data Hostory!")
            .setMessage("Data history yang dihapus tidak dapat dikembalikan")
            .setPositiveButton("Hapus"){_, _ ->
                localViewModel.deleteAllHistory()
                quickShowToast("Data history berhasil dihapus!")
            }
            .setNegativeButton("Batal"){d, _ ->
                d.dismiss()
            }
            .show()
    }

    private fun deleteAllBookmark(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Data Favorite!")
            .setMessage("Data favorite yang dihapus tidak dapat dikembalikan")
            .setPositiveButton("Hapus"){_, _ ->
                localViewModel.deleteAllBookmark()
                quickShowToast("Data favorite berhasil dihapus!")
            }
            .setNegativeButton("Batal"){d, _ ->
                d.dismiss()
            }
            .show()
    }

    private fun sendRating(){
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.wahidabd.mangain")))
        }catch (e: ActivityNotFoundException){
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.wahidabd.mangain")))
        }
    }

    private fun clearCache(context: Context){

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Cache Aplikasi?")
            .setMessage("Menghapus cache aplikasi dapat mengurangi jumlah penyimpanan pada smartphone mu-^")
            .setPositiveButton("Hapus"){_, _ ->
                try {
                    val file: File = context.cacheDir
                    deleteDir(file)
                    quickShowToast("Cache Cleared")
                }catch (e: Exception){
                    quickShowToast(e.toString())
                }
            }
            .setNegativeButton("Batal"){d, _ ->
                d.dismiss()
            }
            .show()
    }

    private fun deleteDir(dir: File?): Boolean{
        if (dir != null && dir.isDirectory){
            val children = ArrayList<String>()
            children.addAll(dir.list())

            for (i in 0 until children.size){
                val success = deleteDir(File(dir, children[i]))
                if (!success) return false
            }
            return dir.delete()
        }else if (dir != null && dir.isFile){
            return dir.delete()
        }else{
            return false
        }
    }

    private fun logout(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Keluar")
            .setMessage("Yakin untuk keluar dari aplikasi?")
            .setPositiveButton("YA"){_, _ ->
                pref.logout()
                val intent = Intent(activity, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity?.finish()
                auth.signOut()
            }
            .setNegativeButton("TIDAK"){d, _ ->
                d.dismiss()
            }
            .show()
    }

}