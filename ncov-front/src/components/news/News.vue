<template>
    <el-row class="news-tab">
        <el-col :span = "24">
            <el-tabs value="first" @tab-click="handleClick">
                <el-tab-pane label="新闻" name="first">
                    <div class="new-browse">
                        <div class="new-list" v-for="newpage in news" v-bind:key="newpage.pubDate">
                            <v-news-detail :newpage="newpage"/>
                        </div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="谣言" name="second">
                    <div class="new-browse">
                        <div class="new-list" v-for="rumor in rumors" v-bind:key="rumor.id">
                            <v-rumors-detail :rumor="rumor"/>
                        </div>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </el-col>
    </el-row>
</template>

<script>
    import news_detail from "./NewsDetail";
    import rumors_detail from "./RumorsDetail";
    import {api} from "../../request/api";


    const news = require('../../../static/news');
    const rumors = require('../../../static/rumors');
    export default {
        data() {
            // 调用api接口，并且提供了两个参数
            //setTimeout(function(){console.log(re)},2000)
            return {
                news: news.results,
                rumors: rumors.results
            }
        },
        created() {


            api.apiAddress(
                "news" ,50
            ).then(res => {
                this.news = res.results;
            }) ;

        },
        components: {
            'v-news-detail': news_detail,
            'v-rumors-detail': rumors_detail
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    }
</script>

<style lang="stylus" rel="stylesheet/stylus">
.news-tab
    width 960px
    display inline-block
    padding-top 50px
    .el-tabs__active-bar
        width 40px !important
    .el-tabs__item
        width 50px
        font-size 18px
        padding 0 70px 0 0 !important
    .new-browse
        width: 960px
        font-size: 0

        .new-list
            font-size: 0
            display: inline-block
            margin: 10px 0
</style>
