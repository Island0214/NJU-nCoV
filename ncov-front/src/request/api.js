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
    }

}
