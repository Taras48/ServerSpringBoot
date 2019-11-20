package com.server.repository;

import com.server.model.Role;

public interface RoleRepository {

    Role findAllByRole(String role);

}