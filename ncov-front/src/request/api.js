import { get } from './http'

export const api = {

    apiAddress: function (paramObj,nums) {

        return get(paramObj, {num:nums})

    },

    apiGet: function(paramObj, paras){
        return get(paramObj, paras);
    },

    getOverall: function (latest) {
        return get("overall", {latest:latest})
    },

    getAreaData: function (provinceName) {
        return get("areaData", {provinceName:provinceName})
    },

    getAllSarsData: function(country) {
        return get("getAllSarsData", {country:country})
    },

    getCountrySerial: function(country) {
        return get("getCountrySerial", {country:country})
    },

    getProvinceSerial: function(province) {
        return get("getProvinceSerial", {province:province})
    },

    getCitySerial: function(city) {
        return get("getCitySerial", {city:city})
    },

    getCountryLatest: function(country) {
        return get("getCountryLatest", {country:country})
    },

    getAllCountryLatest: function(){
        return get("getAllCountryLatest", {})
    }


}
