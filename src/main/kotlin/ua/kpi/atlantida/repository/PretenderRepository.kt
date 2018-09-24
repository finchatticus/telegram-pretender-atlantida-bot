package ua.kpi.atlantida.repository

import ua.kpi.atlantida.model.Pretender

/**
 * Created by vlad on 05.09.2018
 */
interface PretenderRepository {

    fun get(id: Long): Pretender?

    fun insert(pretender: Pretender)

    fun update(pretender: Pretender)

    fun delete(pretender: Pretender)

}