package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.CategoryService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerImplTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryControllerImpl categegoryControllerImpl;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCategories() throws NetflixException {

        //given
    	final List<CategoryRest> categoryRestList = new ArrayList<>();
        categoryRestList.add(new CategoryRest());
        categoryRestList.add(new CategoryRest());
        categoryRestList.add(new CategoryRest());
        Mockito.when(categoryService.getCategories()).thenReturn(categoryRestList);

        //when
        final NetflixResponse<List<CategoryRest>> netflixResponse = categegoryControllerImpl.getCategories();

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.OK));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);
        assertNotNull(netflixResponse.getData());
        assertEquals(netflixResponse.getData(), categoryRestList);
    }

    @Test
    public void createCategory() throws NetflixException{
        //given
    	final CategoryRest categoryRest = new CategoryRest();
        categoryRest.setName("Drama");
        Mockito.when(categoryService.createCategories(categoryRest)).thenReturn(categoryRest);

        //when
        final NetflixResponse<CategoryRest> netflixResponse = categegoryControllerImpl.createCategory(categoryRest);

        //then
        assertNotNull(netflixResponse);
        assertEquals(netflixResponse.getStatus(), CommonConstants.SUCCESS);
        assertEquals(netflixResponse.getCode(), String.valueOf(HttpStatus.CREATED));
        assertEquals(netflixResponse.getMessage(), CommonConstants.OK);       
        assertEquals(netflixResponse.getData(), categoryRest);
    }

}