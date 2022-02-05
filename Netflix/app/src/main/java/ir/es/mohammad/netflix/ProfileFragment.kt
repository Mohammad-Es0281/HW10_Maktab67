package ir.es.mohammad.netflix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import ir.es.mohammad.netflix.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.commit {
            setReorderingAllowed(true)
            if (User.registered)
                add<ShowInfoFragment>(R.id.fragContainerProfile)
            else
                add<RegisterFragment>(R.id.fragContainerProfile)
        }
    }
}