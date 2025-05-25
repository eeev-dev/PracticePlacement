package com.example.practiceplacement.data.remote

import com.example.practiceplacement.data.remote.api.CompanyApi
import com.example.practiceplacement.data.remote.api.InternApi
import com.example.practiceplacement.data.remote.repository.CompaniesRepository
import com.example.practiceplacement.data.remote.repository.InternRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "http://192.168.1.104:8080"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCompanyApi(retrofit: Retrofit): CompanyApi {
        return retrofit.create(CompanyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInternApi(retrofit: Retrofit): InternApi {
        return retrofit.create(InternApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCompaniesRepository(api: CompanyApi): CompaniesRepository {
        return CompaniesRepository(api)
    }

    @Provides
    @Singleton
    fun provideInternRepository(api: InternApi): InternRepository {
        return InternRepository(api)
    }
}
