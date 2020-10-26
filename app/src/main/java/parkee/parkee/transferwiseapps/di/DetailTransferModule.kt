package parkee.parkee.transferwiseapps.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import parkee.parkee.transferwiseapps.ui.TransferMoneyModel
import parkee.parkee.transferwiseapps.ui.home.DetailTransferViewModel

var detailTransferModule = module {

    viewModel { (data: TransferMoneyModel) -> DetailTransferViewModel(data, get(), get()) }
}