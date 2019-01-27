package com.invillia.acme

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.io.IOException

@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles(value = ["test"])
abstract class InvilliaTestBase {

    companion object {
        internal val MAPPER = ObjectMapper()
    }

    @Rule
    @JvmField
    val restDocumentation = JUnitRestDocumentation()

    @Autowired
    internal var context: WebApplicationContext? = null

    protected lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context!!)
            .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
            .build()
    }

    @Throws(IOException::class)
    fun getPayload(resource: String): String {
        val payload = javaClass.classLoader.getResourceAsStream(resource)
        return MAPPER.readTree(payload).toString()
    }
}
