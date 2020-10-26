package parkee.parkee.transferwiseapps.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import parkee.parkee.transferwiseapps.network.recipient.RecipientService
import parkee.parkee.transferwiseapps.repository.RecipientRepository
import parkee.parkee.transferwiseapps.ui.recipients.AddRecipientViewModel
import parkee.parkee.transferwiseapps.ui.recipients.RecipientsViewModel
import retrofit2.Retrofit

val recipientsModules = module {

    factory {
        RecipientRepository(
            get<Retrofit>().create(RecipientService::class.java)
        )
    }

    viewModel {
        RecipientsViewModel(get(),)
    }

    viewModel {
        AddRecipientViewModel(get(), get(),get())
    }
}