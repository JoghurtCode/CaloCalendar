package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.GroceryItem;
import de.sep.calocalendar.model.GroceryItemModel;
import org.mapstruct.Mapper;

@Mapper
public interface GroceryItemMapper {

    GroceryItem toEntity(GroceryItemModel model);

    GroceryItemModel toModel(GroceryItem entity);

}
