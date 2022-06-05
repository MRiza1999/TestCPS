package com.example.tesaplication.core.persistences.mapper.main

import com.example.tesaplication.core.data.main.source.local.entity.UserEntity
import com.example.tesaplication.core.data.main.source.remote.response.ResponseListUser
import com.example.tesaplication.core.domain.main.model.UserEntityDomain
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



}