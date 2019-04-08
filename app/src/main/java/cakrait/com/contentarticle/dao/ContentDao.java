package cakrait.com.contentarticle.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import cakrait.com.contentarticle.model.room.Content;

@Dao
public interface ContentDao {
    @Insert
    void insert(Content content);


}
