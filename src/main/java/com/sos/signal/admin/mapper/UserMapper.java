package com.sos.signal.admin.mapper;

import com.sos.signal.admin.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> getUserList();
    void insertUser(UserVo userVo);
    UserVo getUserByEmail(String email);
    UserVo getUserById(int id);

}
