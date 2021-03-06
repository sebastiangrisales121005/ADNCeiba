package com.ceiba.adnceiba.dimodule

import android.content.Context
import com.ceiba.dataaccess.repository.ParkingRepositoryRoom
import com.ceiba.dataaccess.repository.ParkingServiceRoom
import com.ceiba.dataaccess.repository.RoomInstance
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
abstract class ParkingModule {

    @Binds
    abstract fun bindRepository(parkingRepositoryRoom: ParkingRepositoryRoom): ParkingEntranceExitRepository


    companion object {
        @Provides
        fun provideParkingRoom(@ApplicationContext context: Context): ParkingServiceRoom =
            RoomInstance.createDatabase(context).parkingDao()
    }
}