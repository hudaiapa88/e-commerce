package com.uc.ecommerce.rest.category;

import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.category.CreateCategoryRequest;
import com.uc.ecommerce.model.entity.category.Category;
import com.uc.ecommerce.repository.CategoryRepository;
import com.uc.ecommerce.utils.category.TestCategoryUtility;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;

@Tag("category")
public class CategoryControllerTest extends PlatformTestWithAuth {


    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TestCategoryUtility testCategoryUtility;
    @Nested
    class SaveParent{
        @Test
        void testSaveParent(){
            CreateCategoryRequest createCategoryRequest=buildCreateCategoryRequest();
            CategoryResponse categoryResponse=given().auth().oauth2(getAdmin().getToken())
                    .contentType(ContentType.JSON).body(createCategoryRequest).when().post(path())
                    .then()
                    .spec(ResponseSpec.isCreatedResponse()).extract().body().as(CategoryResponse.class);
            assertThat(categoryRepository.findById(categoryResponse.getId()).isPresent()).isTrue();
            assertThat(createCategoryRequest).isEqualToIgnoringNullFields(categoryResponse);
            assertThat(categoryResponse.getParent()).isNull();
        }

        private String path() {
            return String.format("%s/parent",rootPath());
        }


        private CreateCategoryRequest buildCreateCategoryRequest() {
            CreateCategoryRequest createCategoryRequest= new CreateCategoryRequest();
            createCategoryRequest.setTitle("Ev-Eşya");
            return createCategoryRequest;
        }
    }
    @Nested
    class SaveSubCategory{
        @Test
        void testSaveSubCategory(){
            CreateCategoryRequest createCategoryRequest=buildCreateCategoryRequest();
            Category parentCategory=testCategoryUtility.createAndGetCategory();
            CategoryResponse categoryResponse=given().auth().oauth2(getAdmin().getToken())
                    .contentType(ContentType.JSON).body(createCategoryRequest).when().post(path(parentCategory.getId()))
                    .then()
                    .spec(ResponseSpec.isCreatedResponse()).extract().body().as(CategoryResponse.class);
            assertThat(categoryRepository.findById(categoryResponse.getId()).isPresent()).isTrue();
            assertThat(createCategoryRequest).isEqualToIgnoringNullFields(categoryResponse);
            assertThat(categoryResponse.getParent()).isNotNull();
        }

        private String path(Long parentId) {
            return String.format("%s/parent/%s/sub",rootPath(),parentId);
        }


        private CreateCategoryRequest buildCreateCategoryRequest() {
            CreateCategoryRequest createCategoryRequest= new CreateCategoryRequest();
            createCategoryRequest.setTitle("Ev Terliği");
            return createCategoryRequest;
        }
    }

    @Nested
    class getAllParent{
        @Test
        void testGetAllParent(){
            List<CategoryResponse> categoryResponses= given().contentType(ContentType.JSON)
                    .when().get(path()).then().spec(ResponseSpec.isOkResponse()).extract().body().as(new TypeRef<List<CategoryResponse>>() {});
        }
        private String path() {
            return String.format("%s/parent",rootPath());
        }
    }
    private String rootPath() {
        return String.format("/category");
    }
}
