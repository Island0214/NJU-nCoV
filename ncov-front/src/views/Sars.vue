<template>
    <h1>Test for SARS Page</h1>
</template>

<script>
// import {api} from "../request/api";

export default {
    name: "Sars",
    components: {

    },
        
    data() {
        return {

        }
    }
}
</script>

<style scoped>
    img {
        position: absolute;
        transform: translate(-50%);
        left: 50%;
        height: 60vh;
        margin-top: 12vh;
    }

    .content-wrapper {
        /*margin-top: 20vh;*/
        height: 85vh;
    }

    .title-wrapper {
        text-align: left;
        position: absolute;
        top: 10vh;
    }

    .overview-wrapper {
        position: absolute;
        top: 10vh;
        right: 0;
        text-align: right;
    }

    .info-wrapper {
        text-align: left;
        position: absolute;
        bottom: 0;
    }

    .right-wrapper {
        text-align: right;
        position: absolute;
        bottom: 0;
        right: 0;
    }

    button {
        padding: 0;
    }
</style>
