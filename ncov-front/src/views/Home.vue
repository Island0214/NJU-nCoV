<template>
    <div class="home">
        <img src="../assets/coro.png"/>
        <el-row>
            <el-col :span="12" class="content-wrapper">
                <div class="title-wrapper">
                    <h1>COVID - 19<br><span class="light">2019新型冠状病毒</span><br>————</h1>
                    <h2>Novel<br>Coronavirus<br>Pneumonia</h2>
                </div>
                <div class="info-wrapper">
                    <h4>DATA SOURCES</h4>
                    <p>中华人民共和国卫生与健康委员会</p>
                    <br>

                    <h4>PROJECT OUTLINE</h4>
                    <p>REAL-TIME DATA OF NEW CORONAVIRUS</p>
                    <p>DYNAMIC EPIDEMIC ANALYSIS</p>
                    <p>COLLECTION OF UPDATED NEWS</p>
                    <p>COMPARISON BETWEEN COVID-19 AND SARS</p>
                </div>
            </el-col>
            <el-col :span="12">
                <div class="overview-wrapper">
                    <h2>Epidemic Overview</h2>
                    <p>数据截止{{getCurTime()}}</p>
                    <h1>——</h1>
                    <h4>累计确诊人数</h4>
                    <h2>{{covid.confirmedCount}}</h2>
                    <h4>昨日新增确诊</h4>
                    <h2>{{covid.confirmedIncr}}</h2>
                    <h4>累计治愈人数</h4>
                    <h2>{{covid.curedCount}}</h2>
                    <h4>累计死亡人数</h4>
                    <h2>{{covid.deadCount}}</h2>
                </div>
                <div class="right-wrapper">
                    <!--<h4>DATA SOURCES</h4>-->
                    <!--<p>中华人民共和国卫生与健康委员会</p>-->
                    <!--<br>-->
                    <p>Project © NJU-nCoV</p>
                    <p>Image © Getty Images</p>
                    <button>GITHUB REPO →</button>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import {api} from "../request/api";
    // @ is an alias to /src
    export default {
        name: 'Home',
        components: {

        },
        data() {
            return {
                covid: {
                    currentConfirmedCount: '',      // 现存确诊人数 confirmedCount(Incr) - curedCount(Incr) - deadCount(Incr)
                    currentConfirmedIncr: '',       // 新增现存确诊人数
                    confirmedCount: '',             // 累计确诊人数
                    confirmedIncr: '',              // 新增确诊人数
                    suspectedCount: '',             // 疑似确诊人数
                    suspectedIncr: '',              // 新增疑似确诊人数
                    curedCount: '',                 // 治愈人数
                    curedIncr: '',                  // 新增治愈人数
                    deadCount: '',                  // 死亡人数
                    deadIncr: '',                   // 新增死亡人数
                    seriousCount: '',               // 重症人数
                    seriousIncr: '',                // 新增重症人数
                    updateTime: '',                 // 数据最后变动时间
                },
            };
        },
        mounted() {
            api.getOverall(1)
                .then(res => {
                    this.covid.currentConfirmedCount = res.results[0].currentConfirmedCount;
                    this.covid.currentConfirmedIncr = res.results[0].currentConfirmedIncr;
                    this.covid.confirmedCount = res.results[0].confirmedCount;
                    this.covid.confirmedIncr = res.results[0].confirmedIncr;
                    this.covid.suspectedCount = res.results[0].suspectedCount;
                    this.covid.suspectedIncr = res.results[0].suspectedIncr;
                    this.covid.curedCount = res.results[0].curedCount;
                    this.covid.curedIncr = res.results[0].curedIncr;
                    this.covid.deadCount = res.results[0].deadCount;
                    this.covid.deadIncr = res.results[0].deadIncr;
                    this.covid.seriousCount = res.results[0].seriousCount;
                    this.covid.seriousIncr = res.results[0].seriousIncr;
                    this.covid.updateTime = res.results[0].updateTime;
                })
        },
        methods: {
            getCurTime: function () {
                let d = new Date();
                return d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()+' '+d.getHours()+':'+d.getMinutes();
            }
        },
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
