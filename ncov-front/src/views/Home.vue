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
                    console.log(res);
                    
                    this.covid.currentConfirmedCount = res.currentConfirmedCount;
                    this.covid.currentConfirmedIncr = res.currentConfirmedIncr;
                    this.covid.confirmedCount = res.confirmedCount;
                    this.covid.confirmedIncr = res.confirmedIncr;
                    this.covid.suspectedCount = res.suspectedCount;
                    this.covid.suspectedIncr = res.suspectedIncr;
                    this.covid.curedCount = res.curedCount;
                    this.covid.curedIncr = res.curedIncr;
                    this.covid.deadCount = res.deadCount;
                    this.covid.deadIncr = res.deadIncr;
                    this.covid.seriousCount = res.seriousCount;
                    this.covid.seriousIncr = res.seriousIncr;
                    this.covid.updateTime = res.updateTime;
                    let storage = window.localStorage;
                    storage.setItem("currentConfirmedCount", res.currentConfirmedCount);
                    storage.setItem("currentConfirmedIncr", res.currentConfirmedIncr);
                    storage.setItem("confirmedCount", res.confirmedCount);
                    storage.setItem("confirmedIncr", res.confirmedIncr);
                    storage.setItem("suspectedCount", res.suspectedCount);
                    storage.setItem("suspectedIncr", res.suspectedIncr);
                    storage.setItem("curedCount", res.curedCount);
                    storage.setItem("curedIncr", res.curedIncr);
                    storage.setItem("deadCount", res.deadCount);
                    storage.setItem("deadIncr", res.deadIncr);
                    storage.setItem("seriousCount", res.seriousCount);
                    storage.setItem("seriousIncr", res.seriousIncr);
                    storage.setItem("updateTime", res.updateTime);
                })
        },
        methods: {
            getCurTime: function () {
                let d = new Date();
                let date = d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()+' '+d.getHours()+':'+d.getMinutes();
                localStorage.setItem("date", date);
                return date;
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
