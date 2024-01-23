package com.posidex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posidex.entity.UserOps;
import com.posidex.entity.UserOpsIdentity;

public interface UserOpsRepository extends JpaRepository<UserOps, UserOpsIdentity> {

}