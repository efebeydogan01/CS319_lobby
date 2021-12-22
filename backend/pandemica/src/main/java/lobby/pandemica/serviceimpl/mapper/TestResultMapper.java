package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.TestResult;
import lobby.pandemica.dto.TestResultDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface TestResultMapper extends BaseMapper<TestResult, TestResultDto>
{
    TestResultMapper INSTANCE = Mappers.getMapper(TestResultMapper.class);
}
