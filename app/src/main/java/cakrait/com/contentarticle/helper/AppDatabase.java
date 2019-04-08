package cakrait.com.contentarticle.helper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cakrait.com.contentarticle.dao.ContentDao;
import cakrait.com.contentarticle.model.room.Content;

@Database(entities = {Content.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContentDao contentDao();

}
