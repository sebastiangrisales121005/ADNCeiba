package com.ceiba.adnceiba.dimodule

import android.content.Context
import com.ceiba.dataaccess.repository.*
import com.ceiba.domain.repository.VehicleEnterRepository
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
abstract class ParkingModule {

    @Binds abstract fun bindRepository(parkingRepositoryRoom: ParkingRepositoryRoom): ParkingEntranceExitRepository


    companion object {
        @Provides
        fun provideParkingRoom(@ApplicationContext context: Context): ParkingServiceRoom =
            RoomInstance.createDatabase(context).parkingDao()
    }
}