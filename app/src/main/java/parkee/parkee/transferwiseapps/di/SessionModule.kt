package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.session.SessionManager

var sessionModule = module {

    single { SessionManager(get()) }
}