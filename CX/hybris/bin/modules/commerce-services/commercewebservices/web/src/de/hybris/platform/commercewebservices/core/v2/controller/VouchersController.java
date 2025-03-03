/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservices.core.v2.controller;

import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.commercewebservicescommons.dto.voucher.SAPVoucherOperationRequestWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.voucher.VoucherWsDTO;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import de.hybris.platform.webservicescommons.swagger.ApiFieldsParam;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;


@Controller
@RequestMapping(value = "/{baseSiteId}/vouchers")
@Api(tags = "Vouchers")
public class VouchersController extends BaseController
{
	@Resource(name = "voucherFacade")
	private VoucherFacade voucherFacade;

	/**
	 * @deprecated (since "2205.27", forRemoval = true)
	 */
	@Deprecated(since = "2205.27", forRemoval = true)
	@Secured("ROLE_TRUSTED_CLIENT")
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(nickname = "getVoucher", value = "Get a voucher based on code.", notes = "Returns details of a single voucher that is specified by its voucher identification code. It's deprecated. Use POST {baseSiteId}/vouchers/code/search instead.", authorizations = {
			@Authorization(value = "oauth2_client_credentials") })
	@ApiBaseSiteIdParam
	public VoucherWsDTO getVoucher(@ApiParam(value = "Voucher identifier (code)", required = true) @PathVariable final String code,
			@ApiFieldsParam(defaultValue = BASIC_FIELD_SET) @RequestParam(defaultValue = BASIC_FIELD_SET) final String fields)
			throws VoucherOperationException
	{
		return getDataMapper().map(voucherFacade.getVoucher(code), VoucherWsDTO.class, fields);
	}

	@Secured("ROLE_TRUSTED_CLIENT")
	@PostMapping("/code/search")
	@ResponseBody
	@ApiOperation(nickname = "getVoucherByCode", value = "Retrieves the voucher by voucher code.", notes = "Retrieves the details of the voucher by voucher code.", authorizations = {
			@Authorization(value = "oauth2_client_credentials") })
	@ApiBaseSiteIdParam
	public VoucherWsDTO getVoucherByCode(@RequestBody final SAPVoucherOperationRequestWsDTO voucherOperationRequestWsDTO,
			@ApiFieldsParam(defaultValue = BASIC_FIELD_SET) @RequestParam(defaultValue = BASIC_FIELD_SET) final String fields)
			throws VoucherOperationException
	{
		final String voucherCode = voucherOperationRequestWsDTO.getVoucherCode();
		return getDataMapper().map(voucherFacade.getVoucher(voucherCode), VoucherWsDTO.class, fields);

	}
}
