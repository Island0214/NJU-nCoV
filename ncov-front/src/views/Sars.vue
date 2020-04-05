<template>
    <div class = "epidemic">
        <div>
            <div class="tables-wrapper">
                <el-row>
                    <el-col :span="6">
                        <div class="select-wrapper">
                            <h1>SARS疫情发展推移<br>——</h1>
                            <el-radio v-model="chartType" label="new">2003年SARS每日数据</el-radio>
                        </div>
                    </el-col>
                    <el-col :span="18">
                        <StackAreaChart :data="chartData" v-if="chartType === 'total'"></StackAreaChart>
                        <LineChart :data="chartData" v-else></LineChart>
                    </el-col>
                </el-row>
            </div>
        </div>

        <el-divider></el-divider>

        <div>
            <h1>什么是SARS？</h1>
            <p class="paragraph" style="font-size: 13px; font-weight: 100; line-height: 16px;">
                引自wiki：<a href="https://zh.wikipedia.org/wiki/嚴重急性呼吸系統綜合症">严重急性呼吸道综合征</a>
            </p>
            <p>-</p>
            <p class="paragraph">
                严重急性呼吸道综合征（英语：Severe Acute Respiratory Syndrome，缩写为SARS），是非典型肺炎的一种。2002年，该病在中华人民共和国广东顺德首发，并扩散至东南亚乃至全球，称为SARS事件。
            </p>
            <p class="paragraph">
                严重急性呼吸道综合征由世界卫生组织的意大利医生卡尔娄·武尔班尼发现。他在越南研究该病毒时受到感染，其后于2003年3月在泰国不治殉职。研究指出，该病是由严重急性呼吸道综合征冠状病毒引起的，传播途径包括由已被感染者所咳出的飞沫传染，而整个传染过程主要透过人与人近距离的接触。此病平均导致7%至15%的患者死亡。世界卫生组织警告，该病死亡率在未来有可能达到15%。不同年龄段中，24岁和以下人群死亡率为1%，25到44岁死亡率为6%，45到64岁死亡率15%，65岁以上的死亡率则为55%。
            </p>
            
        </div>
        <Footer> </Footer>
    </div>
</template>

<script>
    import {api} from "../request/api";
    // import echarts from "echarts"
    import LineChart from '../components/charts/LineChart.vue'
    import Footer from '../components/Footer.vue'
    import StackAreaChart from '../components/charts/StackAreaChart.vue'


    export default {
        name: "Sars",
        components: {
            LineChart,
            StackAreaChart,
            Footer
        },
            
        data() {
            return {
                chartData: {
                    xAxis: [],
                    series: [{
                        name: '确诊人数',
                        data: []
                    }, {
                        name: '疑似人数',
                        data: []
                    }, {
                        name: '死亡人数',
                        data: []
                    }]
                },
                chartType: 'new',
            }
        },
        watch: {
            // chartType: function(type) {
            //     switch(type) {
            //         case 'new':
            //             this.chartData = this.chartData;
            //             break;
            //         default:
            //             break;
            //     }
            // }
        },
        methods: {
            loadAllSarsData() {
                api.getAllSarsData('China').then (
                    res => {
                        let chart = {
                            xAxis: [],
                            series: [
                                {
                                    name: '确诊人数',
                                    data: []
                                }, {
                                    name: '疑似人数',
                                    data: []
                                }, {
                                    name: '死亡人数',
                                    data: []
                                }
                            ]
                        }
                        for(let tmp of res) {
                            chart.xAxis.push(tmp.date);
                            chart.series[0].data.push(tmp.confirm);
                            chart.series[1].data.push(tmp.dead);
                            chart.series[2].data.push(tmp.suspect);
                        }
                        this.chartData = chart;
                    }
                )
            }
        },
        mounted() {
            this.loadAllSarsData();
        }
    }
</script>

<style scoped>
    .epidemic {
        z-index: 1;
        position: relative;
        margin-top: -8vh;
    }

    .map-wrapper {
        height: 100vh;
        width: 100%;
    }

    .content-wrapper {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
    }

    .data-wrapper {
        text-align: left;
        position: absolute;
        top: 17vh;
    }

    .area-wrapper {
        text-align: right;
        position: absolute;
        /* top: 72vh; */
        top: 48vh;
        right: 0;
    }

    .divider-wrapper {
        margin: 0 30px;
    }

    .select-wrapper {
        padding-left: 80px;
    }

    .tables-wrapper {
        text-align: left;
    }

    h2 {
        margin-bottom: 10px;
    }

    h2 span {
        font-size: 10px;
    }

    h2 span strong {
        color: #FFFFFF;
    }


</style>
