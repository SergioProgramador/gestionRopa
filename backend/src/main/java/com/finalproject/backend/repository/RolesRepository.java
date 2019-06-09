package com.finalproject.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.entity.Roles;

@Repository("rolesRepository")
public interface RolesRepository extends JpaRepository<Roles, Serializable>{
	
	public abstract Roles findByRole(String role);

}
