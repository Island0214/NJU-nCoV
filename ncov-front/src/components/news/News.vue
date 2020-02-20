<template>
    <div class="new-browse">
        <div class="new-list" v-for="newpage in news" v-bind:key="newpage.id">
            <v-news-detail :newpage="newpage"/>
        </div>
    </div>
</template>

<script>
    import news_detail from "./NewsDetail";

    const appData = require('../../../static/News');
    export default {
        data() {
            return {
                news: appData.news
            }
        },
        components: {
            'v-news-detail': news_detail
        }
    }
</script>

<style lang="stylus" rel="stylesheet/stylus">
    .new-browse
        margin: 0 auto
        width: 960px
        font-size: 0

        .new-list
            font-size: 0
            display: inline-block
            margin: 10px 10px
</style>
