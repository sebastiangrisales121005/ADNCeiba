package com.ceiba.adnceiba.dimodule

import android.content.Context
import com.ceiba.dataaccess.repository.ParkingRepositoryRoom
import com.ceiba.dataaccess.repository.ParkingServiceRoom
import com.ceiba.dataaccess.repository.RoomInstance
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.repository.MotorcycleEnterRepository
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

    @Binds abstract fun bindRepositoryMotorcycle(parkingRepositoryRoom: ParkingRepositoryRoom): MotorcycleEnterRepository

    companion object {
        @Provides
        fun provideParkingRoom(@ApplicationContext context: Context): ParkingServiceRoom =
            RoomInstance.createDatabase(context).parkingDao()
    }
}