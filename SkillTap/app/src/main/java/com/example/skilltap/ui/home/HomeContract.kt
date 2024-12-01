package com.example.skilltap.ui.home

object HomeContract {

    data class UiState(
        var title:String = "",
        var subTitle:String = "",
        var searchbarPlaceHolder:String = "",
        var freelancerBannerView: BannerView =
            BannerView("","",""),
        var advertBannerView: BannerView =
            BannerView("","",""),

    )

     data class BannerView(
         var imageUrl:String,
         var title:String,
         var subTitle: String
     )




    sealed interface UiAction {
        data object clickedEstateType:UiAction
    }


}