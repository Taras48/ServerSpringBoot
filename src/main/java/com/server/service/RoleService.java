package com.server.service;

import com.server.model.Role;

public interface RoleService {
    Role findAllByRole(String role);
}
