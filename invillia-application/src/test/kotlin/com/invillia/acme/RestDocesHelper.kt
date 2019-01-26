package com.invillia.acme

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields

object RestDocsHelper {

    fun storeDocument(documentName: String): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            documentName,
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            requestFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description("Store name. *"),
                fieldWithPath("address").type(JsonFieldType.STRING).description("Store address. *")
            ),
            responseFields(
                fieldWithPath("storeId").type(JsonFieldType.STRING).description("Id of saved Store."),
                fieldWithPath("name").type(JsonFieldType.STRING).description("Store name."),
                fieldWithPath("address").type(JsonFieldType.STRING).description("Store address.")
            )
        )
    }
}
