package cakrait.com.contentarticle.helper;

import android.arch.persistence.room.Database;

import cakrait.com.contentarticle.dao.ContentDao;
import cakrait.com.contentarticle.model.room.Content;

@Database(entities = (Content.class), version=1)
public abstract class AppDatabase {
    public abstract ContentDao contentDao();

}
