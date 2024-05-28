package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.GroceryItem;
import de.sep.calocalendar.model.GroceryItemModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GroceryItemMapperTest {

    private final GroceryItemMapper mapper = new GroceryItemMapperImpl();

    private GroceryItem entity;
    private GroceryItemModel model;

    @BeforeEach
    void Setup(){

        model = new GroceryItemModel();
        model.setId(1L);
        model.setName("TestGrocery");
        model.setWeight(100);

        entity = new GroceryItem();
        entity.setId(1L);
        entity.setName("TestGrocery");
        entity.setWeight(100);
    }

    @Test
    void toEntity_WhenModelProvided_ThenCorrectEntity() {
        var actual = mapper.toEntity(model);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(entity.getId());
        assertThat(actual.getName()).isEqualTo(entity.getName());
        assertThat(actual.getWeight()).isEqualTo(entity.getWeight());
    }

    @Test
    void toModel_WhenEntityProvided_ThenCorrectModel() {
        var actual = mapper.toModel(entity);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(model.getId());
        assertThat(actual.getName()).isEqualTo(model.getName());
        assertThat(actual.getWeight()).isEqualTo(model.getWeight());
    }

}
