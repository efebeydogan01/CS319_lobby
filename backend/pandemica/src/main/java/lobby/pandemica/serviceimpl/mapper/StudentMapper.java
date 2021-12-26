package lobby.pandemica.serviceimpl.mapper;

import lobby.pandemica.db.Student;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.serviceimpl.mapper.base.BaseMapper;
import lobby.pandemica.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface StudentMapper extends BaseMapper<Student, StudentDto>
{
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
}
