package abika.sinau.assignmentweek8.ui.register.register1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abika.sinau.assignmentweek8.R
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnNext.setOnClickListener(this)
        back.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnNext -> {
                when {
                    etName.text.toString().isEmpty() -> {
                        etName.error = "Nama harus diisi!"
                    }
                    etEmail.text.toString().isEmpty() -> {
                        etEmail.error = "Email harus diisi"
                    }
                    else -> {
                        val bundle =
                            bundleOf(
                                "name" to etName.text.toString(),
                                "email" to etEmail.text.toString()
                            )
                        navController.navigate(
                            R.id.action_register1Fragment_to_register2Fragment,
                            bundle
                        )
                    }
                }
            }
            R.id.back -> activity?.onBackPressed()
        }
    }
}