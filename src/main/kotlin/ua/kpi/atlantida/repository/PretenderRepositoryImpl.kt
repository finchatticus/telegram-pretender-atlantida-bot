package ua.kpi.atlantida.repository

import ua.kpi.atlantida.db.DatabaseManager
import ua.kpi.atlantida.model.Pretender

/**
 * Created by vlad on 05.09.2018
 */
class PretenderRepositoryImpl(private val databaseManager: DatabaseManager) : PretenderRepository {

    override fun get(id: Long): Pretender? {
        return databaseManager.getPretenderById(id)
    }

    override fun insert(pretender: Pretender) {
        databaseManager.insertPretender(pretender)
    }

    override fun update(pretender: Pretender) {
        databaseManager.updatePretender(pretender)
    }

    override fun delete(pretender: Pretender) {
        databaseManager.deletePretender(pretender)
    }

}