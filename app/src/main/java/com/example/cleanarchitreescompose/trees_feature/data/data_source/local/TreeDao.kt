package com.example.cleanarchitreescompose.trees_feature.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {

    @Query("SELECT * FROM tree")
    fun getTrees(): Flow<List<Tree>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTree(tree: Tree)

}