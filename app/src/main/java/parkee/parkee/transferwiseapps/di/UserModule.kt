package parkee.parkee.transferwiseapps.di

import org.koin.dsl.module
import parkee.parkee.transferwiseapps.data.network.user.UserService
import parkee.parkee.transferwiseapps.data.network.userProfiles.UserProfilesService
import parkee.parkee.transferwiseapps.data.repository.UserProfilesRepository
import parkee.parkee.transferwiseapps.data.repository.UserRepository
import retrofit2.Retrofit

val userModule = module {

    factory {
        UserRepository(
            get<Retrofit>().create(UserService::class.java)
        )
    }

    factory {
        UserProfilesRepository(
            get<Retrofit>().create(UserProfilesService::class.java)
        )
    }
}