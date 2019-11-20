package com.server.repository;


import com.server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {


    private EntityManager entityManager;

    @Autowired
    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findAllByRole(String role) {
        return (Role) entityManager.createQuery(
                "from Role where role = :role")
                .setParameter("role",role).getSingleResult();
    }

}
