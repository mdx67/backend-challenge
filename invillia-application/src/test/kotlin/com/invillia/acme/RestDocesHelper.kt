package com.invillia.acme

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.payload.ResponseFieldsSnippet

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

    fun updateDocument(documentName: String): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            documentName,
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            requestFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description("Store name. *"),
                fieldWithPath("address").type(JsonFieldType.STRING).description("Store address. *")
            )
        )
    }

    fun getDocument(documentName: String): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            documentName,
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            responseFields(
                fieldWithPath("storeId").type(JsonFieldType.STRING).description("Id of saved Store."),
                fieldWithPath("name").type(JsonFieldType.STRING).description("Store name."),
                fieldWithPath("address").type(JsonFieldType.STRING).description("Store address.")
            )
        )
    }

    fun orderDocument(documentName: String): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            documentName,
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            requestFields(
                fieldWithPath("address").type(JsonFieldType.STRING).description("Order address. *"),
                fieldWithPath("items[0].description").type(JsonFieldType.STRING).description("Item description."),
                fieldWithPath("items[0].amount").type(JsonFieldType.NUMBER).description("Item price. *"),
                fieldWithPath("items[0].quantity").type(JsonFieldType.NUMBER).description("Item quantity. *")
            ),
            buildResponseOrder()
        )
    }

    fun orderItemDocument(documentName: String): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            documentName,
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            requestFields(
                fieldWithPath("description").type(JsonFieldType.STRING).description("Item description."),
                fieldWithPath("amount").type(JsonFieldType.NUMBER).description("Item price. *"),
                fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("Item quantity. *")
            ),
            buildResponseOrder()
        )
    }

    fun orderResponseDocument(documentName: String): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            documentName,
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
        )
    }

    private fun buildResponseOrder(): ResponseFieldsSnippet? {
        return responseFields(
            fieldWithPath("orderId").type(JsonFieldType.STRING).description("Id of saved Order."),
            fieldWithPath("address").type(JsonFieldType.STRING).description("Order address."),
            fieldWithPath("confirmationDate").optional().type(JsonFieldType.STRING).description("Confirmation date of order payment."),
            fieldWithPath("status").type(JsonFieldType.STRING).description("Order status."),
            fieldWithPath("items[*].orderItemId").optional().type(JsonFieldType.STRING).description("Order item id."),
            fieldWithPath("items[*].description").optional().type(JsonFieldType.STRING).description("Item description."),
            fieldWithPath("items[*].amount").optional().type(JsonFieldType.NUMBER).description("Item price."),
            fieldWithPath("items[*].quantity").optional().type(JsonFieldType.NUMBER).description("Item quantity.")
        )
    }

    fun paymentDocument(documentName: String): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            documentName,
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
            requestFields(
                fieldWithPath("orderId").type(JsonFieldType.STRING).description("Order id to perform payment. *"),
                fieldWithPath("creditCardNumber").type(JsonFieldType.STRING).description("Credit card number. *"),
                fieldWithPath("async").type(JsonFieldType.BOOLEAN).description("True to perform payment asynchronous. *")
            ),
            responseFields(
                fieldWithPath("paymentId").type(JsonFieldType.STRING).description("Payment id."),
                fieldWithPath("orderId").type(JsonFieldType.STRING).description("Order id."),
                fieldWithPath("status").type(JsonFieldType.STRING).description("Payment status"),
                fieldWithPath("creditCardNumber").type(JsonFieldType.STRING).description("Credit card number."),
                fieldWithPath("paymentDate").optional().type(JsonFieldType.STRING).description("Payment date.")
            )
        )
    }
}
