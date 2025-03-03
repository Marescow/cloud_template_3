/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.odata2webservices.controller

import de.hybris.bootstrap.annotations.UnitTest
import de.hybris.platform.odata2webservices.facade.IntegrationDataFacade
import de.hybris.platform.testframework.JUnitPlatformSpecification
import org.junit.Test
import org.springframework.http.ResponseEntity

import javax.servlet.http.HttpServletRequest

@UnitTest
class IntegrationDataControllerUnitTest extends JUnitPlatformSpecification {

    def oDataFacade = Stub(IntegrationDataFacade)
    def request = Stub(HttpServletRequest)
    def expectedResponse = Stub(ResponseEntity)
    def integrationDataController = new IntegrationDataController(oDataFacade)

    @Test
    def "get the schema"() {
        given:
        oDataFacade.convertAndHandleSchemaRequest(request) >> expectedResponse

        expect:
        integrationDataController.getSchema(request) == expectedResponse
    }

    @Test
    def "get entity"() {
        given:
        oDataFacade.convertAndHandleRequest(request) >> expectedResponse

        expect:
        integrationDataController.getEntity(request) == expectedResponse
    }

    @Test
    def "get entities"() {
        given:
        oDataFacade.convertAndHandleRequest(request) >> expectedResponse

        expect:
        integrationDataController.getEntities(request) == expectedResponse
    }

    @Test
    def "post"() {
        given:
        oDataFacade.convertAndHandleRequest(request) >> expectedResponse

        expect:
        integrationDataController.post(request) == expectedResponse
    }

    @Test
    def "postBatch"() {
        given:
        oDataFacade.convertAndHandleRequest(request) >> expectedResponse

        expect:
        integrationDataController.postBatch(request) == expectedResponse
    }

    @Test
    def "delete"() {
        given:
        oDataFacade.convertAndHandleRequest(request) >> expectedResponse

        expect:
        integrationDataController.delete(request) == expectedResponse
    }

}
