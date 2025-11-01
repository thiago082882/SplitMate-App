package br.thiago.splitmateapp.di

import android.content.Context
import androidx.room.Room
import br.thiago.splitmateapp.data.local.SplitDatabase
import br.thiago.splitmateapp.data.repository.SplitRepositoryImpl
import br.thiago.splitmateapp.domain.repository.SplitRepository
import br.thiago.splitmateapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SplitDatabase =
        Room.databaseBuilder(
            context,
            SplitDatabase::class.java,
            "split_db"
        ).build()

    @Provides
    @Singleton
    fun provideRepository(db: SplitDatabase): SplitRepository =
        SplitRepositoryImpl(db.splitDao())

    @Provides
    @Singleton
    fun provideSplitUseCases(repository: SplitRepository): SplitUseCases {
        return SplitUseCases(
            getSplits = GetSplitsUseCase(repository),
            addSplit = AddSplitUseCase(repository),
            deleteSplit = DeleteSplitUseCase(repository)
        )
    }
}
