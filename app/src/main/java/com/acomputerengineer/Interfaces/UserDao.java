package com.acomputerengineer.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.acomputerengineer.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    Long insert(User u);

    @Query("SELECT * FROM `User` ORDER BY `id` DESC")
    List<User> getAllUsers();

    @Query("SELECT * FROM `User` WHERE `id` =:id")
    User getUser(int id);

    @Update
    void update(User u);

    @Delete
    void delete(User u);
}
