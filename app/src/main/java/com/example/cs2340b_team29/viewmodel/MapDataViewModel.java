package com.example.cs2340b_team29.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.cs2340b_team29.model.MapData;

public class MapDataViewModel extends ViewModel {
    private MapData mapData = MapData.getMapData();

    public void setMapData(int level) {
        mapData.setDifficulty(level);
        // logic for setting the initial enemy locations can be here

    }

    public MapData getMapData() {
        return mapData;
    }

}
