package abika.sinau.assignmentweek8.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abika.sinau.assignmentweek8.R
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    var getName: String? = null
    var getEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getName = arguments?.getString("name")
        getEmail = arguments?.getString("email")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        back.setOnClickListener(this)
        btnBackLogin.setOnClickListener(this)

        tvName.text = getName
        tvEmail.text = getEmail
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.back -> activity?.onBackPressed()
            R.id.btnBackLogin -> navController.navigate(R.id.action_resultFragment_to_loginFragment)
        }
    }
}