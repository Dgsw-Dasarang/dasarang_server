package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserStatus;
import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.domain.repository.UserRepository;
import com.project.dasarang.domain.user.exception.UserAlreadyActiveException;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.domain.user.presentation.dto.response.UserListResponse;
import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;
import com.project.dasarang.global.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Override
    public UserListResponse getUserList(int page, int size, UserType type) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
        userFacade.checkAdminPermissions();

        Page<User> users = userFacade.findAllUser(pageable, type);
        List<UserResponse> list = users.stream()
                .map(ResponseUtil::getUserResponse)
                .collect(Collectors.toList());

        return UserListResponse.builder()
                .currentPage(users.getNumber() + 1)
                .hasMorePage(users.getTotalPages() > users.getNumber())
                .list(list)
                .build();
    }

    @Override
    public void approveOwner(Long id) {
        userFacade.checkAdminPermissions();
        User user = userFacade.findUserById(id);

        if(user.getStatus().equals(UserStatus.ACTIVE))
            throw UserAlreadyActiveException.EXCEPTION;

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

}
