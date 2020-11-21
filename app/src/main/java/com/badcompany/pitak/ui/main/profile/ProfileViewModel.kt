package com.badcompany.pitak.ui.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.badcompany.core.Constants.TXT_ID
import com.badcompany.core.Constants.TXT_TOKEN
import com.badcompany.core.ErrorWrapper
import com.badcompany.core.ResultWrapper
import com.badcompany.core.exhaustive
import com.badcompany.domain.domainmodel.CarDetails
import com.badcompany.domain.usecases.DeleteCar
import com.badcompany.domain.usecases.GetCars
import com.badcompany.domain.usecases.SetDefaultCar
import com.badcompany.pitak.ui.BaseViewModel
import com.badcompany.pitak.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ProfileViewModel  @ViewModelInject constructor(val getCarList: GetCars, val deleteCar: DeleteCar, val setDefaultCar: SetDefaultCar) :
    BaseViewModel() {


    val carsResponse = SingleLiveEvent<ResultWrapper<List<CarDetails>>>()
    val deleteCarResponse = SingleLiveEvent<ResultWrapper<Int>>()
    val defaultCarResponse = SingleLiveEvent<ResultWrapper<Int>>()

    fun getCarList() {
        carsResponse.value = ResultWrapper.InProgress
        viewModelScope.launch(IO) {
            val response = getCarList.execute()
            withContext(Main) {
                carsResponse.value = response
            }
        }
    }

    fun deleteCar( id: Long, position: Int) {
        deleteCarResponse.value = ResultWrapper.InProgress
        viewModelScope.launch(IO) {
            val response = deleteCar.execute( id)
            withContext(Main) {
                when (response) {
                    is ErrorWrapper.ResponseError -> deleteCarResponse.value = response
                    is ErrorWrapper.SystemError -> deleteCarResponse.value = response
                    is ResultWrapper.Success -> deleteCarResponse.value =
                        ResultWrapper.Success(position)
                    ResultWrapper.InProgress -> deleteCarResponse.value = ResultWrapper.InProgress
                }.exhaustive


            }
        }
    }

    fun setDefault( id: Long, pos: Int) {
        defaultCarResponse.value = ResultWrapper.InProgress
        viewModelScope.launch(IO) {
            val response = setDefaultCar.execute( id)
            withContext(Main) {
                when (response) {
                    is ErrorWrapper.ResponseError -> defaultCarResponse.value = response
                    is ErrorWrapper.SystemError -> defaultCarResponse.value = response
                    is ResultWrapper.Success -> defaultCarResponse.value =
                        ResultWrapper.Success(pos)
                    ResultWrapper.InProgress -> defaultCarResponse.value = ResultWrapper.InProgress
                }.exhaustive


            }
        }
    }

}