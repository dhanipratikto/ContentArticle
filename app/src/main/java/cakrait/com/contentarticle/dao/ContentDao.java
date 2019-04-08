package cakrait.com.contentarticle.dao;

import android.arch.persistence.room.Insert;

import cakrait.com.contentarticle.model.room.Content;

public interface ContentDao {
    @Insert
    void insert(Content content);


}
