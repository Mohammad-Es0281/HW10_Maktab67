package ir.es.mohammad.netflix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.es.mohammad.netflix.databinding.FragmentShowInfoBinding

class ShowInfoFragment : Fragment(R.layout.fragment_show_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShowInfoBinding.bind(view)

        with(binding){
            imgProfilePic.setImageDrawable(User.profilePicId)
            textViewFullName.text = User.fullName
            textViewEmail.text = User.email
            textViewUsername.text = User.username
            textViewPhoneNumber.text = User.phoneNumber
        }
    }
}