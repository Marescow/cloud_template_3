/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmswebservices.cmsitems.controller;

import static com.google.common.collect.Maps.newHashMap;
import static de.hybris.platform.cmsfacades.constants.CmsfacadesConstants.FIELD_UUID;
import static de.hybris.platform.cmswebservices.constants.CmswebservicesConstants.API_VERSION;

import de.hybris.platform.cms2.data.PageableData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cmsfacades.cmsitems.CMSItemFacade;
import de.hybris.platform.cmsfacades.data.CMSItemSearchData;
import de.hybris.platform.cmsfacades.data.CMSVersionData;
import de.hybris.platform.cmsfacades.exception.ValidationException;
import de.hybris.platform.cmsfacades.header.LocationHeaderResource;
import de.hybris.platform.cmsfacades.version.CMSVersionFacade;
import de.hybris.platform.cmswebservices.constants.CmswebservicesConstants;
import de.hybris.platform.cmswebservices.dto.CMSItemSearchWsDTO;
import de.hybris.platform.cmswebservices.dto.CMSItemUuidListWsDTO;
import de.hybris.platform.cmswebservices.dto.PageableWsDTO;
import de.hybris.platform.cmswebservices.security.IsAuthorizedCmsManager;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.webservicescommons.dto.PaginationWsDTO;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import de.hybris.platform.webservicescommons.mapping.DataMapper;
import de.hybris.platform.webservicescommons.pagination.WebPaginationUtils;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Generic controller to deal with CMS items (Components, Pages, Restrictions, etc...). Any item that extends CMSItem is
 * supported using this interface.
 */
@Controller
@IsAuthorizedCmsManager
@RequestMapping(API_VERSION + "/sites/{siteId}/cmsitems")
@Api(tags = "cmsitems")
public class CMSItemController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CMSItemController.class);

	@Resource
	private CMSItemFacade cmsItemFacade;

	@Resource
	private CMSVersionFacade cmsVersionFacade;

	@Resource
	private DataMapper dataMapper;

	@Resource
	private WebPaginationUtils webPaginationUtils;

	@Resource
	private LocationHeaderResource locationHeaderResource;

	@Resource
	private ObjectFactory<CMSVersionData> cmsVersionDataDataFactory;

	@Resource
	private CommonI18NService commonI18NService;

	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Gets CMS Item by uuid.", notes = "Retrieves an item that matches the given item uuid (Universally Unique Identifier).",
					nickname = "getCMSItemByUUId")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "When the item has not been found (CMSItemNotFoundException) "
					+ "or if there is a conversion error (ConversionException)"),
			@ApiResponse(code = 200, message = "Map&lt;String, Object&gt; representation of the CMS Item object.", response = Map.class) })
	public Map<String, Object> getCMSItemByUUid(
			@ApiParam(value = "The universally unique identifier of the item. The uuid is a composed key formed by "
					+ "the cms item uid + the catalog + the catalog version.", required = true) //
			@PathVariable
			final String uuid,
			@ApiParam(value = "Response configuration (list of fields, which should be returned in response)")
			@RequestParam(required = false)
			final String fields) throws CMSItemNotFoundException
	{
		this.setHeaderAcceptLang();
		return getCmsItemFacade().getCMSItemByUuid(uuid, fields);
	}

	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET, params =
	{ "versionId" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Gets CMS Item by uuid.", notes = "Retrieves an item that matches the given item uuid (Universally Unique Identifier).",
					nickname = "getCMSItemByUUIdAndVersion")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = " If there is any validation error (WebserviceValidationException)."),
			@ApiResponse(code = 200, message = "Map&lt;String, Object&gt; representation of the CMS Item object.", response = Map.class)})
	public Map<String, Object> getCMSItemByUUidAndVersion(@ApiParam(value = "The uid of the cms version.", required = false)
	@RequestParam("versionId")
	final String versionId,
			@ApiParam(value = "The universally unique identifier of the item. The uuid is a composed key formed by "
					+ "the cms item uid + the catalog + the catalog version.", required = true) //
			@PathVariable
			final String uuid)
	{
		this.setHeaderAcceptLang();
		try
		{
			final CMSVersionData cmsVersionData = getCmsVersionDataDataFactory().getObject();
			cmsVersionData.setItemUUID(uuid);
			cmsVersionData.setUid(versionId);

			return getCmsVersionFacade().getItemByVersion(cmsVersionData);
		}
		catch (final ValidationException e)
		{
			LOGGER.info("Validation exception", e);
			throw new WebserviceValidationException(e.getValidationErrors());
		}
	}

	@RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Removes CMS Item.", notes = "Deletes a specific instance of the content item (CMSItem) from the system by uuid.",
					nickname = "removeCMSItemByUUId")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "If it cannot find the content item (CMSItemNotFoundException).") })
	public void removeCMSItembyUUid( //
			@ApiParam(value = "The universally unique identifier of the item", required = true) //
			@PathVariable
			final String uuid) throws CMSItemNotFoundException
	{
		this.setHeaderAcceptLang();
		getCmsItemFacade().deleteCMSItemByUuid(uuid);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation(value = "Creates CMS Item.", notes = "Creates a new CMS Item for a given map.",
					nickname = "createCMSItem")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "If it cannot find the content item (CMSItemNotFoundException) "
					+ "or if there is any validation error (WebserviceValidationException)."),
			@ApiResponse(code = 201, message = "The multi-level Map representing the newly created CMS Item.", response = Map.class) })
	public Map<String, Object> createCMSItem( //
			@ApiParam(value = "Map representing the CMS item to create", required = true) //
			@RequestBody
			final Map<String, Object> inputMap, //
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		this.setHeaderAcceptLang();
		try
		{
			final Map<String, Object> outputMap = getCmsItemFacade().createItem(inputMap);
			response.addHeader(CmswebservicesConstants.HEADER_LOCATION,
					getLocationHeaderResource().createLocationForChildResource(request, outputMap.get(FIELD_UUID)));
			return outputMap;
		}
		catch (final ValidationException e)
		{
			LOGGER.info("Validation exception", e);
			throw new WebserviceValidationException(e.getValidationErrors());
		}
	}

	@RequestMapping(method = RequestMethod.POST, params =
	{ "dryRun=true" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Validates CMS Item for creation.", notes = "Performs validation on the specific instance of the CMS Item in a Dry Run mode.",
					nickname = "validateCMSItemForCreation")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "If it cannot find the content item (CMSItemNotFoundException) "
					+ "or if there is any validation error (WebserviceValidationException)."),
			@ApiResponse(code = 200, message = "The multi-level Map representing the validated CMS Item.", response = Map.class) })
	public Map<String, Object> validateCMSItemForCreation(
			@ApiParam(value = "When set to TRUE, the request is executed in Dry Run mode", required = true) //
			@RequestParam("dryRun")
			final Boolean dryRun, @ApiParam(value = "Map representing the CMS item to create in Dry Run mode", required = true) //
			@RequestBody
			final Map<String, Object> inputMap, //
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		this.setHeaderAcceptLang();
		try
		{
			return getCmsItemFacade().validateItemForCreate(inputMap);
		}
		catch (final ValidationException e)
		{
			LOGGER.info("Validation exception", e);
			throw new WebserviceValidationException(e.getValidationErrors());
		}
	}

	@RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Updates CMS Item.", notes = "Updates a CMS Item for a given site id.",
					nickname = "replaceCMSItem")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "If it cannot find the content item (CMSItemNotFoundException) "
					+ "or if there is any validation error (WebserviceValidationException)."),
			@ApiResponse(code = 200, message = "The multi-level Map representing the updated CMS Item.", response = Map.class) })
	public Map<String, Object> updateCMSItem( //
			@ApiParam(value = "Unique Identifier of a CMS Item", required = true) //
			@PathVariable
			final String uuid, //
			@ApiParam(value = "Map representing the CMS item to update", required = true) //
			@RequestBody
			final Map<String, Object> inputMap) throws CMSItemNotFoundException
	{
		this.setHeaderAcceptLang();
		try
		{
			return getCmsItemFacade().updateItem(uuid, inputMap);
		}
		catch (final ValidationException e)
		{
			LOGGER.info("Validation exception", e);
			throw new WebserviceValidationException(e.getValidationErrors());
		}
	}

	@RequestMapping(value = "/{uuid}", method = RequestMethod.PUT, params =
	{ "dryRun=true" })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Validates CMS Item for update.", notes = "Performs validation steps on a CMS Item in a Dry Run mod.",
					nickname = "validateCMSItemForUpdate")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "If it cannot find the content item (CMSItemNotFoundException) "
					+ "or if there is any validation error (WebserviceValidationException)."),
			@ApiResponse(code = 200, message = "The multi-level Map representing the validated CMS Item.", response = Map.class) })
	public Map<String, Object> validateCMSItemForUpdate( //
			@ApiParam(value = "When set to TRUE, the request is executed in Dry Run mode", required = true) //
			@RequestParam("dryRun")
			final Boolean dryRun, @ApiParam(value = "Unique Identifier of a CMS Item", required = true) //
			@PathVariable
			final String uuid, @ApiParam(value = "Map representing the CMS item to validate", required = true) //
			@RequestBody
			final Map<String, Object> inputMap) throws CMSItemNotFoundException
	{
		this.setHeaderAcceptLang();
		try
		{
			return getCmsItemFacade().validateItemForUpdate(uuid, inputMap);
		}
		catch (final ValidationException e)
		{
			LOGGER.info("Validation exception", e);
			throw new WebserviceValidationException(e.getValidationErrors());
		}
	}

	@RequestMapping(value = "/uuids", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "Finds cms items by uuids in body.", notes = "Retrieves a list of available CMSItems matching the given uuids by POSTing the uuids in the request body.",
					nickname = "searchCMSItemByUUId")
	@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "If it cannot find one of the items (CMSItemNotFoundException)."),
			@ApiResponse(code = 200, message = "A map list of elements in the form of Map&lt;String, Object&gt;, "
					+ "each representing a CMSItem.", response = Map.class) })
	public Map<String, Object> findCmsItemsByUuidsInBody(
			@ApiParam(value = "CMSItemUuidListWsDTO", required = true)
			@RequestBody
			final CMSItemUuidListWsDTO dto) throws CMSItemNotFoundException
	{
		this.setHeaderAcceptLang();
		final List<Map<String, Object>> searchResults = getCmsItemFacade().findCMSItems(dto.getUuids(), dto.getFields());
		return Collections.singletonMap(CmswebservicesConstants.WSDTO_RESPONSE_PARAM_RESULTS, searchResults);
	}

	@RequestMapping(method = RequestMethod.GET, params =
	{ "pageSize", "currentPage" })
	@ResponseBody
	@ApiOperation(value = "Finds CMS items.", notes = "Retrieves a list of available CMS items that match pages search.", nickname = "getCMSItemByPagedSearch")
	@ApiResponses(
	{ //
			@ApiResponse(code = 400, message = "If the required fields are missing (WebserviceValidationException)."),
			@ApiResponse(code = 200, message = "A map of paging info and results. Results are in the form of "
					+ "Map&lt;String, Object&gt;, each representing a CMSItem. Never null.") })
	@ApiImplicitParams(
	{ //
			@ApiImplicitParam(name = "siteId", value = "The site identifier", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "pageSize", value = "Page size for paging", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "currentPage", value = "The current result page requested", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "catalogId", value = "The catalog id", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "catalogVersion", value = "CatalogVersion on which to search", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "mask", value = "Search mask applied to the UID and NAME fields, Uses partial matching", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "typeCode", value = "TypeCode filter. Exact matches only. Either typeCode or typeCodes can be set.", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "typeCodes", value = "Search using a comma separated list of type code. Either typeCode or typeCodes can be set.", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "itemSearchParams", value = "Search on additional fields using a comma separated list of field name "
					+ "and value pairs which are separated by a colon. Exact matches only. You can use {@code null} as value.", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "The requested ordering for the search results.", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "fields", value = "Response configuration (list of fields, which should be returned in response", dataType = "string", paramType = "query")
	})
	public Map<String, Object> findCmsItems( //
			@ApiParam(value = "CMS Item search DTO", required = true) //
			@ModelAttribute
			final CMSItemSearchWsDTO cmsItemSearchInfo, //
			@ApiParam(value = "Pageable DTO", required = true) //
			@ModelAttribute
			final PageableWsDTO pageableInfo)
	{
		this.setHeaderAcceptLang();
		final Map<String, Object> results = newHashMap();
		try
		{
			final PageableData pageableData = getDataMapper().map(pageableInfo, PageableData.class);
			final CMSItemSearchData cmsItemSearchData = getDataMapper().map(cmsItemSearchInfo, CMSItemSearchData.class);

			final SearchResult<Map<String, Object>> searchResults = getCmsItemFacade().findCMSItems(cmsItemSearchData, pageableData);

			final PaginationWsDTO paginationInfo = getWebPaginationUtils().buildPagination(searchResults);

			results.put(CmswebservicesConstants.WSDTO_RESPONSE_PARAM_RESULTS, searchResults.getResult());
			results.put(CmswebservicesConstants.WSDTO_RESPONSE_PARAM_PAGINATION, paginationInfo);
		}
		catch (final ValidationException e)
		{
			LOGGER.info("Validation exception", e);
			throw new WebserviceValidationException(e.getValidationObject());
		}

		return results;
	}

	protected CMSItemFacade getCmsItemFacade()
	{
		return cmsItemFacade;
	}

	public void setCmsItemFacade(final CMSItemFacade cmsItemFacade)
	{
		this.cmsItemFacade = cmsItemFacade;
	}

	protected DataMapper getDataMapper()
	{
		return dataMapper;
	}

	public void setDataMapper(final DataMapper dataMapper)
	{
		this.dataMapper = dataMapper;
	}

	protected WebPaginationUtils getWebPaginationUtils()
	{
		return webPaginationUtils;
	}

	public void setWebPaginationUtils(final WebPaginationUtils webPaginationUtils)
	{
		this.webPaginationUtils = webPaginationUtils;
	}

	protected LocationHeaderResource getLocationHeaderResource()
	{
		return locationHeaderResource;
	}

	public void setLocationHeaderResource(final LocationHeaderResource locationHeaderResource)
	{
		this.locationHeaderResource = locationHeaderResource;
	}

	protected CMSVersionFacade getCmsVersionFacade()
	{
		return cmsVersionFacade;
	}

	public void setCmsVersionFacade(final CMSVersionFacade cmsVersionFacade)
	{
		this.cmsVersionFacade = cmsVersionFacade;
	}

	protected ObjectFactory<CMSVersionData> getCmsVersionDataDataFactory()
	{
		return cmsVersionDataDataFactory;
	}

	public void setCmsVersionDataDataFactory(final ObjectFactory<CMSVersionData> cmsVersionDataDataFactory)
	{
		this.cmsVersionDataDataFactory = cmsVersionDataDataFactory;
	}

	protected CommonI18NService getCommonI18NService() {
		return commonI18NService;
	}

	public void setCommonI18NService(final CommonI18NService commonI18NService) {
		this.commonI18NService = commonI18NService;
	}

	protected void setHeaderAcceptLang()
	{
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (sra != null)
		{
			HttpServletRequest req = sra.getRequest();
			String acceptLanguage = req.getHeader("Accept-Language");
			if (acceptLanguage != null)
			{
				try
				{
					getCommonI18NService().setCurrentLanguage(getCommonI18NService().getLanguage(acceptLanguage));
				}
				catch (final UnknownIdentifierException e)
				{
					LOGGER.debug("No language with the code " + acceptLanguage, e);
				}
			}
		}
	}


}
