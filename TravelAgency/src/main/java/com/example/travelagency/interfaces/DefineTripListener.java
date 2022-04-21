package com.example.travelagency.interfaces;

import com.example.travelagency.models.TripStage;

public interface DefineTripListener {
    void onClickChooseDestinationButton();
    void onClickAddPlaneButton(TripStage stage);
    void onClickAddHotelButton(TripStage stage);

}
