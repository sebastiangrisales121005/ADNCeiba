package com.ceiba.adnceiba.dimodule

import com.ceiba.dataaccess.repository.ParkingRepositoryRoom
import com.ceiba.domain.repository.ParkingValidateEnterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class) abstract class ParkingModule {

    @Binds abstract fun bindRepository(parkingRepositoryRoom: ParkingRepositoryRoom): ParkingValidateEnterRepository
}