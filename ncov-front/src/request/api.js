import { get } from './http'

export const api = {

    apiAddress: function (paramObj,nums) {

        return get(paramObj, {num:nums})

    }

}
