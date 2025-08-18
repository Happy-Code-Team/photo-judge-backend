package com.photo.judge.common.mapstucture;

import com.photo.judge.model.entity.quartz.QuartzJob;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMapStructCommonService {
	IMapStructCommonService MapStruct = Mappers.getMapper(IMapStructCommonService.class);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void clone(QuartzJob sourceJob, @MappingTarget QuartzJob targetJob);

}
