package fr.colline.monatis.typologie;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeCompteInterneConverter implements AttributeConverter<TypeCompteInterne, String> {

	@Override
	public String convertToDatabaseColumn(TypeCompteInterne attribute) {
		
		return attribute == null ? null : attribute.getCode();
	}

	@Override
	public TypeCompteInterne convertToEntityAttribute(String dbData) {

		return dbData == null ? null : TypeCompteInterne.findByCode(dbData);
	}
}
