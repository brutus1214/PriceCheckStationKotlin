package com.example.pricecheckstationkotlin.di

import com.example.pricecheckstationkotlin.domain.repository.DepartmentRepository
import com.example.pricecheckstationkotlin.domain.repository.ItemRepository
import com.example.pricecheckstationkotlin.domain.repository.VendorRepository
import com.example.pricecheckstationkotlin.domain.repository.usecase.DepartmentUseCases
import com.example.pricecheckstationkotlin.domain.repository.usecase.GetDepartmentByIdUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.GetDepartmentUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.GetItemByIdUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.GetItemUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.GetVendorByIdUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.GetVendorUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.InsertDepartmentUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.InsertItemUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.InsertVendorUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.ItemUseCases
import com.example.pricecheckstationkotlin.domain.repository.usecase.PrintItemUseCase
import com.example.pricecheckstationkotlin.domain.repository.usecase.VendorUseCases
import com.example.pricecheckstationkotlin.model.data.repository.DepartmentRepositoryImpl
import com.example.pricecheckstationkotlin.model.data.repository.ItemRepositoryImpl
import com.example.pricecheckstationkotlin.model.data.repository.VendorRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideItemRepository(): ItemRepository {
        return ItemRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideDepartmentRepository(): DepartmentRepository {
        return DepartmentRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideVendorRepository(): VendorRepository {
        return VendorRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideItemUseCases(repository: ItemRepository): ItemUseCases {
        return ItemUseCases(
            getItem = GetItemUseCase(repository),
            getItemById = GetItemByIdUseCase(repository),
            insertItem = InsertItemUseCase(repository),
            printItem = PrintItemUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDepartmentUseCases(repository: DepartmentRepository): DepartmentUseCases {
        return DepartmentUseCases(
            getDepartment = GetDepartmentUseCase(repository),
            getDepartmentById = GetDepartmentByIdUseCase(repository),
            insertDepartment = InsertDepartmentUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideVendorUseCases(repository: VendorRepository): VendorUseCases {
        return VendorUseCases(
            getVendor = GetVendorUseCase(repository),
            getVendorById = GetVendorByIdUseCase(repository),
            insertVendor = InsertVendorUseCase(repository)
        )
    }


}