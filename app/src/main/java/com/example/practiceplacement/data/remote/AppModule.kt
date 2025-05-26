package com.example.practiceplacement.data.remote

import android.content.Context
import com.example.practiceplacement.data.remote.api.CompanyApi
import com.example.practiceplacement.data.remote.api.InternApi
import com.example.practiceplacement.data.remote.api.LetterApi
import com.example.practiceplacement.data.remote.api.LoginApi
import com.example.practiceplacement.data.remote.api.ReviewApi
import com.example.practiceplacement.data.remote.repository.AuthRepository
import com.example.practiceplacement.data.remote.repository.CompaniesRepository
import com.example.practiceplacement.data.remote.repository.InternRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLetterApi(retrofit: Retrofit): LetterApi {
        return retrofit.create(LetterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewApi(retrofit: Retrofit): ReviewApi {
        return retrofit.create(ReviewApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCompaniesRepository(api: CompanyApi): CompaniesRepository {
        return CompaniesRepository(api)
    }

    @Provides
    @Singleton
    fun provideInternRepository(@ApplicationContext context: Context, api: InternApi): InternRepository {
        return InternRepository(context, api)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: LoginApi): AuthRepository {
        return AuthRepository(api)
    }
}
