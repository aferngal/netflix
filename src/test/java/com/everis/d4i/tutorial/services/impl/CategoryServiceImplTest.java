
package com.everis.d4i.tutorial.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;
    
    @Mock
	private ModelMapper modelMapper;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCategoriesReturnsAListOfCategoryRest() throws NetflixException {
        //given
        final Category category = new Category();
        final CategoryRest categoryRest = new CategoryRest();
        final List<Category> categoryList = new ArrayList<>();
        
        category.setId(88L);
        category.setName("Buffoonery");        
        categoryList.add(category);
        
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
        Mockito.when(modelMapper.map(category, CategoryRest.class)).thenReturn(categoryRest);

        //when
        final List<CategoryRest> categoryRestList = categoryService.getCategories();

        //then
        //assertThat(categoryRestList.size()).isEqualTo(1);
        assertThat(categoryRestList).contains(categoryRest);
    }

    @Test(expected = NotFoundException.class)
    public void getCategoriesThrowsAnExceptionWhenTheDBContainsNoCategories() throws NetflixException{
        //given
        List<Category> categoryList = new ArrayList<>();
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);

        //when
        categoryService.getCategories();       
       
        
    }

    //happy path
    @Test
    public void createCategoriesSavesTheCategoryInTheDBBasedOnTheCategoryNameAndReturnsTheCategoryWithIdInfo() throws NetflixException{
        //given
        final Category categoryInput = new Category();
        categoryInput.setName("Drama");
        final Category categoryOutput = new Category();
        categoryOutput.setId(3L);
        categoryOutput.setName(categoryInput.getName());
        final CategoryRest categoryRest = new CategoryRest();
        categoryRest.setName("Drama");
        Mockito.when(categoryRepository.save(categoryInput)).thenReturn(categoryOutput);

        //when
       categoryService.createCategories(categoryRest);

        //then
        assertThat(categoryOutput.getName()).isEqualTo(categoryInput.getName());
        assertThat(categoryOutput.getId()).isEqualTo(3L);
    }

    @Test(expected = InternalServerErrorException.class)
    public void createCategoriesThrowsAnInternalSeverErrorExceptionIfThereIsAnErrorWithTheDB () throws NetflixException{
        //given
        Category category = new Category();
        category.setName("Horror");
        CategoryRest categoryRest = new CategoryRest();
        categoryRest.setName("Horror");
        Mockito.when(categoryRepository.save(category)).thenThrow(new PersistenceException());

        //when
        categoryService.createCategories(categoryRest);
    }

    //happy path
    @Test
    public void getCategoryByNameReturnsTheCategoryInformation () throws NetflixException{
        //given
        final String name = "Horror";
        final Category category = new Category();
        final CategoryRest categoryRest1 = new CategoryRest();
        categoryRest1.setName(name);
        Mockito.when(categoryRepository.findByName(name)).thenReturn(Optional.of(category));
		Mockito.when(modelMapper.map(category, CategoryRest.class)).thenReturn(categoryRest1);


        //when
        final CategoryRest categoryRest2 = categoryService.getCategoryByName(name);

        //then
        assertThat(categoryRest2.getName()).isEqualTo(name);
    }

    @Test(expected = NotFoundException.class)
    public void getCategoryByNameThrowsANotFoundExceptionIfCategoryDoesNotExistInDB () throws NetflixException{
        //given
        final String name = "CategoriaInexistente";
        final Category category = new Category();
        category.setName(name);
        Mockito.when(categoryRepository.findByName(name)).thenReturn(Optional.empty());

        //when
        categoryService.getCategoryByName(name);
        
    }
    
    @Test
    public void getCategoryById () throws NetflixException{
        //given
        final long id = 1L;
        final Category category = new Category();
        final CategoryRest categoryRest = new CategoryRest();
        categoryRest.setId(id);
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
		Mockito.when(modelMapper.map(category, CategoryRest.class)).thenReturn(categoryRest);

        //when
        final CategoryRest categoryRest2 = categoryService.getCategoryById(id);

        //then
        assertThat(categoryRest2.getId()).isEqualTo(id);
    }
    
    @Test (expected = NotFoundException.class)
    public void getCategoryByIdNotFoundException () throws NetflixException{
        //given
        final long id = 1L;
        //final Category category = new Category();
        //final CategoryRest categoryRest = new CategoryRest();
        //categoryRest.setId(id);
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.empty());
		//Mockito.when(modelMapper.map(category, CategoryRest.class)).thenReturn(categoryRest);

        //when
        categoryService.getCategoryById(id);

        //then
        //assertThat(categoryRest2.getId()).isEqualTo(id);
    }
}
