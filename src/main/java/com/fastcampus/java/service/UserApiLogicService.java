package com.fastcampus.java.service;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.User;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.UserApiRequest;
import com.fastcampus.java.model.network.response.UserApiResponse;
import com.fastcampus.java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    private final UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
                    user.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setStatus(userApiRequest.getStatus())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setEmail(userApiRequest.getEmail())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
                    return user;
                })
                .map(user -> userRepository.save(user))
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        Optional<User> optional = userRepository.findById(id);

        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user) {

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
