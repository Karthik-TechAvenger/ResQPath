package com.src.model;

import java.util.Date;

import com.src.assetType.AssetType;

class QueryRecord {
    AssetType type;
    int distance;
    Date timestamp;

    QueryRecord(AssetType type, int distance) {
        this.type = type;
        this.distance = distance;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "Asset: " + type + ", Distance: " + distance + ", Time: " + timestamp;
    }
}
