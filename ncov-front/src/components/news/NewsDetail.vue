<template>
    <div class="newpage-intro">
        <div class="newpage-detail-info">
            <a style="text-decoration:none" @mouseover="mouseOver" @mouseleave="mouseLeave"
               :style="active" :href="newpage.sourceUrl" class="news-title">{{newpage.title}}</a>
            <div class="news-description">{{newpage.summary}}</div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">

    export default {
        props: {
            newpage: {
                type: Object
            }
        },
        data() {
            return {
                active: ''
            }
        },
        methods: {
            mouseOver: function(){
                this.active = 'color: #ccc';
            },
            mouseLeave: function () {
                this.active = '';
            }
        }
    }
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .newpage-intro
        .newpage-detail-info
            display: inline-block
            .news-title
                display: inline-block
                width: 960px
                vertical-align: top
                color: #fff
                font-weight: 700
                font-size: 16px
                white-space nowrap
                margin: 15px 10px 20px 0
                overflow: hidden
                text-align left
                text-overflow: ellipsis

            .news-description
                height: 74px
                margin: 5px 10px 5px 0
                font-size: 14px
                text-align left
                overflow: hidden
                text-overflow: ellipsis
</style>
