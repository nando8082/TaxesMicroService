package com.weconnect.taxes.micro.service.models.dao

import com.weconnect.taxes.micro.service.models.entities.Taxes
import org.springframework.data.repository.CrudRepository

interface ITaxesDAO : CrudRepository<Taxes, Long>{


}