package com.everis.d4i.tutorial.utils.converters;

import java.time.Year;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {

	@Override
	public Short convertToDatabaseColumn(final Year attribute) {
		return (short) attribute.getValue();
	}

	@Override
	public Year convertToEntityAttribute(final Short dbValue) {
		return Year.of(dbValue);
	}
}
