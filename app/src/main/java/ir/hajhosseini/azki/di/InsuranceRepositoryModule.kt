package ir.hajhosseini.azki.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hajhosseini.azki.business.data.remote.insurancelistimpimpl.GetInsuranceNetworkDataSourceImpl
import ir.hajhosseini.azki.business.domain.repository.InsuranceListRepository
import ir.hajhosseini.azki.util.InternetStatus
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object InsuranceRepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        retrofitInterface: GetInsuranceNetworkDataSourceImpl,
        internetStatus: InternetStatus,
    ): InsuranceListRepository {
        return InsuranceListRepository(retrofitInterface,internetStatus)
    }
}