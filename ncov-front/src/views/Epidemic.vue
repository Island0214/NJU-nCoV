<template>
    <div class="epidemic">
        <div>
            <div class="map-wrapper" ref="myEchart"></div>
            <div class="content-wrapper">
                <el-row>
                    <el-col :span="12">
                        <div class="data-wrapper">
                            <h1>实时疫情动态</h1>
                            <p>数据截止{{this.curTime}}</p>
                            <h1>——</h1>

                            <h4>累计确诊人数</h4>
                            <h2>{{statistic.totalDiagnosis}}<span> 较昨日<strong>{{statistic.totalDiagnosisIncrement > 0 ? '+' + statistic.totalDiagnosisIncrement: statistic.totalDiagnosisIncrement}}</strong></span>
                            </h2>
                            <h4>现存确诊人数</h4>
                            <h2>{{statistic.currentDiagnosis}}<span> 较昨日<strong>{{statistic.currentDiagnosisIncrement > 0 ? '+' + statistic.currentDiagnosisIncrement: statistic.currentDiagnosisIncrement}}</strong></span>
                            </h2>
                            <h4>现存疑似人数</h4>
                            <h2>{{statistic.currentSuspect}}<span> 较昨日<strong>{{statistic.currentSuspectIncrement > 0 ? '+' + statistic.currentSuspectIncrement: statistic.currentSuspectIncrement}}</strong></span>
                            </h2>
                            <h4>累计死亡人数</h4>
                            <h2>{{statistic.totalDeath}}<span> 较昨日<strong>{{statistic.totalDeathIncrement > 0 ? '+' + statistic.totalDeathIncrement: statistic.totalDeathIncrement}}</strong></span>
                            </h2>
                            <h4>累计治愈人数</h4>
                            <h2>{{statistic.totalHealing}}<span> 较昨日<strong>{{statistic.totalHealingIncrement > 0 ? '+' + statistic.totalHealingIncrement: statistic.totalHealingIncrement}}</strong></span>
                            </h2>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="area-wrapper">
                            <!-- <h4>感染人数</h4>
                            <p>Number of Infected People</p>
                            <h1>——</h1>
                            <h2>{{area.name}}<br>{{area.value}}</h2> -->
                            <h2>{{area.name}}</h2>
                            <h1>——</h1>
                            <h4>总感染人数</h4>
                            <p>Number of Infected People</p>
                            <h2>{{area.value}}</h2>
                            <h4>治愈人数</h4>
                            <p>Number of Cured People</p>
                            <h2>{{area.cured}}</h2>
                            <h4>剩余感染人数</h4>
                            <p>Number of Current Infected People</p>
                            <h2>{{area.current}}</h2>
                        </div>
                    </el-col>        
                </el-row>
            </div>
        </div>
        <div class="divider-wrapper" style="margin-top: -9vh">
            <el-divider></el-divider>
        </div>
        <div class="tables-wrapper">
            <el-row>
                <el-col :span="6">
                    <div class="select-wrapper">
                        <h1>疫情发展推移<br>——</h1>
                        <el-radio v-model="chartType" label="new">全国新增确诊/疑似人数</el-radio>
                        <el-radio v-model="chartType" label="current">全国现存确诊/疑似人数</el-radio>
                        <el-radio v-model="chartType" label="finish">全国死亡/治愈人数</el-radio>
                        <el-radio v-model="chartType" label="total">全国疫情累计趋势图</el-radio>
                    </div>
                </el-col>
                <el-col :span="18">
                    <StackAreaChart :data="chartData" v-if="chartType === 'total'"></StackAreaChart>
                    <LineChart :data="chartData" v-else></LineChart>
                </el-col>
            </el-row>
        </div>
        <!--<el-divider></el-divider>-->
        <Footer></Footer>
    </div>
</template>

<script>
    import echarts from "echarts";
    import '../../node_modules/echarts/map/js/china.js'
    import LineChart from '../components/charts/LineChart.vue'
    import StackAreaChart from '../components/charts/StackAreaChart.vue'
    import Footer from '../components/Footer.vue'
    import {api} from "../request/api";

    export default {
        name: "Epidemic",
        components: {
            LineChart,
            StackAreaChart,
            Footer
        },
        data() {
            return {
                statistic: {
                    totalDiagnosis: 74282,
                    totalDiagnosisIncrement: 1756,
                    currentDiagnosis: 57337,
                    currentDiagnosisIncrement: -760,
                    currentSuspect: 5248,
                    currentSuspectIncrement: 1185,
                    totalDeath: 2009,
                    totalDeathIncrement: 139,
                    totalHealing: 14938,
                    totalHealingIncrement: 1377,
                },
                area: {
                    name: '全国',
                    value: 74282,
                    cured: 0,
                    current: 0
                },
                country: {
                    name: '全国',
                    value: 74282,
                    cured: 0,
                    current: 0
                },
                chartData: {
                    xAxis: ['2020-02-13', '2020-02-14', '2020-02-15', '2020-02-16', '2020-02-17', '2020-02-18', '2020-02-19'],
                    series: [{
                        name: '新增确证人数',
                        data: [820, 932, 901, 934, 1290, 1330, 1320]
                    }, {
                        name: '新增疑似人数',
                        data: [100, 1213, 122, 342, 1236, 41, 123]
                    }]
                },
                provinceName: {
                    name: ['黑龙江','辽宁','吉林','北京','天津','河北','内蒙古','宁夏','山西','陕西','山东','江苏','安徽','上海',
                    '浙江','福建','河南','湖南','湖北','江西','广东','广西','海南','澳门','香港','台湾','四川','贵州','云南','重庆',
                    '甘肃','青海','西藏','新疆']
                },
                provinceData: [
                    // {"name":"山东", "value":"123"}, {"name":"江苏", "value":"999"}
                ],
                chartType: 'new',
                curTime: '2020-02-24 18:00'
            }
        },
        watch: {
            chartType: function (type) {
                switch (type) {
                    case 'new':
                        this.chartData = {
                            xAxis: ['2020-02-13', '2020-02-14', '2020-02-15', '2020-02-16', '2020-02-17', '2020-02-18', '2020-02-19'],
                            series: [{
                                name: '新增确诊人数',
                                data: [820, 932, 901, 934, 1290, 1330, 1320]
                            }, {
                                name: '新增疑似人数',
                                data: [100, 1213, 122, 342, 1236, 41, 123]
                            }]
                        };
                        break;
                    case 'current':
                        this.chartData = {
                            xAxis: ['2020-02-13', '2020-02-14', '2020-02-15', '2020-02-16', '2020-02-17', '2020-02-18', '2020-02-19'],
                            series: [{
                                name: '现存确诊人数',
                                data: [820, 932, 901, 934, 1290, 1330, 1320]
                            }, {
                                name: '现存疑似人数',
                                data: [100, 1213, 122, 342, 1236, 41, 123]
                            }]
                        };
                        break;
                    case 'finish':
                        this.chartData = {
                            xAxis: ['2020-02-13', '2020-02-14', '2020-02-15', '2020-02-16', '2020-02-17', '2020-02-18', '2020-02-19'],
                            series: [{
                                name: '死亡人数',
                                data: [820, 932, 901, 934, 1290, 1330, 1320]
                            }, {
                                name: '治愈人数',
                                data: [100, 1213, 122, 342, 1236, 41, 123]
                            }]
                        };
                        break;
                    case 'total':
                        this.chartData = {
                            xAxis: ['2020-02-13', '2020-02-14', '2020-02-15', '2020-02-16', '2020-02-17', '2020-02-18', '2020-02-19'],
                            series: [{
                                name: '死亡人数',
                                data: [100, 1213, 122, 342, 1236, 41, 123]
                            }, {
                                name: '治愈人数',
                                data: [100, 1213, 122, 342, 1236, 41, 123]
                            }, {
                                name: '确诊人数',
                                data: [820, 932, 901, 934, 1290, 1330, 1320]
                            }]
                        }
                        break;
                    default:
                        break;
                }
            }
        },
        methods: {
            chinaConfigure() {
                let that = this;
                let myChart = echarts.init(this.$refs.myEchart);
                window.onresize = myChart.resize;
                myChart.setOption({
                    tooltip: {
                        show: false
                    }, // 鼠标移到图里面的浮动提示框
                    dataRange: {
                        show: false,
                        min: 0,
                        max: 1000,
                        text: ['High', 'Low'],
                        realtime: true,
                        calculable: true,
                        // color: ['#1a4474', '#096dd9', '#5cdbd3', '#73d13d', '#a0d911', '#fff566']
                        // color: ['#10239e', '#0050b3', '#5f7685']
                        color: ['#494775', '#414f75', '#5f7685']
                    },
                    geo: { // 这个是重点配置区
                        map: 'china', // 表示中国地图
                        roam: false,
                        label: {
                            normal: {
                                show: true, // 是否显示对应地名
                                textStyle: {
                                    color: 'rgba(0,0,0,0.4)'
                                }
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#303843',
                                borderColor: '#303843'
                            },
                            emphasis: {
                                areaColor: null,
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                shadowBlur: 0,
                                borderWidth: 0,
                                color: '#afcaed',
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    },
                    series: [{
                        type: 'scatter',
                        coordinateSystem: 'geo' // 对应上方配置
                    },
                        {
                            name: '启动次数',
                            type: 'map',
                            geoIndex: 0,
                            data: this.testData
                            // data: [{
                            //     "name": "北京",
                            //     "value": 599
                            // }, {
                            //     "name": "上海",
                            //     "value": 142
                            // }, {
                            //     "name": "黑龙江",
                            //     "value": 44
                            // }, {
                            //     "name": "深圳",
                            //     "value": 92
                            // }, {
                            //     "name": "湖北",
                            //     "value": 810
                            // }, {
                            //     "name": "四川",
                            //     "value": 453
                            // }]
                        }
                    ]
                });

                myChart.on('mouseover', function (params) {
                    api.getAreaData(params.name).then (
                        res => {
                            // console.log(res.confirmedCount);
                            params.value = res.confirmedCount;
                            params.cured = res.curedCount;
                            params.current = res.currentConfirmedCount;
                            that.currentData(params);
                        }
                    )
                    
                });

                myChart.on('mouseout', function () {
                    that.currentData(that.country);
                });
            },
            currentData(param) {
                this.area = {
                    name: param.name,
                    value: isNaN(param.value) ? 0 : param.value,
                    cured: isNaN(param.cured) ? 0 : param.cured,
                    current: isNaN(param.current) ? 0 : param.current,
                };
            },
            getLocalData() {
                let storage = window.localStorage;
                this.statistic.totalDiagnosis = storage.getItem("confirmedCount");
                this.statistic.totalDiagnosisIncrement = storage.getItem("confirmedIncr");
                this.statistic.currentDiagnosis = storage.getItem("currentConfirmedCount");
                this.statistic.currentDiagnosisIncrement = storage.getItem("currentConfirmedIncr");
                this.statistic.currentSuspect = storage.getItem("suspectedCount");
                this.statistic.currentSuspectIncrement = storage.getItem("suspectedIncr");
                this.statistic.totalDeath = storage.getItem("deadCount");
                this.statistic.totalDeathIncrement = storage.getItem("deadIncr");
                this.statistic.totalHealing = storage.getItem("curedCount");
                this.statistic.totalHealingIncrement = storage.getItem("curedIncr");
                this.country.value = storage.getItem("confirmedCount");
                this.country.cured = storage.getItem("curedCount");
                this.country.current = storage.getItem("currentConfirmedCount");
                this.curTime = storage.getItem("date");
            },
            updateMap() {
                let myChart = echarts.init(this.$refs.myEchart);
                myChart.setOption({
                    series: [{
                        type: 'scatter',
                        coordinateSystem: 'geo' // 对应上方配置
                    },
                    {
                        name: '启动次数',
                        type: 'map',
                        geoIndex: 0,
                        data: this.provinceData
                    }
                    ]
                })
            },
            initAreaData() {
                console.log(this.provinceName.name);
                for(let area of this.provinceName.name){
                    console.log(area);
                    api.getAreaData(area).then (
                        res => {
                            console.log(area+","+res.confirmedCount);
                            let obj = {
                                "name": area,
                                // 这里返回的是剩余的人数（去除治愈）
                                "value": res.currentConfirmedCount,
                                "cured": res.curedCount,
                                "current": res.currentConfirmedCount,
                                // "value": res.confirmedCount
                            };
                            this.provinceData.push(obj);
                            this.updateMap();
                        }
                    ); 
                } 
            },
            
        },
        mounted() {
            this.getLocalData();
            this.initAreaData();
            this.chinaConfigure();
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
