/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.promotionengineservices.action.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.promotionengineservices.model.RuleBasedOrderAddProductActionModel;
import de.hybris.platform.promotionengineservices.promotionengine.PromotionActionService;
import de.hybris.platform.promotionengineservices.util.ActionUtils;
import de.hybris.platform.promotionengineservices.util.PromotionResultUtils;
import de.hybris.platform.promotions.model.PromotionResultModel;
import de.hybris.platform.ruleengine.model.AbstractRuleEngineRuleModel;
import de.hybris.platform.ruleengineservices.converters.populator.CartModelBuilder;
import de.hybris.platform.ruleengineservices.rao.AbstractActionedRAO;
import de.hybris.platform.ruleengineservices.rao.AbstractRuleActionRAO;
import de.hybris.platform.ruleengineservices.rao.CartRAO;
import de.hybris.platform.ruleengineservices.rao.FreeProductRAO;
import de.hybris.platform.ruleengineservices.rao.OrderEntryRAO;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.SingletonMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class DefaultAddProductToCartActionStrategyTest
{
	private static final String BEAN_NAME = "defaultAddProductToCartActionStrategy";
	private static final String PRODUCT_CODE = "1234";
	private static final int PRODUCT_QUANTITY = 1;

	@InjectMocks
	private DefaultAddProductToCartActionStrategy defaultAddProductToCartActionStrategy;

	@Mock
	private FreeProductRAO freeProductRao;
	@Mock
	private AbstractActionedRAO abstractActionedRao;
	@Mock
	private CartRAO cartRao;
	@Mock
	private OrderEntryRAO orderEntryRao;
	@Mock
	private ProductService productService;
	@Mock
	private PromotionActionService promotionActionService;
	@Mock
	private ProductModel product;
	@Mock
	private ModelService modelService;
	@Mock
	private PromotionResultModel promotionResult;
	@Mock
	private CartModel cart;
	@Mock
	private CartService cartService;
	@Mock
	private CartEntryModel cartEntry;
	@Mock
	private AbstractRuleEngineRuleModel rule;
	@Mock
	private PromotionResultUtils promotionResultUtils;
	@Mock
	private ActionUtils actionUtils;

	private Class<RuleBasedOrderAddProductActionModel> promotionAction;

	@Before
	public void setUp()
	{

		defaultAddProductToCartActionStrategy.setPromotionAction(promotionAction);
		defaultAddProductToCartActionStrategy.setBeanName(BEAN_NAME);
		Mockito.lenient().when(Boolean.valueOf(modelService.isNew(any(Object.class)))).thenReturn(Boolean.FALSE);
		Mockito.lenient().when(freeProductRao.getAppliedToObject()).thenReturn(cartRao);
		Mockito.lenient().when(freeProductRao.getAddedOrderEntry()).thenReturn(orderEntryRao);
		Mockito.lenient().when(orderEntryRao.getProductCode()).thenReturn(PRODUCT_CODE);
		Mockito.lenient().when(freeProductRao.getQuantityAdded()).thenReturn(1);
		Mockito.lenient().when(promotionActionService.createPromotionResult(freeProductRao)).thenReturn(promotionResult);
		Mockito.lenient().when(promotionResult.getOrder()).thenReturn(cart);
		Mockito.lenient().when(productService.getProductForCode(PRODUCT_CODE)).thenReturn(product);
		Mockito.lenient().when(cartService.addNewEntry(cart, product, PRODUCT_QUANTITY, null, -1, false)).thenReturn(cartEntry);
		Mockito.lenient().when(cartEntry.getEntryNumber()).thenReturn(Integer.valueOf(0));
		Mockito.lenient().when(promotionActionService.getRule(freeProductRao)).thenReturn(rule);
		Mockito.lenient().when(Integer.valueOf(orderEntryRao.getQuantity())).thenReturn(Integer.valueOf(PRODUCT_QUANTITY));
		Mockito.lenient().when(promotionResultUtils.getOrder(promotionResult)).thenReturn(cart);
		Mockito.lenient().when(Boolean.valueOf(actionUtils.isActionUUID(anyString()))).thenReturn(Boolean.TRUE);
	}

	@Test
	public void testApplyNotFreeProductRAO()
	{
		final List result = defaultAddProductToCartActionStrategy.apply(new AbstractRuleActionRAO());
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApplyAppliedToObjectNotCartRAO()
	{
		Mockito.lenient().when(freeProductRao.getAppliedToObject()).thenReturn(abstractActionedRao);

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApplyAddedOrderEntryNull()
	{
		Mockito.lenient().when(freeProductRao.getAddedOrderEntry()).thenReturn(null);

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApplyProductNull()
	{
		Mockito.lenient().when(orderEntryRao.getProductCode()).thenReturn(null);

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApplyProductCodeNull()
	{
		Mockito.lenient().when(orderEntryRao.getProductCode()).thenReturn(null);

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApplyProductNotFound()
	{
		Mockito.lenient().when(productService.getProductForCode(PRODUCT_CODE)).thenThrow(new UnknownIdentifierException("Product not found"));

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApplyPromotionResultNull()
	{
		Mockito.lenient().when(promotionActionService.createPromotionResult(freeProductRao)).thenReturn(null);

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApplyOrderNull()
	{
		Mockito.lenient().when(promotionResult.getOrder()).thenReturn(null);
		Mockito.lenient().when(promotionResultUtils.getOrder(promotionResult)).thenReturn(null);

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testApply()
	{
		Mockito.lenient().when(modelService.create(promotionAction)).thenReturn(new RuleBasedOrderAddProductActionModel());

		final List result = defaultAddProductToCartActionStrategy.apply(freeProductRao);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals(promotionResult, result.get(0));
		//fix https://jira.tools.sap/browse/CXEC-47781, Cart calculation may run into deadlock when has promotion applied
		verify(modelService, times(1)).saveAll(any(PromotionResultModel.class),
				any(RuleBasedOrderAddProductActionModel.class), any(CartEntryModel.class), any(CartModel.class));
	}

	@Test
	public void testCreatePromotionAction()
	{
		Mockito.lenient().when(modelService.create(promotionAction)).thenReturn(new RuleBasedOrderAddProductActionModel());

		final RuleBasedOrderAddProductActionModel action = defaultAddProductToCartActionStrategy
				.createOrderAddProductAction(freeProductRao, PRODUCT_QUANTITY, product, promotionResult);

		assertEquals(promotionResult, action.getPromotionResult());
		assertEquals(rule, action.getRule());
		assertTrue(action.getMarkedApplied().booleanValue());
		assertEquals(BEAN_NAME, action.getStrategyId());
		assertEquals(Long.valueOf(PRODUCT_QUANTITY), action.getQuantity());
		assertEquals(product, action.getProduct());
	}

	@Test
	public void testUndo()
	{
		final RuleBasedOrderAddProductActionModel action = new RuleBasedOrderAddProductActionModel();
		product.setCode("1111");
		action.setProduct(product);
		action.setPromotionResult(promotionResult);
		action.setQuantity(Long.valueOf(1));

		final CartModel cart = CartModelBuilder.newCart("1234").setCurrency("USD").addProduct("123456", 1, 30.00, 0, "cat1")
				.addProduct("1111", 1, 20.00, 1, "cat1").addProduct("234567", 2, 60.00, 2, "cat2").getModel();
		Mockito.lenient().when(promotionResultUtils.getOrder(promotionResult)).thenReturn(cart);

		// check entry numbers prior to undo
		assertEquals(3, cart.getEntries().size());
		assertEquals(0, cart.getEntries().get(0).getEntryNumber().intValue());
		assertEquals(1, cart.getEntries().get(1).getEntryNumber().intValue());
		assertEquals(2, cart.getEntries().get(2).getEntryNumber().intValue());

		AbstractOrderEntryModel freeGift = null;
		for (final AbstractOrderEntryModel entry : cart.getEntries())
		{
			// set 2nd entry as the free gift.
			if (entry.getEntryNumber().intValue() == 1)
			{
				freeGift = entry;
				entry.setProduct(product);
				entry.setGiveAway(Boolean.TRUE);
			}
		}

		doAnswer(new UpdateQuantitiesAnswer()).when(cartService).updateQuantities(cart,
				new SingletonMap(Integer.valueOf(1), Long.valueOf(0)));
		Mockito.lenient().when(promotionResult.getOrder()).thenReturn(cart);
		Mockito.lenient().when(Boolean.valueOf(modelService.isRemoved(freeGift))).thenReturn(Boolean.TRUE);
		// removes free gift and normalizes order entry numbers
		defaultAddProductToCartActionStrategy.undo(action);

		assertEquals(2, cart.getEntries().size());
		assertEquals(0, cart.getEntries().get(0).getEntryNumber().intValue());
		assertEquals(1, cart.getEntries().get(1).getEntryNumber().intValue());
		assertEquals("123456", cart.getEntries().get(0).getProduct().getCode());
		assertEquals("234567", cart.getEntries().get(1).getProduct().getCode());
	}


	private class UpdateQuantitiesAnswer implements Answer
	{

		@Override
		public Object answer(final InvocationOnMock invocation) throws Throwable
		{
			final CartModel arg1 = (CartModel) invocation.getArguments()[0];
			for (final Iterator<AbstractOrderEntryModel> iter = arg1.getEntries().listIterator(); iter.hasNext();)
			{
				final AbstractOrderEntryModel entry = iter.next();
				if (entry.getEntryNumber().intValue() == 1)
				{
					iter.remove();
				}
			}
			return arg1;
		}
	}

	@Test
	public void testUndoNoUndoEntryFound()
	{
		final RuleBasedOrderAddProductActionModel action = new RuleBasedOrderAddProductActionModel();
		product.setCode("1111");
		action.setProduct(product);
		action.setPromotionResult(promotionResult);
		action.setQuantity(Long.valueOf(1));

		final CartModel cart = CartModelBuilder.newCart("1234").setCurrency("USD").addProduct("123456", 1, 30.00, 0, "cat1")
				.addProduct("222222", 1, 20.00, 1, "cat1").getModel();
		Mockito.lenient().when(promotionResult.getOrder()).thenReturn(cart);

		defaultAddProductToCartActionStrategy.undo(action);
	}
}
