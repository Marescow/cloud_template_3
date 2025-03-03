/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.hybris.backoffice.searchservices.providers.impl;

import de.hybris.platform.core.model.ItemModel;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.hybris.backoffice.search.resolvers.LabelServiceForSearch;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryFacetValueDisplayNameProviderTest
{
	@Spy
	@InjectMocks
	private CategoryFacetValueDisplayNameProvider displayNameProvider;

	@Mock
	private LabelServiceForSearch labelServiceForSearch;

	@Test
	public void testGetLabelValueByLabelServiceForSearch() {
		final ItemModel mockedItemModel = Mockito.mock(ItemModel.class);
		final Locale mockedLocale = Mockito.mock(Locale.class);

		displayNameProvider.setLabelServiceProxy(null);
		Mockito.when(labelServiceForSearch.getObjectLabel(mockedItemModel, mockedLocale)).thenReturn("test");

		final String labelValue = displayNameProvider.getLabelValue(mockedItemModel, mockedLocale);
		assertThat(labelValue).isEqualTo("test");
		verify(labelServiceForSearch).getObjectLabel(mockedItemModel, mockedLocale);
	}
}
