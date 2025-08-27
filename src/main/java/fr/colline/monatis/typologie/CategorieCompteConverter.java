package fr.colline.monatis.typologie;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategorieCompteConverter implements AttributeConverter<CategorieCompte, String> {

	@Override
	public String convertToDatabaseColumn(CategorieCompte attribute) {

		return attribute == null ? null : attribute.getCode();
	}

	@Override
	public CategorieCompte convertToEntityAttribute(String dbData) {

		return dbData == null ? null : CategorieCompte.findByCode(dbData);
	}
}
