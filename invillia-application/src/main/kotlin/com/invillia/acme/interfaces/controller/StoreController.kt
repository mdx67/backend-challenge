package com.invillia.acme.interfaces.controller

import com.invillia.acme.api.CreateStoreData
import com.invillia.acme.api.CreatedStoreData
import com.invillia.acme.api.StoreApi
import com.invillia.acme.api.UpdateStoreData
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(StoreController.ROOT_URL)
class StoreController : StoreApi {

    companion object {
        const val ROOT_URL = "/v1/store"
    }

    override fun create(@Valid @RequestBody request: CreateStoreData): CreatedStoreData {

        return CreatedStoreData(storeId = "id", name = "name", address = "address")
    }

    override fun update(
        @PathVariable("id") id: String,
        @Valid @RequestBody request: UpdateStoreData
    ) {

    }

    override fun query(@PathVariable("id") id: String): CreatedStoreData {

        return CreatedStoreData(storeId = "id", name = "name", address = "address")
    }
}
