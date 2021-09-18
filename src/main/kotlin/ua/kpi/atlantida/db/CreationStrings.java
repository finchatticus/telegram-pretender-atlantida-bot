package ua.kpi.atlantida.db;

/**
 * Created by vlad on 05.09.2018
 */
class CreationStrings {
    static final int VERSION = 2;
    static final String CREATE_PRETENDER_TABLE = "CREATE TABLE IF NOT EXISTS pretender(" +
            "chat_id INTEGER PRIMARY KEY," +
            "name TEXT," +
            "level TEXT," +
            "faculty TEXT," +
            "my_swimming_level TEXT," +
            "phone TEXT, " +
            "email TEXT," +
            "profile TEXT," +
            "motivation TEXT," +
            "marketing TEXT," +
            "timestamp INTEGER);";
    static final String GET_CURRENT_VERSION = "PRAGMA schema_version;";
}