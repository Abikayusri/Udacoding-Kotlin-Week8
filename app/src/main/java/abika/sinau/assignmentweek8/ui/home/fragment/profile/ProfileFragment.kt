package abika.sinau.assignmentweek8.ui.home.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.data.preferences.SessionManager
import abika.sinau.assignmentweek8.ui.boarding.BoardingActivity
import abika.sinau.assignmentweek8.utils.extension.shortToast
import android.content.Intent
import android.util.Log
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    lateinit var session: SessionManager
    private val TAG = "ProfileFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = SessionManager(requireContext())

        Log.d(TAG, "onViewCreated: ${session.email}, ${session.isLogin}")
        tvProfileEmail.text = session.email ?: "Email User"
        btnProfileLogout.setOnClickListener{
            session.logout()

            val intent = Intent(requireContext(), BoardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            shortToast(requireContext(), "Sukses logout")
            activity?.finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}