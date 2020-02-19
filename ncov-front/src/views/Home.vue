<template>
    <div class="home">
        <img src="../assets/coro.png"/>
        <el-row>
            <el-col :span="12" class="content-wrapper">
                <div class="title-wrapper">
                    <h1>COVID - 19<br><span class="light">2019新型冠状病毒</span><br>----</h1>
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
                    <p>数据截止2020-02-19 18:58</p>
                    <h1>----</h1>
                    <h4>累计确诊人数</h4>
                    <h2>74282</h2>
                    <h4>昨日新增确诊</h4>
                    <h2>1754</h2>
                    <h4>累计治愈人数</h4>
                    <h2>14770</h2>
                    <h4>累计死亡人数</h4>
                    <h2>2009</h2>
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
    // @ is an alias to /src
    export default {
        name: 'Home',
        components: {

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

    h1 {
        margin: 0;
        letter-spacing: -2px;
    }

    .light {
        font-weight: 400;
    }

    h2 {
        margin: 0;
        font-weight: 600;
    }

    h4 {
        margin: 0;
        color: #aeaeae;
    }

    p {
        margin: 0;
        color: #cfcfcf;
    }

    button {
        padding: 0;
    }
</style>