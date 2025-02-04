package com.novatec.remote

import com.novatec.core.ErrorWrapper
import com.novatec.core.ResultWrapper
import com.novatec.data.model.CarColorEntity
import com.novatec.data.model.CarDetailsEntity
import com.novatec.data.model.CarEntity
import com.novatec.data.model.CarModelEntity
import com.novatec.data.repository.CarRemote
import com.novatec.remote.mapper.CarColorMapper
import com.novatec.remote.mapper.CarDetailsMapper
import com.novatec.remote.mapper.CarMapper
import com.novatec.remote.mapper.CarModelMapper
import javax.inject.Inject


/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class CarRemoteImpl @Inject constructor(private val authApi: AuthApi,
                                        private val carModelMapper: CarModelMapper,
                                        private val carColorMapper: CarColorMapper,
                                        private val carMapper: CarMapper,
                                        private val carDetailsMapper: CarDetailsMapper
) : CarRemote {

    override suspend fun getCars(): ResultWrapper<List<CarDetailsEntity>> {

        return try {
            val response = authApi.getCars()
            if (response.code == 1) {
                val newCars = ArrayList<CarDetailsEntity>()
                response.data?.forEach {
                    newCars.add(carDetailsMapper.mapToEntity(it))
                }
                ResultWrapper.Success(newCars)
            } else ErrorWrapper.RespError(response.code, response.message ?: "")
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }

    override suspend fun getCarModels(): ResultWrapper<List<CarModelEntity>> {
        return try {
            val response = authApi.getCarModels()
            if (response.code == 1) {
                val newCarModels = ArrayList<CarModelEntity>()
                response.data!!.forEach {
                    newCarModels.add(carModelMapper.mapToEntity(it))
                }
                ResultWrapper.Success(newCarModels)
            } else ErrorWrapper.RespError(response.code, response.message ?: "")
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }

    override suspend fun getCarColors(): ResultWrapper<List<CarColorEntity>> {
        return try {
            val response = authApi.getCarColors()
            if (response.code == 1) {
                val newCarColors = ArrayList<CarColorEntity>()
                response.data!!.forEach {
                    newCarColors.add(carColorMapper.mapToEntity(it))
                }
                ResultWrapper.Success(newCarColors)
            } else ErrorWrapper.RespError(response.code, response.message ?: "")
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }

    override suspend fun createCar(car: CarEntity): ResultWrapper<CarEntity> {
        return try {
            val response = authApi.createCar(carMapper.mapFromEntity(car))
            if (response.code == 1) {
                ResultWrapper.Success(carMapper.mapToEntity(response.data!!))
            } else ErrorWrapper.RespError(response.code, response.message ?: "")
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }

    override suspend fun deleteCar(id: Long): ResultWrapper<List<CarDetailsEntity>> {
        return try {
            val response = authApi.deleteCar(id)
            if (response.code == 1) {
                val cars = arrayListOf<CarDetailsEntity>()
                response.data?.forEach { cars.add(carDetailsMapper.mapToEntity(it)) }
                ResultWrapper.Success(cars)
            } else ErrorWrapper.RespError(response.code, response.message ?: "")
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }

    override suspend fun updateCar(car: CarEntity): ResultWrapper<CarEntity> {
        return try {
            val response = authApi.updateCar(car.id!!, carMapper.mapFromEntity(car))
            if (response.code == 1) {
                ResultWrapper.Success(carMapper.mapToEntity(response.data!!))
            } else ErrorWrapper.RespError(response.code, response.message ?: "")
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }


    override suspend fun setDefaultCar(id: Long): ResultWrapper<String> {
        return try {
            val response = authApi.setDefaultCar(id)
            if (response.code == 1) {
                ResultWrapper.Success("SUCCESS")
            } else ErrorWrapper.RespError(response.code, response.message ?: "" )
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }


}