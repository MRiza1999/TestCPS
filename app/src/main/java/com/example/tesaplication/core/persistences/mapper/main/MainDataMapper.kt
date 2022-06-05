package com.example.tesaplication.core.persistences.mapper.main

import com.example.tesaplication.core.data.main.source.local.entity.CityEntity
import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListCity
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.model.CityEntityDomain
import com.example.tesaplication.core.domain.main.model.UserEntityDomain
import com.example.tesaplication.view.main.model.CityEntityPresentation
import com.example.tesaplication.view.main.model.UserEntityPresentation

object MainDataMapper {

    fun mapListUser(input:List<UserEntity>):List<UserEntityDomain> =
        input.map {
            UserEntityDomain(
                id = it.id,
                name = it.name,
                address = it.address,
                email = it.email,
                phoneNumber = it.phoneNumber,
                city = it.city,
            )
        }

    fun mapListUserResponeToEntity(input:List<ResponseListUser?>):List<UserEntity> =
        input.map {
            UserEntity(
                id = it?.id,
                name = it?.name,
                address = it?.address,
                email = it?.email,
                phoneNumber = it?.phoneNumber,
                city = it?.city,
            )
        }

    fun mapListUserDomainToPresentation(input:List<UserEntityDomain>):List<UserEntityPresentation> =
        input.map {
            UserEntityPresentation(
                id = it.id,
                name = it.name,
                address = it.address,
                email = it.email,
                phoneNumber = it.phoneNumber,
                city = it.city,
            )
        }

    fun mapListCity(input:List<CityEntity>):List<CityEntityDomain> =
        input.map {
            CityEntityDomain(
                id = it.id,
                name = it.name,
            )
        }

    fun mapListCityResponeToEntity(input:List<ResponseListCity?>):List<CityEntity> =
        input.map {
            CityEntity(
                id = it?.id,
                name = it?.name,
            )
        }

    fun mapListCityDomainToPresentation(input:List<CityEntityDomain>):List<CityEntityPresentation> =
        input.map {
            CityEntityPresentation(
                id = it.id,
                name = it.name
            )
        }


}