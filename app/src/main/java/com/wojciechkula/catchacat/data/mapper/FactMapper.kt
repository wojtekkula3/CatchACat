package com.wojciechkula.catchacat.data.mapper

import com.wojciechkula.catchacat.data.entity.FactEntity
import com.wojciechkula.catchacat.domain.model.FactModel
import javax.inject.Inject

class FactMapper @Inject constructor() {

    fun mapToDomain(factEntity: FactEntity) = FactModel(
        _id = factEntity._id,
        text = factEntity.text,
        createdAt = factEntity.createdAt,
        updatedAt = factEntity.updatedAt
    )

    fun mapToEntity(factModel: FactModel) = FactEntity(
        _id = factModel._id,
        text = factModel.text,
        createdAt = factModel.createdAt,
        updatedAt = factModel.updatedAt
    )
}