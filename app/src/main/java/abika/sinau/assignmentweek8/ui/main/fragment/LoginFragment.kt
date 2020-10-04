package abika.sinau.assignmentweek8.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abika.sinau.assignmentweek8.R
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        btnLoginMain.setOnClickListener(this)
        btnRegisterMain.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            /** Untuk login **/
            R.id.btnLoginMain -> navController.navigate(
                R.id.action_loginFragment_to_homeActivity
            )
            /** Untuk register **/
            R.id.btnRegisterMain -> navController.navigate(
                R.id.action_loginFragment_to_register1Fragment
            )
        }
    }
}