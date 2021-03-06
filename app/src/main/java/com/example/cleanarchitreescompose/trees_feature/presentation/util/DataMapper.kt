package com.example.cleanarchitreescompose.trees_feature.presentation.util

import com.example.cleanarchitreescompose.trees_feature.data.data_source.entity.Tree
import com.example.cleanarchitreescompose.trees_feature.data.data_source.remote.responses.Record

class DataMapper {
    companion object {
        fun mapRecordToTree(record: Record): Tree {
            return Tree(
                datasetid = record.datasetid,
                fields = record.fields,
                geometry = record.geometry,
                record_timestamp = record.record_timestamp,
                recordid = record.recordid
            )
        }
    }
}