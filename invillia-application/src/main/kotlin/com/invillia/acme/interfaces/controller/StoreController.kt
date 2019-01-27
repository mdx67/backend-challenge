package com.invillia.acme.interfaces.controller

import com.invillia.acme.api.CreateStoreData
import com.invillia.acme.api.CreatedStoreData
import com.invillia.acme.api.StoreApi
import com.invillia.acme.api.UpdateStoreData
import com.invillia.acme.service.StoreService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(StoreController.ROOT_URL)
class StoreController(
    private val storeService: StoreService
) : StoreApi {

    var LOG = LoggerFactory.getLogger(StoreController::class.java)

    companion object {
        const val ROOT_URL = "/v1/store"
    }

    override fun create(@Valid @RequestBody request: CreateStoreData): CreatedStoreData {
        LOG.info("Create new Store name: ${request.name}.")

        return storeService.create(request)
    }

    override fun update(
        @PathVariable("id") id: String,
        @Valid @RequestBody request: UpdateStoreData
    ) {
        LOG.info("Update Store id: $id.")

        storeService.update(id, request)
    }

    override fun query(@PathVariable("id") id: String): CreatedStoreData {
        LOG.info("Query store, id: $id.")

        return storeService.getById(id)
    }
}
