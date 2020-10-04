package abika.sinau.assignmentweek8.di.module

import abika.sinau.assignmentweek8.ui.main.fragment.LoginViewModel
import abika.sinau.assignmentweek8.ui.register.RegisterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */

val viewModelModule = module {

    viewModel{
        RegisterViewModel(get())
        LoginViewModel(get())
    }
}